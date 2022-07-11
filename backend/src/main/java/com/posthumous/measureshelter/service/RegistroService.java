package com.posthumous.measureshelter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posthumous.measureshelter.model.RegistroIlha;
import com.posthumous.measureshelter.repository.RegistroRepository;

@Service
public class RegistroService {
  @Autowired
  private RegistroRepository repository;

  public RegistroIlha create(RegistroIlha registro) {
    RegistroIlha created = repository.save(registro);
    return created;
  }
  
}
