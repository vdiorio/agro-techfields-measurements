package com.posthumous.measureshelter.controller;

import java.util.Date;
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

import com.posthumous.measureshelter.model.RegistroIlha;
import com.posthumous.measureshelter.service.RegistroService;

@RestController
@RequestMapping("/ilhas/{id}/registros")
public class RegistroController {
  @Autowired
  private RegistroService service;

}
