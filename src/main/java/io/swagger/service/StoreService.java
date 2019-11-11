package io.swagger.service;

import io.swagger.model.Store;
import io.swagger.repository.StoreRepository;
import io.swagger.repository.StoreRepository.BasicStoreInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

  @Autowired
  StoreRepository storeRepository;

  public BasicStoreInfo getStoreById(String storeId) {
    return storeRepository.findStoreByBranchId(storeId);
  }

  public List<Store> getAllStores() {
    return storeRepository.findAll();
  }

}
