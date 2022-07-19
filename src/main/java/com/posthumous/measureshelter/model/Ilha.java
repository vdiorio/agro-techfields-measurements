package com.posthumous.measureshelter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ilhas")
public class Ilha {
  /**
   * Identificador da ilha.
   */
  @Id
  private String id; // Identificador do documento no MongoDB

  /**
   * Localização da ilha.
   */
  private String localizacao;

  /**
   * Construtor padrão da ilha.
   */
  public Ilha() {}

  /**
   * Construtor da ilha.
   * @param localizacao Localização da ilha.
   */
  public Ilha(final String localizacao) {
    this.localizacao = localizacao;
  }

  /**
   * Retorna o identificador da ilha.
   * @return Identificador da ilha.
   */
  public final String getId() {
    return id;
  }

  /**
   * Define o identificador da ilha.
   * @param id Identificador da ilha.
   */
  public final void setId(final String id) {
    this.id = id;
  }

  /**
   * Retorna a localização da ilha.
   * @return Localização da ilha.
   */
  public final String getLocalizacao() {
    return localizacao;
  }

  /**
   * Define a localização da ilha.
   * @param localizacao Localização da ilha.
   */
  public final void setLocalizacao(final String localizacao) {
    this.localizacao = localizacao;
  }
}
