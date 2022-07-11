package com.posthumous.measureshelter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.posthumous.measureshelter.model.Ilha;
import com.posthumous.measureshelter.service.IlhaService;

@RestController
public class IlhaController {
  @Autowired
  private IlhaService service;

  @PostMapping("/ilhas")
  public ResponseEntity<Ilha> create(@RequestBody Ilha ilha) {
    Ilha created = service.create(ilha);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }
  
  @GetMapping("/ilhas")
  public ResponseEntity<List<Ilha>> findAll() {
    List<Ilha> ilhas = service.findAll();
    return ResponseEntity.ok().body(ilhas);
  }

}
