package com.posthumous.measureshelter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.posthumous.measureshelter.DTO.FotoDTO;
import com.posthumous.measureshelter.model.FotoSatelite;
import com.posthumous.measureshelter.service.FotoService;

@RestController
public class FotoController {
  @Autowired
  private FotoService fotoService;

  @PostMapping("/fotos")
  public ResponseEntity<FotoDTO> create(
      @RequestParam MultipartFile foto,
      HttpServletRequest request
    ) throws IOException {
    FotoSatelite info = fotoService.saveImage(foto);
      return ResponseEntity.status(HttpStatus.CREATED)
        .body(new FotoDTO(info, request.getRequestURL().toString()));
  }

  @GetMapping("/fotos")
  public ResponseEntity<List<FotoDTO>> findAll(HttpServletRequest request) {
    List<FotoSatelite> fotos = fotoService.findAll();
    List<FotoDTO> fotosResponse = fotos.stream()
      .map((foto) -> new FotoDTO(foto, request.getRequestURL().toString()))
      .collect(java.util.stream.Collectors.toList());
    return ResponseEntity.ok().body(fotosResponse);
  }

  @GetMapping("/fotos/{id}")
  public ResponseEntity<FotoDTO> findById(@PathVariable String id, HttpServletRequest request) throws IOException {
    FotoSatelite foto = fotoService.findById(id);
    return ResponseEntity.ok().body(new FotoDTO(foto, request.getRequestURL().toString()));
  }

  @GetMapping("/download/{id}")
  public HttpEntity<byte[]> downloadImage(@PathVariable String id) throws IOException {
    byte[] image = fotoService.downloadImage(id);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG);
    headers.setContentLength(image.length);
    return new HttpEntity<byte[]>(image, headers);
  }

  @DeleteMapping("/fotos/{id}")
  public ResponseEntity<FotoDTO> delete(@PathVariable String id) throws IOException {
    fotoService.delete(id);
    return ResponseEntity.ok().build();
  }
}
