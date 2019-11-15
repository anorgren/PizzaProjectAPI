package io.swagger.repository;

import io.swagger.model.Crust;
import io.swagger.model.DietaryProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class CrustRepositoryTest {

    @Autowired
    private CrustRepository crustRepository;

    private Double price;
    private Crust crustOriginal;
    private Crust crustGlutenFree;

    private static HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;

    @Before
    public void setUp() throws Exception {
        crustRepository.deleteAll();

        price = 100d;

        vegetarianGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        crustOriginal = new Crust();
        crustGlutenFree = new Crust();
        crustOriginal.crustName("original").price(price);
        crustGlutenFree.crustName("gluten free").price(price).dietaryProperties(vegetarianGlutenFree);

        crustRepository.insert(crustOriginal);
        crustRepository.insert(crustGlutenFree);
    }

    @After
    public void tearDown() throws Exception {
        crustRepository.deleteAll();
    }

    @Test
    public void getCrustByCorrectName() {
        Crust actual = crustRepository.getCrustByCrustName("gluten free");
        assertThat(actual).isEqualToComparingFieldByField(crustGlutenFree);
    }

    @Test
    public void getCrustByNonExistentName() {
        Crust actual = crustRepository.getCrustByCrustName("non existent crust");
        assertNull(actual);
    }

    @Test
    public void getAllCrusts() {
        List<Crust> actual = crustRepository.findAll();
        List<Crust> expected = Arrays.asList(crustGlutenFree, crustOriginal);
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }
}