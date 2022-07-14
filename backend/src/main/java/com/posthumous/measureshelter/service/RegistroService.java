package com.posthumous.measureshelter.service;

import java.util.Date;
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
    registro.setData(new Date());
    RegistroIlha created = repository.save(registro);
    return created;
  }

  public RegistroIlha findById(String id) {
    return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro não encontrado."));
  }

  public List<RegistroIlha> findAllByIlhaId(String id) {
    List<RegistroIlha> list = repository.findByIlhaId(id);
    if (list.isEmpty()) {
      throw new IllegalArgumentException("Nenhum registro encontrado.");
    }
    return list;
  }
  
  public void delete(String id) {
    repository.deleteById(id);
  }
}
