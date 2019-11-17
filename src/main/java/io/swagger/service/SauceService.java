package io.swagger.service;

import io.swagger.model.Sauce;
import io.swagger.repository.SauceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SauceService {
    @Autowired
    private SauceRepository sauceRepository;

    public Sauce getSauceBySauceName(String sauceName) {
        return sauceRepository.getSauceBySauceName(sauceName);
    }

    public List<Sauce> getSauces() {
        return sauceRepository.findAll();
    }
}
