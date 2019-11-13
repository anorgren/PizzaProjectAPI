package io.swagger.repository;

import io.swagger.model.Dessert;
import io.swagger.model.DietaryProperty;
import io.swagger.repository.DessertRepository;
import io.swagger.service.DessertService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class DessertRepositoryTest {

    @Autowired
    private DessertRepository dessertRepository;

    private Double price;
    private Dessert cookies;
    private Dessert brownies;
    private List<Dessert> desserts;

    private static HashMap<DietaryProperty, Boolean> vegetarian;

    @Before
    public void setUp() throws Exception {
        dessertRepository.deleteAll();

        price = 4.99d;

        vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        brownies = new Dessert();
        cookies = new Dessert();
        desserts = new ArrayList<>();

        brownies.dessertName("brownies").description("BROWNIE_DESCRIPTION")
                .dietaryProperties(vegetarian).price(price);

        cookies.dessertName("cookies").description("chocolate cookies")
                .dietaryProperties(vegetarian).price(price);

        desserts = new ArrayList<>();
        desserts.add(cookies);
        desserts.add(brownies);


        dessertRepository.insert(desserts);

    }

    @After
    public void tearDown() throws Exception {
        dessertRepository.deleteAll();
    }

    @Test
    public void getDessertByNonExistentName() {
        Dessert actual = dessertRepository.findDessertByDessertName("not a real name");
        assertNull(actual);
    }

    @Test
    public void getDessertByCorrectName() {
        Dessert actual = dessertRepository.findDessertByDessertName("brownies");
        assertThat(actual).isEqualToComparingFieldByField(brownies);
    }

    @Test
    public void getAllDessertsContainsAllDesserts() {
        List<Dessert> actual = dessertRepository.findAll();
        List<Dessert> expected = Arrays.asList(cookies, brownies);
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }
}