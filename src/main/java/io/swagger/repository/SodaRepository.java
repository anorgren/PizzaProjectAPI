package io.swagger.repository;

import io.swagger.model.Soda;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SodaRepository extends MongoRepository<Soda, String> {

  List<Soda> getSodasBySize(String size);

  List<Soda> getSodasBySodaName(String name);
}
