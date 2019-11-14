package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.DietaryProperty;
import io.swagger.model.PizzaSize;
import io.swagger.model.Store;
import io.swagger.repository.StoreRepository;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    private HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
    private Store storeOne;
    private Store storeTwo;
    private Store storeBasic;
    private List<Store> stores;
    private List<PizzaSize> pizzaSizes;
    private List<String> toppings;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();

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
    //TODO: Fix projection issue
    public void getStoresOneStore() throws Exception {
        List<Store> storeList = Arrays.asList(this.storeBasic);
        String basicStoreString = objectMapper.writeValueAsString(storeList);
        when(storeRepository.findAll()).thenReturn(storeList);
        this.mockMvc.perform(get("/stores")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void getStoresByIdValueExists() throws Exception {
        String stringStoreTwo = objectMapper.writeValueAsString(storeTwo);
        when(storeRepository.findStoreByBranchId(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Store store : this.stores) {
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
        when(storeRepository.findStoreByBranchId(any()))
                .thenAnswer(invocationOnMock -> {
                    for (Store store : this.stores) {
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
