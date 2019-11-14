package io.swagger.service;

import static org.junit.Assert.*;

import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
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
  }

  @Test
  public void nonExistentToppingNameReturnsNull() {
    assertNull(toppingRepository.findToppingByToppingName("this topping name does not exist"));
  }

  @Test
  public void getAllToppings() {
  }
}