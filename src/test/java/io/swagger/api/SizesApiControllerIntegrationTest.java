package io.swagger.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizeRepository;
import java.util.Arrays;
import java.util.Collections;
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
@WebMvcTest(SizesApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
        {SizesApiController.class, TestContext.class, WebApplicationContext.class})
public class SizesApiControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaSizeRepository repository;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void contextLoads() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void getSizesEmptySizesRepo() throws Exception {
        when(repository.findAll()).thenReturn(null);
        this.mockMvc.perform(get("/sizes")
                .header("Accept", "application/json"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }


    @Test
    public void getSizesOneSizeInRepo() throws Exception {
        PizzaSize expectedOne;

        expectedOne = new PizzaSize("small", 12);

        List<PizzaSize> singleSize = Collections.singletonList(expectedOne);
        String stringSizesList = objectMapper.writeValueAsString(singleSize);

        when(repository.findAll()).thenReturn(singleSize);
        this.mockMvc.perform(get("/sizes")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringSizesList));
    }

    @Test
    public void getSizesMultipleSizesReturned() throws Exception {
        PizzaSize expectedOne;
        PizzaSize expectedTwo;
        PizzaSize expectedThree;
        List<PizzaSize> allSizes;

        expectedOne = new PizzaSize("small", 12);
        expectedTwo = new PizzaSize("large", 16);
        expectedThree = new PizzaSize("medium", 14);
        allSizes = Arrays.asList(expectedOne, expectedTwo, expectedThree);
        String stringSizesList = objectMapper.writeValueAsString(allSizes);

        when(repository.findAll()).thenReturn(allSizes);
        this.mockMvc.perform(get("/sizes")
                .header("Accept", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(stringSizesList));
    }

    @Test
    public void getSizesInvalidHeader() throws Exception {
        this.mockMvc.perform(get("/sizes")
                .header("null", "null"))
                .andExpect(status().isNotImplemented());
    }
}
