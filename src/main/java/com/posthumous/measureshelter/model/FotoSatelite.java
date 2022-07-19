package com.posthumous.measureshelter.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fotos")
public class FotoSatelite {
  @Id
  private String id; // Identificador do documento no MongoDB

  private String fileName; // Nome da foto salva

  private String path; // URL da foto

  private Date data; // Data e hora da foto

  public FotoSatelite() {}

  public FotoSatelite(String fileName, String path) {
    this.fileName = fileName;
    this.path = path;
    this.data = new Date();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
