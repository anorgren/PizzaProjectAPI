package io.swagger.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.DietaryProperty;
import io.swagger.model.PizzaSize;
import io.swagger.model.Store;
import io.swagger.repository.StoreRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StoresApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {StoresApiController.class, TestContext.class, WebApplicationContext.class})
public class StoresApiControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StoreRepository storeRepository;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(storeRepository).isNotNull();
    }

    @Test
    public void getStoresEmptyStoreList() throws Exception {
        this.mockMvc.perform(get("/stores")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }


    @Test
    public void getStoresOneStoreInRepo() throws Exception {
        HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
        Store storeOne;
        List<StoreRepository.BasicStoreInfo> stores;
        List<PizzaSize> pizzaSizes;
        List<String> toppings;

        veganVegetarianGlutenFree = new HashMap<>();
        veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        PizzaSize expectedOne = new PizzaSize("small", 12);
        PizzaSize expectedTwo = new PizzaSize("large", 16);
        PizzaSize expectedThree = new PizzaSize("medium", 14);
        pizzaSizes = Arrays.asList(expectedOne, expectedTwo, expectedThree);

        toppings = Arrays.asList("pepperoni", "ham", "cheese");

        storeOne = new Store();
        storeOne.id("1").branchName("store one").address("123 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);
        stores = Collections.singletonList(storeOne);
        String basicStoreString = objectMapper.writeValueAsString(stores);

        when(storeRepository.getAllByBranchIdExists(true)).thenReturn(stores);
        this.mockMvc.perform(get("/stores")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(basicStoreString));
    }


    @Test
    public void getStoresManyStoresInRepo() throws Exception {
        HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
        Store storeOne;
        Store storeTwo;
        Store storeBasic;
        List<StoreRepository.BasicStoreInfo> stores;
        List<PizzaSize> pizzaSizes;
        List<String> toppings;

        veganVegetarianGlutenFree = new HashMap<>();
        veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        PizzaSize expectedOne = new PizzaSize("small", 12);
        PizzaSize expectedTwo = new PizzaSize("large", 16);
        PizzaSize expectedThree = new PizzaSize("medium", 14);
        pizzaSizes = Arrays.asList(expectedOne, expectedTwo, expectedThree);

        toppings = Arrays.asList("pepperoni", "ham", "cheese");

        storeOne = new Store();
        storeOne.id("1").branchName("store one").address("123 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeTwo = new Store();
        storeTwo.id("2").branchName("store two").address("1234 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeBasic = new Store();
        storeBasic.id("3").branchName("basic").address("12345 street")
                .dietaryRestrictions(null)
                .availableSizes(null).availableToppings(null);
        stores = Arrays.asList(storeOne, storeTwo, storeBasic);
        String basicStoreString = objectMapper.writeValueAsString(stores);

        when(storeRepository.getAllByBranchIdExists(true)).thenReturn(stores);
        this.mockMvc.perform(get("/stores")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(basicStoreString));
    }

    @Test
    public void getStoresByIdValueExists() throws Exception {
        HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
        Store storeOne;
        Store storeTwo;
        Store storeBasic;
        List<Store> stores;
        List<PizzaSize> pizzaSizes;
        List<String> toppings;

        veganVegetarianGlutenFree = new HashMap<>();
        veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        PizzaSize expectedOne = new PizzaSize("small", 12);
        PizzaSize expectedTwo = new PizzaSize("large", 16);
        PizzaSize expectedThree = new PizzaSize("medium", 14);
        pizzaSizes = Arrays.asList(expectedOne, expectedTwo, expectedThree);

        toppings = Arrays.asList("pepperoni", "ham", "cheese");

        storeOne = new Store();
        storeOne.id("1").branchName("store one").address("123 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeTwo = new Store();
        storeTwo.id("2").branchName("store two").address("1234 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeBasic = new Store();
        storeBasic.id("3").branchName("basic").address("12345 street")
                .dietaryRestrictions(null)
                .availableSizes(null).availableToppings(null);
        stores = Arrays.asList(storeOne, storeTwo, storeBasic);
        String stringStoreTwo = objectMapper.writeValueAsString(storeTwo);

        when(storeRepository.findStoreByBranchId(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Store store : stores) {
                        if (store.getBranchId()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return store;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/stores/2")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringStoreTwo));
    }

    @Test
    public void getStoresByIdValueDoesNotExist() throws Exception {
        HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
        Store storeOne;
        Store storeTwo;
        Store storeBasic;
        List<Store> stores;
        List<PizzaSize> pizzaSizes;
        List<String> toppings;

        veganVegetarianGlutenFree = new HashMap<>();
        veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
        veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);

        PizzaSize expectedOne = new PizzaSize("small", 12);
        PizzaSize expectedTwo = new PizzaSize("large", 16);
        PizzaSize expectedThree = new PizzaSize("medium", 14);
        pizzaSizes = Arrays.asList(expectedOne, expectedTwo, expectedThree);

        toppings = Arrays.asList("pepperoni", "ham", "cheese");

        storeOne = new Store();
        storeOne.id("1").branchName("store one").address("123 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeTwo = new Store();
        storeTwo.id("2").branchName("store two").address("1234 street")
                .dietaryRestrictions(veganVegetarianGlutenFree)
                .availableSizes(pizzaSizes).availableToppings(toppings);

        storeBasic = new Store();
        storeBasic.id("3").branchName("basic").address("12345 street")
                .dietaryRestrictions(null)
                .availableSizes(null).availableToppings(null);
        stores = Arrays.asList(storeOne, storeTwo, storeBasic);

        when(storeRepository.findStoreByBranchId(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Store store : stores) {
                        if (store.getBranchId()
                                .equals(invocationOnMock.getArguments()[0])) {
                            return store;
                        }
                    }
                    return null;
                });
        this.mockMvc.perform(get("/stores/15")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void getStoresTestNoHeader() throws Exception {
        this.mockMvc.perform(get("/stores/15")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }
}
