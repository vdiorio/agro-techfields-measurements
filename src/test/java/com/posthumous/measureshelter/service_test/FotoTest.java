package com.posthumous.measureshelter.service_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.Optional;
import java.util.List;

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
    mockPhoto.setPath("photo/path");
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
    assertEquals(newMock.getPath(), "photo/path");
    assertEquals(newMock.getId(), "1");
    assertNotNull(newMock.getData());
  }

  @Test
  @DisplayName("Testa se o service retorna uma foto pelo id.")
  public void testaBuscaDeFotoPeloId() {
    FotoSatelite mockPhoto = mockPhoto();
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.of(mockPhoto));
    FotoSatelite photo = fotoService.findById("1");
    assertEquals(photo.getPath(), "photo/path");
    assertEquals(photo.getId(), "1");
    assertNotNull(photo.getData());
  }

  @Test
  @DisplayName("Testa se lan??a um erro quando o id n??o existe.")
  public void testaRetornoDeFotoPeloId() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.empty());
    assertThrows(IllegalArgumentException.class, () -> fotoService.findById("1"));
  }

  @Test
  @DisplayName("Testa se o service retorna todas as fotos.")
  public void testaBuscaDeFotoPeloIdDaIlha() {
    List<FotoSatelite> mockPhoto = List.of(mockPhoto());
    Mockito.when(repository.findAll()).thenReturn(mockPhoto);
    List<FotoSatelite> photos = fotoService.findAll();
    assertEquals(photos.size(), 1);
  }

  @Test
  @DisplayName("Testa se o service lan??a um erro quando a lista est?? vazia.")
  public void testaRetornoDeFotoPeloIdDaIlha() {
    List<FotoSatelite> mockPhoto = List.of();
    Mockito.when(repository.findAll()).thenReturn(mockPhoto);
    assertThrows(IllegalArgumentException.class, () -> fotoService.findAll());
  }

  @Test
  @DisplayName("Testa se o service lan??a um erro quando o id n??o existe.")
  public void testaErroDeletaFotoPeloId() {
    Mockito.when(repository.findById(any(String.class))).thenReturn(Optional.empty());
    assertThrows(IllegalArgumentException.class, () -> fotoService.delete("1"));
  }
}
