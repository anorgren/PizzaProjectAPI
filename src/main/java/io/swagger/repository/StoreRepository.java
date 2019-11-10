package io.swagger.repository;

import io.swagger.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {
  Store findStoreByBranchId(String branchId);
}
