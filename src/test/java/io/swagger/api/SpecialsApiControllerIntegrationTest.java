package io.swagger.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Special;
import io.swagger.repository.SpecialsRepository;
import java.util.Arrays;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestPropertySource("classpath:/application-test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SpecialsApiController.class)
@WebAppConfiguration
@ContextConfiguration(classes =
    {SpecialsApiController.class, TestContext.class, WebApplicationContext.class})
public class SpecialsApiControllerIntegrationTest {

   @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpecialsApi api;

    @MockBean
    private SpecialsRepository repository;

    private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    repository.deleteAll();

  }
  @Test
  public void contextLoads() {
    assertThat(repository).isNotNull();
  }

  @Test
  public void getEmptySpecialList() throws Exception {
    this.mockMvc.perform(get("/specials")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json("[]"));
  }

  @Test
  public void getSingleSpecial() throws Exception {
    Special special = new Special().specialId("testId").description("test desc");
    List<Special> specials = Arrays.asList(special);
    String specialJson = objectMapper.writeValueAsString(specials);
    when(repository.findAll()).thenReturn(specials);
    this.mockMvc.perform(get("/specials")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(specialJson));
  }
  @Test
  public void getMultipleSpecials() throws Exception {
    Special special = new Special().specialId("testId").description("test desc");
    Special specialTwo = new Special().specialId("testId 2").description("test desc 2");
    List<Special> specials = Arrays.asList(special, specialTwo);
    String specialJson = objectMapper.writeValueAsString(specials);
    when(repository.findAll()).thenReturn(specials);
    this.mockMvc.perform(get("/specials")
        .header("Accept", "application/json"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(specialJson));
  }
  @Test
  public void getNoHeader() throws Exception {
    this.mockMvc.perform(get("/specials")
        .header("null", "null"))
        .andExpect(status().isNotImplemented());
  }

  @Test
  public void getSpecialNotFound() throws Exception {
    when(repository.findAll()).thenReturn(null);
    this.mockMvc.perform(get("/specials")
        .header("Accept", "application/json"))
        .andExpect(status().isNotFound());
  }

}
