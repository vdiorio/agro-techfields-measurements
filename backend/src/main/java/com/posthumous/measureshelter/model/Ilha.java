package com.posthumous.measureshelter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ilhas")
public class Ilha {
  @Id
  private String id; // Identificador do documento no MongoDB

  private String localizacao; // Localização da ilha

  public Ilha() {}

  public Ilha(String localizacao) {
    this.localizacao = localizacao;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLocalizacao() {
    return localizacao;
  }

  public void setLocalizacao(String localizacao) {
    this.localizacao = localizacao;
  }
}
