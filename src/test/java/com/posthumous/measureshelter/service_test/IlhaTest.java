package com.posthumous.measureshelter.service_test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.List;
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
@DisplayName("Teste de Ilha")
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

  @Test
  @DisplayName("A função findAll deve retornar uma lista de ilhas.")
  public void testaBuscaDeTodasIlhas() {
    List<Ilha> ilhas = List.of(mockIlha());
    Mockito.when(repository.findAll()).thenReturn(ilhas);
    ilhaService.findAll();
    verify(repository, times(1)).findAll();
  }

  @Test
  @DisplayName("A função delete deve deletar uma ilha pelo id.")
  public void testaDelecaoDeIlhaPeloId() {
    Ilha ilha = mockIlha();
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.of(ilha));
    ilhaService.delete("1");
    verify(repository, times(1)).delete(ilha);
  }

  @Test
  @DisplayName("A função delete deve lançar uma exceção caso não encontre uma ilha pelo id.")
  public void testaDelecaoDeIlhaPeloIdSemResultado() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.empty());
    assertThrows(IllegalArgumentException.class, () -> ilhaService.delete("1"));
  }

  @Test
  @DisplayName("A função update deve atualizar uma ilha pelo id.")
  public void testaAtualizacaoDeIlhaPeloId() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.of(mockIlha()));
    Ilha ilha = mockIlha();
    ilhaService.update("1", ilha);
    verify(repository, times(1)).save(any());
  }

  @Test
  @DisplayName("A função update deve lançar uma exceção caso não encontre uma ilha pelo id.")
  public void testaAtualizacaoDeIlhaPeloIdSemResultado() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.empty());
    Ilha ilha = mockIlha();
    assertThrows(IllegalArgumentException.class, () -> ilhaService.update("1", ilha));
  }
}
