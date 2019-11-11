package io.swagger.service;

import io.swagger.model.Breadstick;
import io.swagger.repository.BreadstickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreadstickService {

    @Autowired
    BreadstickRepository breadstickRepository;

    public List<Breadstick> getAllBreadsticks() {
        return breadstickRepository.findAll();
    }

    public List<Breadstick> getBreadsticksByName(String name) {
        return breadstickRepository.getBreadstickByName(name);
    }
}
