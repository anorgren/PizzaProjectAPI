package io.swagger.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SodaTest {

  @Test
  public void testSixPackPrice() {
    Soda soda = new Soda();
    soda.size(Soda.SizeEnum.SIX_PACK);
    assertEquals(3.49d, soda.getPrice(), 0.0);
  }

  @Test
  public void testTwoLiterPrice() {
    Soda soda = new Soda();
    soda.size(Soda.SizeEnum.TWO_LITER);
    assertEquals(2.59d, soda.getPrice(), 0.0);
  }


  @Test
  public void testTwentyOzPrice() {
    Soda soda = new Soda();
    soda.size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE);
    assertEquals(1.75d, soda.getPrice(), 0.0);
  }
}