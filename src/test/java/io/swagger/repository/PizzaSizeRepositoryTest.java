package io.swagger.repository;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.swagger.model.PizzaSize;
import java.util.Arrays;
import java.util.List;
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
public class PizzaSizeRepositoryTest {

  @Autowired
  private PizzaSizeRepository pizzaSizeRepository;

  @Before
  public void setUp() throws Exception {
    pizzaSizeRepository.deleteAll();
  }

  @After
  public void tearDown() throws Exception {
    pizzaSizeRepository.deleteAll();
  }

  @Test
  public void getPizzaSizeBySizeDescription() {
    PizzaSize expected = new PizzaSize("small", 12);
    pizzaSizeRepository.insert(expected);
    PizzaSize actual = pizzaSizeRepository.findPizzaSizeBySizeDescription("small");
    assertThat(actual).isEqualToComparingFieldByField(expected);
  }

  @Test
  public void getPizzaSizeByIncorrectDescription() {
    assertNull(pizzaSizeRepository.findPizzaSizeBySizeDescription("does not exist"));
  }

  @Test
  public void getAllSizes() {
    PizzaSize expectedOne = new PizzaSize("small", 12);
    PizzaSize expectedTwo = new PizzaSize("large", 16);
    PizzaSize expectedThree = new PizzaSize("medium", 14);
    List<PizzaSize> expected = Arrays.asList(expectedOne, expectedTwo, expectedThree);
    pizzaSizeRepository.insert(expected);

    List<PizzaSize> actual = pizzaSizeRepository.findAll();
    assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
        && actual.containsAll(expected));
  }
}