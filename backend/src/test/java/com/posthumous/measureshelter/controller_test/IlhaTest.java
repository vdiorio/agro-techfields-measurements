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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.posthumous.measureshelter.service.IlhaService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Controller Ilhas")
public class IlhaTest {  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private IlhaService ilhaService;


  @Test
  @DisplayName("Testa retorno da rota GET:/ilhas.")
  public void testaSeRetornaListaVaziaQuandoNaoHouverIlhasSalvas() throws Exception {
    Mockito.when(ilhaService.findAll()).thenReturn(List.of());

    mockMvc.perform(get("/ilhas"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string("[]")); 
  }
}
