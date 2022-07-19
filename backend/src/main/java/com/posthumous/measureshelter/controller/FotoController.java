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
  public ResponseEntity<List<FotoSatelite>> findAll() {
    List<FotoSatelite> fotos = fotoService.findAll();
    return ResponseEntity.ok().body(fotos);
  }

  @GetMapping("/fotos/{id}")
  public ResponseEntity<FotoSatelite> findById(@PathVariable String id) {
    FotoSatelite foto = fotoService.findById(id);

  @GetMapping("/download/{id}")
  public HttpEntity<byte[]> downloadImage(@PathVariable String id) throws IOException {
    byte[] image = fotoService.downloadImage(id);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG);
    headers.setContentLength(image.length);
    return new HttpEntity<byte[]>(image, headers);
  }

  @DeleteMapping("/fotos/{id}")
  public ResponseEntity<FotoSatelite> delete(@PathVariable String id) {
    fotoService.delete(id);
    return ResponseEntity.ok().build();
  }
}
