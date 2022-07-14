package com.posthumous.measureshelter.service_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
  import static org.junit.jupiter.api.Assertions.assertThrows;
  import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.posthumous.measureshelter.model.FotoSatelite;
import com.posthumous.measureshelter.repository.FotoSateliteRepository;
import com.posthumous.measureshelter.service.FotoService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste de Foto")
public class FotoTest {
  private FotoSatelite mockPhoto() {
    FotoSatelite mockPhoto = new FotoSatelite();
    mockPhoto.setUrl("http://foto1.com");
    mockPhoto.setId("1");
    mockPhoto.setData(new Date());
    return mockPhoto;
    }
  
  @Mock
  private FotoSateliteRepository repository;

  @InjectMocks
  FotoService fotoService;

  @Test
  @DisplayName("Testa se o service injeta a data para salvar a foto no banco de dados.")
  public void testaInjecaoDeDataNoObjeto() {
    FotoSatelite newMock = mockPhoto();
    Mockito.when(repository.save(any(FotoSatelite.class))).thenReturn(newMock);

    fotoService.create(mockPhoto());
    assertEquals(newMock.getUrl(), "http://foto1.com");
    assertEquals(newMock.getId(), "1");
    assertNotNull(newMock.getData());
  }

  @Test
  @DisplayName("Testa se o service retorna uma foto pelo id.")
  public void testaBuscaDeFotoPeloId() {
    FotoSatelite mockPhoto = mockPhoto();
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.of(mockPhoto));
    FotoSatelite photo = fotoService.findById("1");
    assertEquals(photo.getUrl(), "http://foto1.com");
    assertEquals(photo.getId(), "1");
    assertNotNull(photo.getData());
  }

  @Test
  @DisplayName("Testa se lança um erro quando o id não existe.")
  public void testaRetornoDeFotoPeloId() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.empty());
    assertThrows(IllegalArgumentException.class, () -> fotoService.findById("1"));
  }
}
