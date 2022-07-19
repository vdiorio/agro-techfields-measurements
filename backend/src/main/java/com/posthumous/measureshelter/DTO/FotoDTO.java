package com.posthumous.measureshelter.DTO;

import java.util.Date;

import com.posthumous.measureshelter.model.FotoSatelite;

public class FotoDTO {
  public String id;
  public Date data;
  public String downlaodPath;

  public FotoDTO(FotoSatelite foto, String path) {
    this.id = foto.getId();
    this.data = foto.getData();
    this.downlaodPath = path.replace("/fotos", "/download") + foto.getId();
  }
}
