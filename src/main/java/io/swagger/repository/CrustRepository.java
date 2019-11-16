package io.swagger.repository;

import io.swagger.model.Crust;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrustRepository extends MongoRepository<Crust, String> {
    interface CrustNameOnly {
        String getCrustName();
    }
    Crust getCrustByCrustName(String crustName);
}
