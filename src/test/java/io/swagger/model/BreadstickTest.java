package io.swagger.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BreadstickTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getPriceSmallNoCheese() {
        Breadstick smallNoCheese = new Breadstick();
        smallNoCheese.size(Breadstick.SizeEnum.SMALL).withCheese(Boolean.FALSE);
        assertEquals(2.99d, smallNoCheese.getPrice(), 0.0);
    }

    @Test
    public void getPriceSmallWithCheese() {
        Breadstick smallWithCheese = new Breadstick();
        smallWithCheese.size(Breadstick.SizeEnum.SMALL).withCheese(Boolean.TRUE);
        assertEquals(smallWithCheese.getPrice(), (2.99d + 2.00d), 0.0);
    }

    @Test
    public void getPriceLargeNoCheese() {
        Breadstick largeNoCheese = new Breadstick();
        largeNoCheese.size(Breadstick.SizeEnum.LARGE).withCheese(Boolean.FALSE);
        assertEquals(4.99d, largeNoCheese.getPrice(), 0.0);
    }

    @Test
    public void getPriceLargeWithCheese() {
        Breadstick largeWithCheese = new Breadstick();
        largeWithCheese.size(Breadstick.SizeEnum.LARGE).withCheese(Boolean.TRUE);
        assertEquals((4.99d + 2.00d), largeWithCheese.getPrice(), 0.0);
    }

    @Test
    public void getItemType() {
        String expected = "Breadstick";
        Breadstick breadstick = new Breadstick();
        assertEquals(expected, breadstick.getItemType());
    }
}