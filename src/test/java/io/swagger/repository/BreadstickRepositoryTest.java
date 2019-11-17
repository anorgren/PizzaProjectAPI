package io.swagger.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.swagger.model.Breadstick;
import io.swagger.model.DietaryProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
public class BreadstickRepositoryTest {

    @Autowired
    private BreadstickRepository breadstickRepository;

    @Before
    public void setUp() throws Exception {
        breadstickRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        breadstickRepository.deleteAll();
    }

    @Test
    public void returnOneBreadstickFromFindAll() {
        Breadstick breadstick;

        HashMap<DietaryProperty, Boolean> vegetarian;
        breadstick = new Breadstick();

        vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        breadstick.withCheese(true).size(Breadstick.SizeEnum.LARGE).dietaryProperties(vegetarian);

        breadstickRepository.insert(breadstick);
        List<Breadstick> expected = Collections.singletonList(breadstick);
        List<Breadstick> actual = breadstickRepository.findAll();
        assertTrue(actual.size() == expected.size() && expected.get(0).equals(actual.get(0)));
    }

    @Test
    public void returnManyBreadsticksFromFindAll() {
        Breadstick breadstick;
        Breadstick breadstickTwo;
        Breadstick breadstickThree;
        HashMap<DietaryProperty, Boolean> vegetarian;
        breadstick = new Breadstick();
        breadstickTwo = new Breadstick();
        breadstickThree = new Breadstick();

        vegetarian = new HashMap<>();
        vegetarian.put(DietaryProperty.VEGAN, false);
        vegetarian.put(DietaryProperty.VEGETARIAN, true);
        vegetarian.put(DietaryProperty.GLUTEN_FREE, false);

        breadstick.withCheese(true).size(Breadstick.SizeEnum.LARGE).dietaryProperties(vegetarian);
        breadstickTwo.withCheese(true).size(Breadstick.SizeEnum.SMALL).dietaryProperties(vegetarian);
        breadstickThree.withCheese(false).size(Breadstick.SizeEnum.LARGE).dietaryProperties(vegetarian);

        List<Breadstick> expected = Arrays.asList(breadstick, breadstickTwo, breadstickThree);
        breadstickRepository.insert(expected);
        List<Breadstick> actual = breadstickRepository.findAll();


        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));

    }

    @Test
    public void returnFromEmptyRepository() {
        List<Breadstick> expected = new ArrayList<>();
        List<Breadstick> actual = breadstickRepository.findAll();
        assertEquals(expected, actual);
    }
}