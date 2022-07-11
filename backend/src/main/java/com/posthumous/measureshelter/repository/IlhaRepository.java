package com.posthumous.measureshelter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.posthumous.measureshelter.model.Ilha;

public interface IlhaRepository extends MongoRepository <Ilha, String> {}
