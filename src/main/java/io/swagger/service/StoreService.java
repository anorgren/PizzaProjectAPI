package io.swagger.service;

import io.swagger.model.Store;
import io.swagger.repository.StoreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

  @Autowired
  StoreRepository storeRepository;

  public Store getStoreById(String storeId) {
    return storeRepository.findStoreByBranchId(storeId.toLowerCase());
  }

  public List<Store> getAllStores() {
    return storeRepository.findAll();
  }

}
