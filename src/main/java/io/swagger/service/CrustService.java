package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import io.swagger.model.Crust;
import io.swagger.repository.CrustRepository;

@Service
public class CrustService {
    @Autowired
    CrustRepository crustRepository;

    public Crust getCrustByName(String crustName) {
        return crustRepository.getCrustByCrustName(crustName.toLowerCase());
    }

    public List<Crust> getAllCrusts() {
        return crustRepository.findAll();
    }
}
