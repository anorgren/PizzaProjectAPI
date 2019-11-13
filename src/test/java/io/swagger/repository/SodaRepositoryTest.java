package io.swagger.repository;

import io.swagger.model.DietaryProperty;
import io.swagger.model.Soda;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
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

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class SodaRepositoryTest {
    @Autowired
    private SodaRepository sodaRepository;

    private Soda sixPack;
    private Soda twoLiter;
    private Soda twentyOunce;
    private HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
    private List<Soda> sodas;

    @Before
    public void setUp() throws Exception {
        sodaRepository.deleteAll();

        veganVegetarianGlutenFree = new HashMap<>();
        veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        sixPack = new Soda();
        sixPack.size(Soda.SizeEnum.SIX_PACK).sodaName("coke")
                .dietaryProperties(veganVegetarianGlutenFree);
        twoLiter = new Soda();
        twoLiter.size(Soda.SizeEnum.TWO_LITER).sodaName("sprite")
                .dietaryProperties(veganVegetarianGlutenFree);
        twentyOunce = new Soda();
        twentyOunce.size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE).sodaName("coke")
                .dietaryProperties(veganVegetarianGlutenFree);
        sodas = Arrays.asList(sixPack, twoLiter, twentyOunce);
        sodaRepository.insert(sodas);
    }

    @After
    public void tearDown() throws Exception {
        sodaRepository.deleteAll();
    }

    @Test
    public void getSodasBySizeCorrectSize() {
        List<Soda> expected = Arrays.asList(sixPack);
        List<Soda> actual = sodaRepository.getSodasBySize("SIX_PACK");
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    public void getSodasBySizeDoesNotExist() {
        List<Soda> expected = Arrays.asList();
        List<Soda> actual = sodaRepository.getSodasBySize("does not exist");
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    public void getSodasBySodaName() {
        List<Soda> expected = Arrays.asList(sixPack, twentyOunce);
        List<Soda> actual = sodaRepository.getSodasBySodaName("coke");
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    public void getSodasBySodaNameDoesNotExist() {
        List<Soda> expected = Arrays.asList();
        List<Soda> actual = sodaRepository.getSodasBySodaName("does not exist");
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }
}