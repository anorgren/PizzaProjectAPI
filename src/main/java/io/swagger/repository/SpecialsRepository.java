package io.swagger.repository;

import io.swagger.model.Special;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecialsRepository extends MongoRepository<Special, String> {
  Special findBySpecialId(String specialId);
  List<Special> findAll();
}
