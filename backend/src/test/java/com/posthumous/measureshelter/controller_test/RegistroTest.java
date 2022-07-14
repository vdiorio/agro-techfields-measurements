package com.posthumous.measureshelter.controller_test;

import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.posthumous.measureshelter.model.RegistroIlha;
import com.posthumous.measureshelter.service.RegistroService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Controller Registros")
public class RegistroTest {  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private RegistroService registroService;

  private RegistroIlha mockRegistro() {
    RegistroIlha mockRegistro = new RegistroIlha();
    mockRegistro.setId("1");
    mockRegistro.setIdIlha("1");
    mockRegistro.setLuz((long) 10);
    mockRegistro.setUmidadeAr((long) 10);
    mockRegistro.setUmidadeSolo((long) 10);
    mockRegistro.setTemperatura(10);
    mockRegistro.setData(new Date());
    return mockRegistro;
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/ilhas/{idIlha}/registros.")
  public void testaSeRetornaListaVaziaQuandoNaoHouverRegistrosSalvas() throws Exception {
    Mockito.when(registroService.findAllByIlhaId("1")).thenReturn(List.of());

    mockMvc.perform(get("/ilhas/1/registros"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string("[]")); 
  }

  @Test
  @DisplayName("Testa retorno da rota GET:/ilhas/{idIlha}/registros quando não encontra nenhuma registro.")
  public void testaSeRetornaRegistrosSalvas() throws Exception {
    Mockito.when(registroService.findAllByIlhaId("1"))
      .thenThrow(new IllegalArgumentException("Nenhum registro encontrada."));

    mockMvc.perform(get("/ilhas/1/registros"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.error").value("Nenhum registro encontrada."));
  }

  @Test
  @DisplayName("Testa retorno da rota POST:/ilhas/{idIlha}/registros.")
  public void testaSeRetornaErroQuandoNaoEncontraRegistros() throws Exception {
    
    Mockito.when(registroService.create(any())).thenReturn(mockRegistro());

    mockMvc.perform(post("/ilhas/1/registros")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"url\":\"http://registro1.com\"}"))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id").value("1"))
      .andExpect(jsonPath("$.idIlha").value("1"))
      .andExpect(jsonPath("$.luz").value(10))
      .andExpect(jsonPath("$.umidadeAr").value(10))
      .andExpect(jsonPath("$.umidadeSolo").value(10))
      .andExpect(jsonPath("$.temperatura").value(10));
  }

}
