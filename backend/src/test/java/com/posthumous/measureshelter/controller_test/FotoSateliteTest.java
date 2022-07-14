package com.posthumous.measureshelter.controller_test;


import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import com.posthumous.measureshelter.model.FotoSatelite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.posthumous.measureshelter.service.FotoService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Controller Fotos")
public class FotoSateliteTest {  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private FotoService fotoService;

  private FotoSatelite mockPhoto() {
    FotoSatelite mockPhoto = new FotoSatelite();
    mockPhoto.setUrl("http://foto1.com");
    mockPhoto.setId("1");
    mockPhoto.setData(new Date());
    return mockPhoto;
    }

  @Test
  @DisplayName("Testa retorno da rota GET:/fotos.")
  public void testaSeRetornaListaVaziaQuandoNaoHouverFotosSalvas() throws Exception {
    Mockito.when(fotoService.findAll()).thenReturn(List.of());

    mockMvc.perform(get("/fotos"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string("[]")); 
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/fotos quando não encontra nenhuma foto.")
  public void testaSeRetornaFotosSalvas() throws Exception {
    Mockito.when(fotoService.findAll()).thenThrow(new IllegalArgumentException("Nenhuma foto encontrada."));

    mockMvc.perform(get("/fotos"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.error").value("Nenhuma foto encontrada."));
  }

  @Test
  @DisplayName("Testa retorno da rota POST:/fotos.")
  public void testaSeRetornaErroQuandoNaoEncontraFotos() throws Exception {
    
    Mockito.when(fotoService.create(any())).thenReturn(mockPhoto());

    mockMvc.perform(post("/fotos")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"url\":\"http://foto1.com\"}"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id").value("1"))
      .andExpect(jsonPath("$.url").value("http://foto1.com"));
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/fotos/{id}.")
  public void testaSeRetornaFotoPorId() throws Exception {    
    Mockito.when(fotoService.findById(any())).thenReturn(mockPhoto());
    
    mockMvc.perform(get("/fotos/1"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value("1"))
      .andExpect(jsonPath("$.url").value("http://foto1.com"));
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/fotos/{id} quando o id não existe no banco.")
  public void testaSeRetornaErroQuandoNaoEncontraFoto() throws Exception {    
    Mockito.when(fotoService.findById(any())).thenThrow(new IllegalArgumentException("Não existe uma foto com o id: 1."));
    
    mockMvc.perform(get("/fotos/1"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.error").value("Não existe uma foto com o id: 1."));
  }

  @Test
  @DisplayName("Testa retorno da rota DELETE:/fotos/{id}.")
  public void testaSeRetornaFotoDeletada() throws Exception {
    
    doNothing().when(fotoService).delete(any());
    
    mockMvc.perform(delete("/fotos/1"))
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Testa retorno da rota DELETE:/fotos/{id} quando o id não existe no banco.")
  public void testaSeRetornaErroQuandoNaoEncontrarFoto() throws Exception {
    
    doThrow(new IllegalArgumentException("Não existe uma foto com o id: 1.")).when(fotoService).delete("1");
    
    mockMvc.perform(delete("/fotos/1"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.error").value("Não existe uma foto com o id: 1."));
  }

  @Test
  @DisplayName("As rotas devem retornar status 500 quando ocorre um erro.")
  public void testaSeRetornaErro500() throws Exception {
    
    doThrow(new RuntimeException("Erro Interno no servidor")).when(fotoService).findAll();
    doThrow(new RuntimeException("Erro Interno no servidor")).when(fotoService).findById(any());
    doThrow(new RuntimeException("Erro Interno no servidor")).when(fotoService).create(any());
    doThrow(new RuntimeException("Erro Interno no servidor")).when(fotoService).delete(any());
    
    mockMvc.perform(post("/fotos")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"url\":\"http://foto1.com\"}"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.error").value("Erro Interno no servidor"));

    mockMvc.perform(get("/fotos"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.error").value("Erro Interno no servidor"));
      
    mockMvc.perform(get("/fotos/1"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.error").value("Erro Interno no servidor"));
      
    mockMvc.perform(delete("/fotos/1"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.error").value("Erro Interno no servidor"));
  }
}
