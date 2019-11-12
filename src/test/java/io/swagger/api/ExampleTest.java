package io.swagger.api;


import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class ExampleTest {

  @Autowired
  private ToppingRepository toppingRepository;

  @Test
  public void getToppingByNameTest() throws Exception {
    Topping topping = new Topping();
  }

}
