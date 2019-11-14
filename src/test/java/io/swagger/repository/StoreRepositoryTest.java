package io.swagger.repository;

import io.swagger.model.DietaryProperty;
import io.swagger.model.PizzaSize;
import io.swagger.model.Store;
import io.swagger.model.Topping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.websocket.RemoteEndpoint;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    private HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
    private Store storeOne;
    private Store storeTwo;
    private List<Store> stores;
    private List<PizzaSize> pizzaSizes;
    private List<String> toppings;

    @Before
    public void setUp() throws Exception {
        storeRepository.deleteAll();

        veganVegetarianGlutenFree = new HashMap<>();
        veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        PizzaSize expectedOne = new PizzaSize("small", 12);
        PizzaSize expectedTwo = new PizzaSize("large", 16);
        PizzaSize expectedThree = new PizzaSize("medium", 14);
        pizzaSizes = Arrays.asList(expectedOne, expectedTwo, expectedThree);

        toppings = Arrays.asList("pepperoni");

        storeOne = new Store();
        storeOne.id("1").branchName("store one").address("123 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeTwo = new Store();
        storeTwo.id("2").branchName("store two").address("1234 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        stores = Arrays.asList(storeOne, storeTwo);
        storeRepository.insert(stores);
    }

    @After
    public void tearDown() throws Exception {
        storeRepository.deleteAll();
    }

    @Test
    public void getAllByBranchIdExistsTrue() {
        Store basicStoreOne = new Store();
        basicStoreOne.id("1").branchName("store one").address("123 street")
                .dietaryRestrictions(null)
                .availableSizes(null).availableToppings(null);

        Store basicStoreTwo = new Store();
        basicStoreTwo.id("2").branchName("store two").address("1234 street")
                .dietaryRestrictions(null)
                .availableSizes(null).availableToppings(null);
        List<Store> expected = Arrays.asList(basicStoreOne, basicStoreTwo);

        List<StoreRepository.BasicStoreInfo> actual = storeRepository.getAllByBranchIdExists(Boolean.TRUE);
        assertThat(actual.get(0)).isEqualToComparingFieldByField(expected.get(0));
        assertThat(actual.get(1)).isEqualToComparingFieldByField(expected.get(1));
        assertThat(actual.size() == expected.size());
    }

    @Test
    public void getAllByBranchIdExistsFalse() {
        List<StoreRepository.BasicStoreInfo> actual = storeRepository.getAllByBranchIdExists(Boolean.FALSE);
        List<StoreRepository.BasicStoreInfo> expected = Arrays.asList();
        assertEquals(actual, expected);

    }

    @Test
    public void findStoreByBranchId() {
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