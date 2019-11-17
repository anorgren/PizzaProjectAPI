package io.swagger.repository;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import io.swagger.model.DietaryProperty;
import io.swagger.model.PizzaSize;
import io.swagger.model.Store;
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
public class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    @Before
    public void setUp() throws Exception {
        storeRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        storeRepository.deleteAll();
    }

    @Test
    public void getAllByBranchIdExistsTrue() {

        Store storeOne;
        Store storeTwo;

        HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree = new HashMap<>();
        veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        PizzaSize expectedOne = new PizzaSize("small", 12);
        PizzaSize expectedTwo = new PizzaSize("large", 16);
        PizzaSize expectedThree = new PizzaSize("medium", 14);
        List<PizzaSize> pizzaSizes = Arrays.asList(expectedOne, expectedTwo, expectedThree);

        List<String> toppings = Collections.singletonList("pepperoni");

        storeOne = new Store();
        storeOne.id("1").branchName("store one").address("123 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeTwo = new Store();
        storeTwo.id("2").branchName("store two").address("1234 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        List<Store> stores = Arrays.asList(storeOne, storeTwo);
        storeRepository.insert(stores);

        List<Store> expected = Arrays.asList(storeOne, storeTwo);

        List<StoreRepository.BasicStoreInfo> actual = storeRepository.getAllByBranchIdExists(Boolean.TRUE);
        assertThat(actual.get(0)).isEqualToComparingFieldByField(expected.get(0));
        assertThat(actual.get(1)).isEqualToComparingFieldByField(expected.get(1));
        assertThat(actual.size() == expected.size());
    }

    @Test
    public void getAllByBranchIdExistsFalse() {
        List<StoreRepository.BasicStoreInfo> actual = storeRepository.getAllByBranchIdExists(Boolean.FALSE);
        List<StoreRepository.BasicStoreInfo> expected = Collections.emptyList();
        assertEquals(actual, expected);

    }

    @Test
    public void findStoreByBranchId() {
        Store storeOne;
        Store storeTwo;

        HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree = new HashMap<>();
        veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        PizzaSize expectedOne = new PizzaSize("small", 12);
        PizzaSize expectedTwo = new PizzaSize("large", 16);
        PizzaSize expectedThree = new PizzaSize("medium", 14);
        List<PizzaSize> pizzaSizes = Arrays.asList(expectedOne, expectedTwo, expectedThree);

        List<String> toppings = Collections.singletonList("pepperoni");

        storeOne = new Store();
        storeOne.id("1").branchName("store one").address("123 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeTwo = new Store();
        storeTwo.id("2").branchName("store two").address("1234 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        List<Store> stores = Arrays.asList(storeOne, storeTwo);
        storeRepository.insert(stores);
        Store actual = storeRepository.findStoreByBranchId("1");
        Store expected = storeOne;
        assertEquals(actual, expected);
    }

    @Test
    public void findStoreByBranchIdDoesNotExist() {
        Store actual = storeRepository.findStoreByBranchId("Does not exist");
        assertNull(actual);
    }
}