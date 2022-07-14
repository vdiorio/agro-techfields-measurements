package com.posthumous.measureshelter.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posthumous.measureshelter.model.FotoSatelite;
import com.posthumous.measureshelter.repository.FotoSateliteRepository;

@Service
public class FotoService {
  @Autowired
  private FotoSateliteRepository fotoSateliteRepository;
}
