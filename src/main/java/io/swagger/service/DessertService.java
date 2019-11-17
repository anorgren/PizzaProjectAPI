package io.swagger.service;

import io.swagger.model.Dessert;
import io.swagger.repository.DessertRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DessertService {

    @Autowired
    private DessertRepository dessertRepository;

    public Dessert getDessert(String dessertName) {
        return dessertRepository.findDessertByDessertName(dessertName.toLowerCase());
    }

    public List<Dessert> getAllDesserts() {
        return dessertRepository.findAll();
    }
}
