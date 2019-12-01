package io.swagger.repository;

import io.swagger.model.DietaryProperty;
import io.swagger.model.Soda;
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

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class SodaRepositoryTest {

    @Autowired
    private SodaRepository sodaRepository;

    @Before
    public void setUp() throws Exception {
        sodaRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        sodaRepository.deleteAll();
    }

    @Test
    public void getSodasBySizeCorrectSize() {
        Soda sixPack;
        Soda twoLiter;
        Soda twentyOunce;
        HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
        List<Soda> sodas;
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
        List<Soda> expected = Collections.singletonList(sixPack);
        List<Soda> actual = sodaRepository.getSodasBySize("SIX_PACK");
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    public void getSodasBySizeDoesNotExist() {
        List<Soda> expected = Collections.emptyList();
        List<Soda> actual = sodaRepository.getSodasBySize("does not exist");
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    public void getSodasBySodaName() {
        Soda sixPack;
        Soda twoLiter;
        Soda twentyOunce;
        HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
        List<Soda> sodas;
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

        List<Soda> expected = Arrays.asList(sixPack, twentyOunce);
        List<Soda> actual = sodaRepository.getSodasBySodaName("coke");
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    public void getSodasBySodaNameDoesNotExist() {
        List<Soda> expected = Collections.emptyList();
        List<Soda> actual = sodaRepository.getSodasBySodaName("does not exist");
        assertTrue(actual.size() == expected.size() && expected.containsAll(actual)
                && actual.containsAll(expected));
    }
}
