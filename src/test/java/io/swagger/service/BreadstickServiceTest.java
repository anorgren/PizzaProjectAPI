package io.swagger.service;

import io.swagger.model.Breadstick;
import io.swagger.repository.BreadstickRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class BreadstickServiceTest {

    @Autowired
    private BreadstickRepository breadstickRepository;

    @Autowired
    private BreadstickService breadstickService;

    private Breadstick breadstick;
    private Breadstick breadstickTwo;
    private Breadstick breadstickThree;

    @Before
    public void setUp() throws Exception {
        breadstickRepository.deleteAll();
        breadstick = new Breadstick();
        breadstickTwo = new Breadstick();
        breadstickThree = new Breadstick();

        breadstick.withCheese(true).size(Breadstick.SizeEnum.LARGE).dietaryProperties(null);
        breadstickTwo.withCheese(true).size(Breadstick.SizeEnum.SMALL).dietaryProperties(null);
        breadstickThree.withCheese(false).size(Breadstick.SizeEnum.LARGE).dietaryProperties(null);


    }

    @After
    public void tearDown() throws Exception {
        breadstickRepository.deleteAll();
    }

    @Test
    public void returnOneBreadstickFromFindAll() {
        breadstickRepository.insert(breadstick);

        assertEquals(breadstickService.getAllBreadsticks().size(), breadstickRepository.findAll().size());
    }

    @Test
    public void returnManyBreadsticksFromFindAll() {
        breadstickRepository.insert(breadstick);
        breadstickRepository.insert(breadstickTwo);
        breadstickRepository.insert(breadstickThree);

        assertEquals(breadstickService.getAllBreadsticks().size(), breadstickRepository.findAll().size());
    }

    public void returnFromEmptyRepository() {
        assertEquals(breadstickService.getAllBreadsticks(), 0);
    }
}