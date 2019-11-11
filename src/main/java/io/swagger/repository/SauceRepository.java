package io.swagger.repository;

import io.swagger.model.Sauce;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SauceRepository extends MongoRepository<Sauce, String> {
    Sauce getSauceBySauceName(String sauceName);
}
