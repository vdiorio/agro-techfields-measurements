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

import com.posthumous.measureshelter.service.FotoService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Controller Fotos")
public class FotoSateliteTest {  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private FotoService fotoService;



  @Test
  @DisplayName("Testa retorno da rota GET:/fotos.")
  public void testaSeRetornaListaVaziaQuandoNaoHouverFotosSalvas() throws Exception {
    Mockito.when(fotoService.findAll()).thenReturn(List.of());

    mockMvc.perform(get("/fotos"))
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string("[]")); 
  }

}
