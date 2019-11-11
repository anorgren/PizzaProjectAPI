package io.swagger.service;

import io.swagger.model.Crust;
import io.swagger.repository.CrustRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrustService {
    @Autowired
    CrustRepository crustRepository;

    public Crust getCrustByName(String crustName) {
        return crustRepository.getCrustByCrustName(crustName);
    }

    public List<Crust> getAllCrusts() {
        return crustRepository.findAll();
    }
}
