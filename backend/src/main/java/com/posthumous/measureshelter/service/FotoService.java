package com.posthumous.measureshelter.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public void delete(String id) {
    FotoSatelite foto = fotoSateliteRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Não existe uma foto com o id: " + id));
    fotoSateliteRepository.delete(foto);
  }
}
