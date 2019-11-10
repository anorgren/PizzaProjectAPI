package io.swagger.service;

import static org.junit.Assert.*;

import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ToppingServiceTest {
  @Autowired
  ToppingRepository toppingRepository;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getTopping() {
    ToppingService toppingService = new ToppingService();
    //System.out.println(toppingRepository.findToppingByToppingName("dafads"));
  }

  @Test
  public void getAllToppings() {
  }
}