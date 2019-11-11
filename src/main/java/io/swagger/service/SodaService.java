package io.swagger.service;

import io.swagger.model.Soda;
import io.swagger.repository.SodaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SodaService {
    @Autowired
    private SodaRepository sodaRepository;

    public List<Soda> getSodasBySize(String size) {
        return sodaRepository.getSodasBySize(size);
    }

    public List<Soda> getSodasByBrandName(String name) {
        return sodaRepository.getSodasBySodaName(name);
    }

    public List<Soda> getAllSodas() {
        return sodaRepository.findAll();
    }
}