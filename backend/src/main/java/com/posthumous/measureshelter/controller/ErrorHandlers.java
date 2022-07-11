package com.posthumous.measureshelter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.posthumous.measureshelter.model.ResponseError;

@ControllerAdvice
public class ErrorHandlers {
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ResponseError> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(e.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseError> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseError(e.getMessage()));
  }
}
