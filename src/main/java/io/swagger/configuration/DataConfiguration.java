package io.swagger.configuration;

import io.swagger.model.DietaryProperty;
import io.swagger.model.Topping;
import io.swagger.repository.ToppingRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataConfiguration {

  private static final BigDecimal NON_MEAT_PRICE = new BigDecimal(2.00);
  private static final BigDecimal MEAT_PRICE = new BigDecimal(3.00);

  private static HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
  private static HashMap<DietaryProperty, Boolean> veganVegetarian;
  private static HashMap<DietaryProperty, Boolean> vegetarian;
  private static HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
  private static HashMap<DietaryProperty, Boolean> notVegetarianIsGlutenFree;
  private static HashMap<DietaryProperty, Boolean> notAnything;

  private static void veganVegetarianGlutenFree() {
    veganVegetarianGlutenFree = new HashMap<>();
    veganVegetarianGlutenFree.put(DietaryProperty.VEGAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
    veganVegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);
  }

  private static void veganVegetarian() {
    veganVegetarian = new HashMap<>();
    veganVegetarian.put(DietaryProperty.VEGAN, true);
    veganVegetarian.put(DietaryProperty.VEGETARIAN, true);
    veganVegetarian.put(DietaryProperty.GLUTEN_FREE, false);
  }

  private static void vegetarian() {
    vegetarian = new HashMap<>();
    vegetarian.put(DietaryProperty.VEGAN, false);
    vegetarian.put(DietaryProperty.VEGETARIAN, true);
    vegetarian.put(DietaryProperty.GLUTEN_FREE, false);
  }

  private static void vegetarianGlutenFree() {
    vegetarianGlutenFree = new HashMap<>();
    vegetarianGlutenFree.put(DietaryProperty.VEGAN, false);
    vegetarianGlutenFree.put(DietaryProperty.VEGETARIAN, true);
    vegetarianGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);
  }

  private static void notVegetarianIsGlutenFree() {
    notVegetarianIsGlutenFree = new HashMap<>();
    notVegetarianIsGlutenFree.put(DietaryProperty.VEGAN, false);
    notVegetarianIsGlutenFree.put(DietaryProperty.VEGETARIAN, false);
    notVegetarianIsGlutenFree.put(DietaryProperty.GLUTEN_FREE, true);
  }

  private static void notAnything() {
    notAnything = new HashMap<>();
    notAnything.put(DietaryProperty.VEGAN, false);
    notAnything.put(DietaryProperty.VEGETARIAN, false);
    notAnything.put(DietaryProperty.GLUTEN_FREE, false);
  }

  private static void initializeDietaryProperties() {
    notAnything();
    notVegetarianIsGlutenFree();
    vegetarianGlutenFree();
    vegetarian();
    veganVegetarian();
    veganVegetarianGlutenFree();

  }

  public static List<Topping> backfillToppingsRepository(ToppingRepository toppingRepository) {
    toppingRepository.deleteAll();
    initializeDietaryProperties();
    List<Topping> defaultToppings = new LinkedList<>();

    Topping hotSauce = new Topping();
    hotSauce.toppingName("hot sauce").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(hotSauce);

    Topping garlic = new Topping();
    garlic.toppingName("garlic").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(garlic);

    Topping jalapenoPeppers = new Topping();
    jalapenoPeppers.toppingName("jalapeno peppers").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(jalapenoPeppers);

    Topping onions = new Topping();
    onions.toppingName("onions").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(onions);

    Topping bananaPeppers = new Topping();
    bananaPeppers.toppingName("banana peppers").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(bananaPeppers);

    Topping dicedTomatoes = new Topping();
    dicedTomatoes.toppingName("diced tomatoes").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(dicedTomatoes);

    Topping blackOlives = new Topping();
    blackOlives.toppingName("black olives").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(blackOlives);

    Topping mushrooms = new Topping();
    mushrooms.toppingName("mushrooms").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(mushrooms);

    Topping pineapple = new Topping();
    pineapple.toppingName("pineapple").price(NON_MEAT_PRICE)
        .dietaryProperties(vegetarian);
    defaultToppings.add(pineapple);

    Topping greenPeppers = new Topping();
    greenPeppers.toppingName("green peppers").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(greenPeppers);

    Topping spinach = new Topping();
    spinach.toppingName("spinach").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(spinach);

    Topping roastedRedPeppers = new Topping();
    roastedRedPeppers.toppingName("roasted red peppers").price(NON_MEAT_PRICE)
        .dietaryProperties(veganVegetarian);
    defaultToppings.add(roastedRedPeppers);

    Topping shreddedProvoloneCheese = new Topping();
    shreddedProvoloneCheese.toppingName("shredded provolone cheese").price(NON_MEAT_PRICE)
        .dietaryProperties(vegetarianGlutenFree);
    defaultToppings.add(shreddedProvoloneCheese);

    Topping cheddarCheese = new Topping();
    cheddarCheese.toppingName("cheddar cheese").price(NON_MEAT_PRICE)
        .dietaryProperties(vegetarianGlutenFree);
    defaultToppings.add(cheddarCheese);

    Topping fetaCheese = new Topping();
    fetaCheese.toppingName("feta cheese").price(NON_MEAT_PRICE)
        .dietaryProperties(vegetarianGlutenFree);
    defaultToppings.add(fetaCheese);

    Topping shreddedParmesan = new Topping();
    shreddedParmesan.toppingName("shredded parmesan asiago").price(NON_MEAT_PRICE)
        .dietaryProperties(vegetarianGlutenFree);
    defaultToppings.add(shreddedParmesan);

    Topping ham = new Topping();
    ham.toppingName("ham").price(MEAT_PRICE)
        .dietaryProperties(notVegetarianIsGlutenFree);
    defaultToppings.add(ham);

    Topping beef = new Topping();
    beef.toppingName("beef").price(MEAT_PRICE)
        .dietaryProperties(notVegetarianIsGlutenFree);
    defaultToppings.add(beef);

    Topping salami = new Topping();
    salami.toppingName("salami").price(MEAT_PRICE)
        .dietaryProperties(notVegetarianIsGlutenFree);
    defaultToppings.add(salami);

    Topping pepperoni = new Topping();
    pepperoni.toppingName("pepperoni").price(MEAT_PRICE)
        .dietaryProperties(veganVegetarianGlutenFree);
    defaultToppings.add(pepperoni);

    Topping italianSausage = new Topping();
    italianSausage.toppingName("italian sausage").price(MEAT_PRICE)
        .dietaryProperties(notVegetarianIsGlutenFree);
    defaultToppings.add(italianSausage);

    Topping premiumChicken = new Topping();
    premiumChicken.toppingName("premium chicken").price(MEAT_PRICE)
        .dietaryProperties(notVegetarianIsGlutenFree);
    defaultToppings.add(premiumChicken);

    Topping bacon = new Topping();
    bacon.toppingName("bacon").price(MEAT_PRICE)
        .dietaryProperties(notVegetarianIsGlutenFree);
    defaultToppings.add(bacon);

    Topping phillySteak = new Topping();
    phillySteak.toppingName("philly steak").price(MEAT_PRICE)
        .dietaryProperties(notAnything);
    defaultToppings.add(phillySteak);

    toppingRepository.insert(defaultToppings);
    return defaultToppings;
  }

}
