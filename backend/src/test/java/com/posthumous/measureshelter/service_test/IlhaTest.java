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

import com.posthumous.measureshelter.model.Ilha;
import com.posthumous.measureshelter.repository.IlhaRepository;
import com.posthumous.measureshelter.service.IlhaService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de Foto")
public class IlhaTest {
  @Mock
  private IlhaRepository repository;
  
  @InjectMocks
  IlhaService ilhaService;

  private Ilha mockIlha() {
    Ilha mockIlha = new Ilha();
    mockIlha.setId("1");
    mockIlha.setLocalizacao("Cajamar");
    return mockIlha;
  }

  @Test
  @DisplayName("A função create deve salvar uma ilha no banco de dados.")
  public void testaInjecaoDeDataNoObjeto() {
    Ilha ilha = mockIlha();
    ilhaService.create(ilha);
    verify(repository, times(1)).save(ilha);
  }

  @Test
  @DisplayName("A função findById deve retornar uma ilha pelo id.")
  public void testaBuscaDeIlhaPeloId() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.of(mockIlha()));
    ilhaService.findById("1");
    verify(repository, times(1)).findById("1");
  }

  @Test
  @DisplayName("A função findById deve lançar uma exceção caso não encontre uma ilha pelo id.")
  public void testaBuscaDeIlhaPeloIdSemResultado() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.empty());
    assertThrows(IllegalArgumentException.class, () -> ilhaService.findById("1"));
  }
}
