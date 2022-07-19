package com.posthumous.measureshelter.controller_test;


import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.posthumous.measureshelter.model.Ilha;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.posthumous.measureshelter.service.IlhaService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Controller Ilhas")
public class IlhaTest {  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private IlhaService ilhaService;

  private Ilha mockIlha() {
    Ilha mockIlha = new Ilha();
    mockIlha.setId("1");
    mockIlha.setLocalizacao("Cajamar");
    return mockIlha;
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/ilhas.")
  public void testaSeRetornaListaVaziaQuandoNaoHouverIlhasSalvas() throws Exception {
    Mockito.when(ilhaService.findAll()).thenReturn(List.of());

    mockMvc.perform(get("/ilhas"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string("[]")); 
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/ilhas quando não encontra nenhuma ilha.")
  public void testaSeRetornaIlhasSalvas() throws Exception {
    Mockito.when(ilhaService.findAll()).thenThrow(new IllegalArgumentException("Nenhuma ilha encontrada."));

    mockMvc.perform(get("/ilhas"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.error").value("Nenhuma ilha encontrada."));
  }

  @Test
  @DisplayName("Testa retorno da rota POST:/ilhas.")
  public void testaSeRetornaErroQuandoNaoEncontraIlhas() throws Exception {
    
    Mockito.when(ilhaService.create(any())).thenReturn(mockIlha());

    mockMvc.perform(post("/ilhas")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"url\":\"http://ilha1.com\"}"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.localizacao").value("Cajamar"));
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/ilhas/{id}.")
  public void testaSeRetornaIlhaPorId() throws Exception {    
    Mockito.when(ilhaService.findById(any())).thenReturn(mockIlha());

    mockMvc.perform(get("/ilhas/1"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.localizacao").value("Cajamar"));
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/ilhas/{id} quando o id não existe no banco.")
  public void testaSeRetornaErroQuandoNaoEncontraIlha() throws Exception {    
    Mockito.when(ilhaService.findById(any())).thenThrow(new IllegalArgumentException("Não existe uma ilha com o id: 1."));

    mockMvc.perform(get("/ilhas/1"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.error").value("Não existe uma ilha com o id: 1."));
  }

  @Test
  @DisplayName("Testa retorno da rota DELETE:/ilhas/{id}.")
  public void testaSeRetornaIlhaDeletada() throws Exception {
    
    doNothing().when(ilhaService).delete(any());

    mockMvc.perform(delete("/ilhas/1"))
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Testa retorno da rota DELETE:/ilhas/{id} quando o id não existe no banco.")
  public void testaSeRetornaErroQuandoNaoEncontrarIlha() throws Exception {
    
    doThrow(new IllegalArgumentException("Não existe uma ilha com o id: 1.")).when(ilhaService).delete("1");

    mockMvc.perform(delete("/ilhas/1"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.error").value("Não existe uma ilha com o id: 1."));
  }

  @Test
  @DisplayName("As rotas devem retornar status 500 quando ocorre um erro.")
  public void testaSeRetornaErro500() throws Exception {
    
    doThrow(new RuntimeException("Erro Interno no servidor")).when(ilhaService).findAll();
    doThrow(new RuntimeException("Erro Interno no servidor")).when(ilhaService).findById(any());
    doThrow(new RuntimeException("Erro Interno no servidor")).when(ilhaService).create(any());
    doThrow(new RuntimeException("Erro Interno no servidor")).when(ilhaService).delete(any());

    mockMvc.perform(post("/ilhas")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"url\":\"http://ilha1.com\"}"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.error").value("Erro Interno no servidor"));

    mockMvc.perform(get("/ilhas"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.error").value("Erro Interno no servidor"));

    mockMvc.perform(get("/ilhas/1"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.error").value("Erro Interno no servidor"));

    mockMvc.perform(delete("/ilhas/1"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.error").value("Erro Interno no servidor"));
  }
}
