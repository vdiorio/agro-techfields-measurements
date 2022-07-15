package com.posthumous.measureshelter.service_test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.posthumous.measureshelter.model.RegistroIlha;
import com.posthumous.measureshelter.repository.RegistroRepository;
import com.posthumous.measureshelter.service.RegistroService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de Registro")
public class RegistroTest {
  @Mock
  private RegistroRepository repository;
  
  @InjectMocks
  RegistroService registroService;

  private RegistroIlha mockRegistro() {
    RegistroIlha mockRegistro = new RegistroIlha();
    mockRegistro.setId("1");
    mockRegistro.setLuz(300L);
    mockRegistro.setTemperatura(30);
    mockRegistro.setIdIlha("1");
    mockRegistro.setUmidadeAr(500L);
    mockRegistro.setUmidadeSolo(600L);
    return mockRegistro;
  }

  @Test
  @DisplayName("A função create deve salvar um registro no banco de dados.")
  public void testaInjecaoDeDataNoObjeto() {
    RegistroIlha registro = mockRegistro();
    registroService.create(registro);
    verify(repository, times(1)).save(registro);
  }

  @Test
  @DisplayName("A função findById deve retornar um registro pelo id.")
  public void testaBuscaDeRegistroPeloId() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.of(mockRegistro()));
    registroService.findById("1");
    verify(repository, times(1)).findById("1");
  }

  @Test
  @DisplayName("A função findById deve lançar uma exceção caso não encontre um registro pelo id.")
  public void testaBuscaDeRegistroPeloIdSemResultado() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.empty());
    assertThrows(IllegalArgumentException.class, () -> registroService.findById("1"));
  }
}