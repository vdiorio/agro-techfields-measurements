package com.posthumous.measureshelter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.posthumous.measureshelter.model.RegistroIlha;

public interface RegistroRepository extends MongoRepository <RegistroIlha, String> {
  @Query("{ 'idIlha' : ?0 }")
  public List<RegistroIlha> findByIlhaId(String ilhaId);
}
