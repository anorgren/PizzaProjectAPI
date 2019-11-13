package io.swagger.service;

import io.swagger.model.Crust;
import io.swagger.model.DietaryProperty;
import io.swagger.repository.CrustRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class CrustServiceTest {

    @Autowired
    private CrustRepository crustRepository;

    @Autowired
    private CrustService crustService;

    private BigDecimal price;
    private Crust crustOriginal;
    private Crust crustGlutenFree;

    private static HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;

    @Before
    public void setUp() throws Exception {
        crustRepository.deleteAll();

        price = new BigDecimal(100);

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
        Crust actual = crustService.getCrustByName("gluten free");
        assertThat(actual).isEqualToComparingFieldByField(crustGlutenFree);
    }

    @Test
    public void getCrustByCorrectNameMixedCase() {
        Crust actual = crustService.getCrustByName("OrIginAL");
        assertThat(actual).isEqualToComparingFieldByField(crustOriginal);
    }

    @Test
    public void getCrustByNonExistentName() {
        Crust actual = crustService.getCrustByName("non existent crust");
        assertNull(actual);
    }

    @Test
    public void getAllCrusts() {
        List<Crust> actual = crustService.getAllCrusts();
        assertTrue(actual.stream().anyMatch(crust -> crustOriginal.equals(crust)) &
                actual.stream().anyMatch(crust -> crustGlutenFree.equals(crust)));
    }

    @Test
    public void getAllCrustsCheckLength() {
        assertEquals(2, crustService.getAllCrusts().size());
    }
}