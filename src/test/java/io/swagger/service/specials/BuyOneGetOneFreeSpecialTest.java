package io.swagger.service.specials;

import io.swagger.model.*;
import io.swagger.repository.OrderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class BuyOneGetOneFreeSpecialTest {

    @Autowired
    private OrderRepository orderRepository;

    private static final String BROWNIE_NAME = "double chocolate chunk brownies";
    private static final String BROWNIE_DESCRIPTION = "Four large gooey double chocolate brownies.";
    private static final Double BROWNIE_PRICE = new Double(4.99);
    private static final String COCA_COLA_PRODUCT_NAME = "coca cola";
    private static final Double TWO_LITER_PRICE = new Double(2.59);

    private static final String ORDER_ONE_ID = "1";
    private static final String ORDER_TWO_ID = "2";


    private static final String SPECIAL_ID = "BOGO";

    @Autowired
    private ApplicableSpecialFactory applicableSpecialFactory;

    @Before
    public void setUp() throws Exception {
        orderRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        orderRepository.deleteAll();
    }

    @Test
    public void isApplicableValidOrder() {
        Order firstOrder = new Order();
        firstOrder.setOrderId(ORDER_ONE_ID);
        Soda cokeTwoLiter = new Soda();
        cokeTwoLiter = cokeTwoLiter.sodaName(COCA_COLA_PRODUCT_NAME)
                .size(Soda.SizeEnum.TWO_LITER);
        Dessert brownies = new Dessert();
        brownies.dessertName(BROWNIE_NAME).description(BROWNIE_DESCRIPTION)
                .price(BROWNIE_PRICE);
        List<Item> firstOrderItems = new ArrayList<>();
        firstOrderItems.add(cokeTwoLiter);
        firstOrderItems.add(brownies);
        firstOrder.setItemList(firstOrderItems);
        orderRepository.insert(firstOrder);

        ApplicableSpecial special = applicableSpecialFactory.getApplicableSpecial(SPECIAL_ID);

        assertTrue(special.isApplicable(ORDER_ONE_ID));
    }

    @Test
    public void isApplicableInvalidOrder() {
        Order secondOrder = new Order();
        secondOrder.setOrderId(ORDER_TWO_ID);
        Soda cokeTwoLiter = new Soda();
        cokeTwoLiter = cokeTwoLiter.sodaName(COCA_COLA_PRODUCT_NAME)
                .size(Soda.SizeEnum.TWO_LITER);
        orderRepository.insert(secondOrder);
        List<Item> firstOrderItems = new ArrayList<>();
        firstOrderItems.add(cokeTwoLiter);

        ApplicableSpecial special = applicableSpecialFactory.getApplicableSpecial(SPECIAL_ID);

        assertFalse(special.isApplicable(ORDER_TWO_ID));
    }

    @Test
    public void isApplicableOrderDoesntExist() {
        ApplicableSpecial special = applicableSpecialFactory.getApplicableSpecial(SPECIAL_ID);

        assertFalse(special.isApplicable("Doesn't exist"));
    }


    @Test
    public void applyValidOrder() {
        Order firstOrder = new Order();
        firstOrder.setOrderId(ORDER_ONE_ID);
        Soda cokeTwoLiter = new Soda();
        cokeTwoLiter = cokeTwoLiter.sodaName(COCA_COLA_PRODUCT_NAME)
                .size(Soda.SizeEnum.TWO_LITER);
        Dessert brownies = new Dessert();
        brownies.dessertName(BROWNIE_NAME).description(BROWNIE_DESCRIPTION)
                .price(BROWNIE_PRICE);
        List<Item> firstOrderItems = new ArrayList<>();
        firstOrderItems.add(cokeTwoLiter);
        firstOrderItems.add(brownies);
        firstOrder.setItemList(firstOrderItems);
        orderRepository.insert(firstOrder);

        ApplicableSpecial special = applicableSpecialFactory.getApplicableSpecial(SPECIAL_ID);

        special.apply(ORDER_ONE_ID);

        Order databaseFirstOrder = orderRepository.findByOrderId(ORDER_ONE_ID);
        assertEquals(new Price().priceInCents(259), databaseFirstOrder.getDiscountAmount());
        assertEquals(SPECIAL_ID, databaseFirstOrder.getSpecialId());

    }

    @Test
    public void applyInvalidOrder() {
        Order secondOrder = new Order();
        secondOrder.setOrderId(ORDER_TWO_ID);
        secondOrder.setItemList(new ArrayList<>());
        orderRepository.insert(secondOrder);

        ApplicableSpecial special = applicableSpecialFactory.getApplicableSpecial(SPECIAL_ID);
        special.apply(ORDER_TWO_ID);

        Order databaseSecondOrder = orderRepository.findByOrderId(ORDER_TWO_ID);

        //Null because nothing happened
        assertNull(databaseSecondOrder.getSpecialId());
        assertNull(databaseSecondOrder.getDiscountAmount());

    }

    @Test
    public void isApplicableNoItemList() {
        Order secondOrder = new Order();
        secondOrder.setOrderId(ORDER_TWO_ID);
        orderRepository.insert(secondOrder);

        ApplicableSpecial special = applicableSpecialFactory.getApplicableSpecial(SPECIAL_ID);

        assertFalse(special.isApplicable(ORDER_TWO_ID));
    }

}