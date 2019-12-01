package io.swagger.repository;

import io.swagger.model.Soda;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SodaRepository extends MongoRepository<Soda, String> {

    List<Soda> getSodasBySize(String size);

    List<Soda> getSodasBySodaName(String name);
}
