package com.posthumous.measureshelter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posthumous.measureshelter.model.Ilha;
import com.posthumous.measureshelter.repository.IlhaRepository;

@Service
public class IlhaService {
  @Autowired
  private IlhaRepository ilhaRepository;

  public Ilha create(Ilha ilha) {
    return ilhaRepository.save(ilha);
  }

  public List<Ilha> findAll() {
    return ilhaRepository.findAll();
  }

  public Ilha findById(String id) {
    return ilhaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ilha não encontrada."));
  }

  public Ilha update(String id, Ilha ilha) {
    Ilha update = findById(id);
    update.setLocalizacao(ilha.getLocalizacao());
    return ilhaRepository.save(update);
  }

  public void delete(String id) {
    ilhaRepository.deleteById(id);
  }
}
