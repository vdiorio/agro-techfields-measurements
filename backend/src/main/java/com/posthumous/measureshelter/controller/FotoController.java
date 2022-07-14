package com.posthumous.measureshelter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.posthumous.measureshelter.model.FotoSatelite;
import com.posthumous.measureshelter.service.FotoService;

@RestController
public class FotoController {
  @Autowired
  private FotoService fotoService;

  @PostMapping("/fotos")
  public ResponseEntity<FotoSatelite> create(@RequestBody FotoSatelite fotoSatelite) {
    FotoSatelite created = fotoService.create(fotoSatelite);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @GetMapping("/fotos")
  public ResponseEntity<List<FotoSatelite>> findAll() {
    List<FotoSatelite> fotos = fotoService.findAll();
    return ResponseEntity.ok().body(fotos);
  }

  @GetMapping("/fotos/{id}")
  public ResponseEntity<FotoSatelite> findById(@PathVariable String id) {
    FotoSatelite foto = fotoService.findById(id);
    return ResponseEntity.ok().body(foto);
  }

  @DeleteMapping("/fotos/{id}")
  public ResponseEntity<FotoSatelite> delete(@PathVariable String id) {
    fotoService.delete(id);
    return ResponseEntity.ok().build();
  }
}
