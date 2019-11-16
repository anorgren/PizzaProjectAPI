package io.swagger.service;

import io.swagger.model.Store;
import io.swagger.repository.StoreRepository;
import io.swagger.repository.StoreRepository.BasicStoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

  @Autowired
  StoreRepository storeRepository;

  public Store getStoreById(String storeId) {
    return storeRepository.findStoreByBranchId(storeId);
  }

  public List<Store> getAllStores() {
    return storeRepository.findAll();
  }
  public List<BasicStoreInfo> getAllBasicInfoStores() {
    return storeRepository.getAllByBranchIdExists(true);
  }

}
