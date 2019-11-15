package io.swagger.repository;

import io.swagger.model.Breadstick;
import io.swagger.model.DietaryProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class BreadstickRepositoryTest {

    @Autowired
    private BreadstickRepository breadstickRepository;

    private Breadstick breadstick;
    private Breadstick breadstickTwo;
    private Breadstick breadstickThree;

    private HashMap<DietaryProperty, Boolean> vegetarian;

    @Before
    public void setUp() throws Exception {
        breadstickRepository.deleteAll();
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


    }

    @After
    public void tearDown() throws Exception {
        breadstickRepository.deleteAll();
    }

    @Test
    public void returnOneBreadstickFromFindAll() {
        breadstickRepository.insert(breadstick);
        List<Breadstick> expected = Arrays.asList(breadstick);
        List<Breadstick> actual = breadstickRepository.findAll();
        assertTrue(actual.size() == expected.size() && expected.get(0).equals(actual.get(0)));
    }

    @Test
    public void returnManyBreadsticksFromFindAll() {
        List<Breadstick> expected = Arrays.asList(breadstick, breadstickTwo, breadstickThree);
        breadstickRepository.insert(expected);
        List<Breadstick> actual = breadstickRepository.findAll();

        System.out.println(expected);
        System.out.println(actual);
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