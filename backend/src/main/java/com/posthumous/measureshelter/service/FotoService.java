package com.posthumous.measureshelter.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.posthumous.measureshelter.model.FotoSatelite;
import com.posthumous.measureshelter.repository.FotoSateliteRepository;

@Service
public class FotoService {
  @Autowired
  private FotoSateliteRepository fotoSateliteRepository;

  public FotoSatelite create(FotoSatelite fotoSatelite) {
    fotoSatelite.setData(new Date());
    return fotoSateliteRepository.save(fotoSatelite);
  }

  public List<FotoSatelite> findAll() {
    List<FotoSatelite> fotos = fotoSateliteRepository.findAll();
    if (fotos.isEmpty()) {
      throw new IllegalArgumentException("Nenhuma foto encontrada.");
    }
    return fotos;
  }

  public FotoSatelite findById(String id) {
    return fotoSateliteRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Não existe uma foto com o id: " + id));
  }

  public void delete(String id) throws IOException {
    FotoSatelite foto = fotoSateliteRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Não existe uma foto com o id: " + id));
      Files.delete(Paths.get(foto.getPath()));
    fotoSateliteRepository.delete(foto);
  }

  public FotoSatelite saveImage(MultipartFile foto) throws IOException {
    String folder = Paths.get(".").toAbsolutePath().normalize().toString();
    byte[] bytes = foto.getBytes();
    String filename = new Date().getTime() + ".jpg";
    Path path = Path.of(folder + "/backend/src/main/resources/static/images/" + filename);
    Files.write(path, bytes);
    return fotoSateliteRepository.save(new FotoSatelite(filename, path.toString()));
  }

  public byte[] downloadImage(String id) throws IOException {
    FotoSatelite foto = fotoSateliteRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Não existe uma foto com o id: " + id));
    return Files.readAllBytes(Paths.get(foto.getPath()));
  }
}
