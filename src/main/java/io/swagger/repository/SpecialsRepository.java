package io.swagger.repository;

import io.swagger.model.Special;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpecialsRepository extends MongoRepository<Special, String> {

    Special findBySpecialId(String specialId);

    List<Special> findAll();
}
