package com.posthumous.measureshelter.controller;

import com.posthumous.measureshelter.model.RegistroIlha;
import com.posthumous.measureshelter.service.RegistroService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ilhas/{id}/registros")
public class RegistroController {
  @Autowired
  private RegistroService service;

  /**
   * Cria um novo registro de uma ilha.
   * @param id Ilha a ser registrada.
   * @param registro Ilha a ser registrada.
   * @return
   */
  @PostMapping
  public ResponseEntity<RegistroIlha> create(
      @PathVariable String id,
      @RequestBody RegistroIlha registro) {
    registro.setIdIlha(id);
    RegistroIlha created = service.create(registro);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @GetMapping
  public ResponseEntity<List<RegistroIlha>> findAll(@PathVariable String id) {
    List<RegistroIlha> registros = service.findAllByIlhaId(id);
    return ResponseEntity.ok().body(registros );
  }

  @GetMapping("/{registerId}")
  public ResponseEntity<RegistroIlha> findById(@PathVariable String registerId) {
    RegistroIlha registro = service.findById(registerId);
    return ResponseEntity.ok().body(registro);
  }

  @DeleteMapping
  public ResponseEntity<RegistroIlha> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
