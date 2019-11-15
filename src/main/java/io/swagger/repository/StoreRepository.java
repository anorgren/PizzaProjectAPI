package io.swagger.repository;

import io.swagger.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {
    /**
     * Creates a projection to select only the most basal elements of a store: branch name,
     * branch id, and store address.
     */
    interface BasicStoreInfo {
        String getBranchName();

        String getBranchId();

        String getAddress();
    }

    List<BasicStoreInfo> getAllByBranchIdExists(Boolean bool);

    Store findStoreByBranchId(String branchId);
}