package com.posthumous.measureshelter.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.posthumous.measureshelter.DTO.ErrorDTO;

@ControllerAdvice
public class ErrorHandlers {
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDTO> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorDTO(e.getMessage()));
  }
}
