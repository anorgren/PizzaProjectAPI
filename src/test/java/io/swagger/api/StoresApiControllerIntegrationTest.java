//package io.swagger.api;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//
//import io.swagger.model.DietaryProperty;
//import io.swagger.model.PizzaSize;
//import io.swagger.model.Store;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class StoresApiControllerIntegrationTest {
//
//    @Autowired
//    private StoresApi api;
//
//    private ObjectMapper objectMapper;
//
//    @Before
//    public void setUp() {
//        objectMapper = new ObjectMapper();
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.addHeader("Accept", "application/json");
//        api = new StoresApiController(request);
//    }
//    @Test
//    public void getStoresTestOKStatus() throws Exception {
//        ResponseEntity<List<Store>> responseEntity = api.getStores();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//    @Test
//    public void getStoresTestFirstStore() throws Exception {
//        ResponseEntity<List<Store>> responseEntity = api.getStores();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        List<Store> stores = responseEntity.getBody();
//        Store first = stores.get(0);
//        assertEquals(first.getAddress(), "101 Fremont Ave, Seattle, WA 12345");
//        assertEquals(first.getBranchId(), "1");
//        assertEquals(first.getBranchName(), "Fremont Branch");
//        Map<DietaryProperty, Boolean> dietProp =  first.getDietaryRestrictions();
//        assertEquals(dietProp.get(DietaryProperty.VEGAN), true);
//        assertEquals(dietProp.get(DietaryProperty.GLUTEN_FREE), false);
//        assertEquals(dietProp.get(DietaryProperty.VEGETARIAN), true);
//        assertTrue(first.getAvailableSizes().contains(PizzaSize.MEDIUM));
//        assertTrue(first.getAvailableSizes().contains(PizzaSize.LARGE));
//    }
//
//    @Test
//    public void getStoresTestCountStores() throws Exception {
//        ResponseEntity<List<Store>> responseEntity = api.getStores();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        List<Store> stores = responseEntity.getBody();
//        assertEquals(stores.size(),3);;
//    }
//
//    @Test
//    public void getStoresTestToppingNumbers() throws Exception {
//        ResponseEntity<List<Store>> responseEntity = api.getStores();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        List<Store> stores = responseEntity.getBody();
//        assertEquals(stores.get(0).getAvailableToppings().size(),24);
//        assertEquals(stores.get(1).getAvailableToppings().size(),7);
//        assertEquals(stores.get(2).getAvailableToppings().size(),17);
//    }
//
//    @Test
//    public void getStoresTestCheckIDs() throws Exception {
//        ResponseEntity<List<Store>> responseEntity = api.getStores();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        List<Store> stores = responseEntity.getBody();
//        assertEquals(stores.get(0).getBranchId(),"1");
//        assertEquals(stores.get(1).getBranchId(),"2");
//        assertEquals(stores.get(2).getBranchId(),"3");
//    }
//    @Test
//    public void getStoresTestNoHeader() throws Exception {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        objectMapper = new ObjectMapper();
//        api = new StoresApiController(request);
//        ResponseEntity<List<Store>> responseEntity = api.getStores();
//        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
//    }
//
//    @Test
//    public void getStoresByIdTest() throws Exception {
//        String id = "1";
//        ResponseEntity<Store> responseEntity = api.getStoresById(id);
//        Store first = responseEntity.getBody();
//        assertEquals(first.getAddress(), "101 Fremont Ave, Seattle, WA 12345");
//        assertEquals(first.getBranchId(), "1");
//        assertEquals(first.getBranchName(), "Fremont Branch");
//        Map<DietaryProperty, Boolean> dietProp =  first.getDietaryRestrictions();
//        assertEquals(dietProp.get(DietaryProperty.VEGAN), true);
//        assertEquals(dietProp.get(DietaryProperty.GLUTEN_FREE), false);
//        assertEquals(dietProp.get(DietaryProperty.VEGETARIAN), true);
//        assertTrue(first.getAvailableSizes().contains(PizzaSize.MEDIUM));
//        assertTrue(first.getAvailableSizes().contains(PizzaSize.LARGE));
//    }
//
//    @Test
//    public void getStoresByIdTest_InvalidId() throws Exception {
//        String id = "-1";
//        ResponseEntity<Store> responseEntity = api.getStoresById(id);
//        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
//    }
//
//    @Test
//    public void getStoresByIdTestNoHeader() throws Exception {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        objectMapper = new ObjectMapper();
//        api = new StoresApiController(request);
//        ResponseEntity<Store> responseEntity = api.getStoresById("1");
//        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
//    }
//
//    @Test
//    public void getToppingsTest_FileNotFound() throws Exception {
//        String fileName = "StoreList.json";
//        String newFileName = "StoreList_new.json";
//        File storeListFile = new File(fileName);
//        File storeListTempFile = new File(newFileName);
//        storeListFile.renameTo(storeListTempFile);
//        ResponseEntity<List<Store>> storeListResponse = api.getStores();
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, storeListResponse.getStatusCode());
//        ResponseEntity<Store> storeResponse = api.getStoresById("1");
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, storeResponse.getStatusCode());
//        storeListTempFile.renameTo(storeListFile);
//    }
//}
