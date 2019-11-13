package io.swagger.repository;

import io.swagger.model.DietaryProperty;
import io.swagger.model.Sauce;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class SauceRepositoryTest {

    @Autowired
    private SauceRepository repository;

    private Sauce original;
    private Sauce robust;
    private HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
    private List<Sauce> sauces;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();

        vegetarianGlutenFree = new HashMap<>();
        vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
        vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        original = new Sauce();
        robust = new Sauce();
        original.sauceName("original").dietaryProperties(vegetarianGlutenFree);
        robust.sauceName("robust").dietaryProperties(vegetarianGlutenFree);

        sauces = Arrays.asList(original, robust);
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void getSauceBySauceNameValidName() {
        repository.insert(sauces);
        Sauce actual = repository.getSauceBySauceName("original");
        Sauce expected = original;
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void getSauceByInvalidName() {
        assertNull(repository.getSauceBySauceName("invalid name"));
    }

    @Test
    public void getAllSaucesEmptyRepo() {
        assertEquals(Collections.emptyList(), repository.findAll());
    }

    @Test
    public void getAllSaucesFilledRepo() {
        repository.insert(sauces);
        List<Sauce> actual = repository.findAll();
        List<Sauce> expected = sauces;
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }
}