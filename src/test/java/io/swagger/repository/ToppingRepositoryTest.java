package io.swagger.repository;

import io.swagger.model.DietaryProperty;
import io.swagger.model.Topping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class ToppingRepositoryTest {
    @Autowired
    ToppingRepository toppingRepository;

    private Topping pepperoni;
    private Topping ham;
    private Topping cheese;
    private HashMap<DietaryProperty, Boolean> notVegetarianIsGlutenFree;
    private HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
    private BigDecimal price;
    private List<Topping> toppings;

    @Before
    public void setUp() throws Exception {
        toppingRepository.deleteAll();

        price = new BigDecimal(5);
        pepperoni = new Topping();
        ham = new Topping();
        cheese = new Topping();
        vegetarianGlutenFree = new HashMap<>();
        notVegetarianIsGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);
        notVegetarianIsGlutenFree.put(DietaryProperty.VEGAN, false);
        notVegetarianIsGlutenFree.put(DietaryProperty.VEGETARIAN, false);
        notVegetarianIsGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        pepperoni.toppingName("pepperoni").price(price)
                .dietaryProperties(notVegetarianIsGlutenFree);
        ham.toppingName("ham").price(price)
                .dietaryProperties(notVegetarianIsGlutenFree);
        cheese.toppingName("cheese").price(price)
                .dietaryProperties(vegetarianGlutenFree);
        toppings = Arrays.asList(pepperoni, ham, cheese);

    }

    @After
    public void tearDown() throws Exception {
        toppingRepository.deleteAll();
    }

    @Test
    public void getToppingByValidName() {
      toppingRepository.insert(toppings);
      Topping actual = toppingRepository.findToppingByToppingName("ham");
      Topping expected = ham;
      assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void nonExistentToppingNameReturnsNull() {
        assertNull(toppingRepository.findToppingByToppingName("this topping name does not exist"));
    }

    @Test
    public void getAllToppings() {
      toppingRepository.insert(toppings);
      List<Topping> actual = toppingRepository.findAll();
      List<Topping> expected = toppings;
      assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
              && actual.containsAll(expected));
    }
}