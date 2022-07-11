package com.posthumous.measureshelter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "registro_ilha")
public class RegistroIlha {
  @Id
  private String id; // Identificador do documento no MongoDB

  private String idIlha; // Identificador da ilha

  private long umidadeAr; // % Porcentagem 

  private long umidadeSolo; // % Porcentagem

  private Integer temperatura; // Graus Celsius

  private Long luz; // Wh/m2

  private String data; // Data e hora da medida

  public RegistroIlha() {}

  public RegistroIlha(String idIlha, long umidadeAr, long umidadeSolo, Integer temperatura, Long luz, String data) {
    this.idIlha = idIlha;
    this.umidadeAr = umidadeAr;
    this.umidadeSolo = umidadeSolo;
    this.temperatura = temperatura;
    this.luz = luz;
    this.data = data;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdIlha() {
    return idIlha;
  }

  public void setIdIlha(String idIlha) {
    this.idIlha = idIlha;
  }

  public long getUmidadeAr() {
    return umidadeAr;
  }

  public void setUmidadeAr(long umidadeAr) {
    this.umidadeAr = umidadeAr;
  }

  public long getUmidadeSolo() {
    return umidadeSolo;
  }

  public void setUmidadeSolo(long umidadeSolo) {
    this.umidadeSolo = umidadeSolo;
  }

  public Integer getTemperatura() {
    return temperatura;
  }

  public void setTemperatura(Integer temperatura) {
    this.temperatura = temperatura;
  }

  public Long getLuz() {
    return luz;
  }

  public void setLuz(Long luz) {
    this.luz = luz;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
