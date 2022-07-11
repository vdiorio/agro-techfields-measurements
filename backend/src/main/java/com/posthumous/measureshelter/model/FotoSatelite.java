package com.posthumous.measureshelter.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fotos")
public class FotoSatelite {
  @Id
  private String id; // Identificador do documento no MongoDB

  private String url; // URL da foto

  private Date data; // Data e hora da foto

  public FotoSatelite(String url) {
    this.url = url;
    this.data = new Date();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }
}
