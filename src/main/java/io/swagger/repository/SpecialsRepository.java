package io.swagger.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import io.swagger.model.Special;

public interface SpecialsRepository extends MongoRepository<Special, String> {
  Special findBySpecialId(String specialId);
  List<Special> findAll();
}
