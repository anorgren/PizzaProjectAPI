package io.swagger.service;

import io.swagger.model.Breadstick;
import io.swagger.repository.BreadstickRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BreadstickService {

    @Autowired
    BreadstickRepository breadstickRepository;

    public List<Breadstick> getAllBreadsticks() {
        return breadstickRepository.findAll();
    }
}
