package io.swagger.configuration;

import io.swagger.model.*;
import io.swagger.repository.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DataConfiguration {

    private static final BigDecimal NON_MEAT_PRICE = new BigDecimal(2.00);
    private static final BigDecimal MEAT_PRICE = new BigDecimal(3.00);

    private static final String SMALL_DESCRIPTION = "small";
    private static final String MEDIUM_DESCRIPTION = "medium";
    private static final String LARGE_DESCRIPTION = "large";
    private static final Integer SMALL_SIZE_INCHES = 12;
    private static final Integer MEDIUM_SIZE_INCHES = 14;
    private static final Integer LARGE_SIZE_INCHES = 16;

    private static HashMap<DietaryProperty, Boolean> veganVegetarianGlutenFree;
    private static HashMap<DietaryProperty, Boolean> veganVegetarian;
    private static HashMap<DietaryProperty, Boolean> vegetarian;
    private static HashMap<DietaryProperty, Boolean> vegetarianGlutenFree;
    private static HashMap<DietaryProperty, Boolean> notVegetarianIsGlutenFree;
    private static HashMap<DietaryProperty, Boolean> notAnything;

    private static final String BRANCH_ONE_NAME = "Fremont Branch";
    private static final String BRANCH_ONE_ID = "1";
    private static final String BRANCH_ONE_ADDRESS =
            "101 Fremont Ave, Seattle, WA 12345";

    private static final String BRANCH_TWO_NAME = "SODO BRANCH";
    private static final String BRANCH_TWO_ID = "2";
    private static final String BRANCH_TWO_ADDRESS =
            "101 1st Ave, Seattle, WA 98765";

    private static final String BRANCH_THREE_NAME = "Capitol Hill Branch";
    private static final String BRANCH_THREE_ID = "3";
    private static final String BRANCH_THREE_ADDRESS =
            "101 Pine Ave, Seattle, WA 01234";

    private static final String GARLIC_BREADSTICKS = "garlic breadsticks";
    private static final String CHEESY_BREADSTICKS = "cheesy breadsticks";

    private static final BigDecimal SMALL_BREADSTICK_PRICE = new BigDecimal(3.99);
    private static final BigDecimal LARGE_BREADSTICK_PRICE = new BigDecimal(5.99);
    private static final BigDecimal SMALL_CHEESE_BREADSTICK_PRICE = new BigDecimal(5.99);
    private static final BigDecimal LARGE_CHEESE_BREADSTICK_PRICE = new BigDecimal(6.99);

    private static final String CHOCOLATE_CHIP_COOKIE_NAME = "chocolate chip cookies";
    private static final String CHOCOLATE_CHIP_COOKIE_DESCRIPTION =
            "six large chocolate chip cookies baked fresh in our ovens.";
    private static final BigDecimal CHOCOLATE_CHIP_COOKIE_PRICE = new BigDecimal(3.99);

    private static final String BROWNIE_NAME = "double chocolate chunk brownies";
    private static final String BROWNIE_DESCRIPTION = "Four large gooey double chocolate brownies.";
    private static final BigDecimal BROWNIE_PRICE = new BigDecimal(4.99);

    private static final String COCA_COLA_PRODUCT_NAME = "coca cola";
    private static final String SPRITE_PRODUCT_NAME = "sprite";
    private static final BigDecimal SIX_PACK_PRICE = new BigDecimal(3.59);
    private static final BigDecimal TWENTY_OUNCE_PRICE = new BigDecimal(1.59);
    private static final BigDecimal TWO_LITER_PRICE = new BigDecimal(2.25);



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

    private static List<Topping> createAllToppings() {
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

        return defaultToppings;
    }

    public static void backfillToppingsRepository(ToppingRepository toppingRepository) {
        toppingRepository.insert(createAllToppings());
    }

    private static List<PizzaSize> createAllPizzaSizes() {
        List<PizzaSize> pizzaSizes = new LinkedList<>();

        PizzaSize pizzaSizeSmall = new PizzaSize(SMALL_DESCRIPTION, SMALL_SIZE_INCHES);
        pizzaSizes.add(pizzaSizeSmall);

        PizzaSize pizzaSizeMedium = new PizzaSize(MEDIUM_DESCRIPTION, MEDIUM_SIZE_INCHES);
        pizzaSizes.add(pizzaSizeMedium);

        PizzaSize pizzaSizeLarge = new PizzaSize(LARGE_DESCRIPTION, LARGE_SIZE_INCHES);
        pizzaSizes.add(pizzaSizeLarge);

        return pizzaSizes;
    }

    public static void backfillPizzaSizesRepository(PizzaSizeRepository pizzaSizeRepository) {
        pizzaSizeRepository.insert(createAllPizzaSizes());
    }

    private static List<Store> createAllStores() {
        initializeDietaryProperties();
        List<Store> stores = new LinkedList<>();
        List<String> toppingNames = new LinkedList<>();

        List<Topping> toppings = createAllToppings();
        for (Topping topping : toppings) {
            String toppingName = topping.getToppingName();
            toppingNames.add(toppingName);
        }

        Store storeOne = new Store();
        storeOne.id(BRANCH_ONE_ID).branchName(BRANCH_ONE_NAME).address(BRANCH_ONE_ADDRESS)
                .dietaryRestrictions(veganVegetarian).availableToppings(toppingNames)
                .availableSizes(createAllPizzaSizes());
        stores.add(storeOne);

        Store storeTwo = new Store();
        storeTwo.id(BRANCH_TWO_ID).branchName(BRANCH_TWO_NAME).address(BRANCH_TWO_ADDRESS)
                .dietaryRestrictions(veganVegetarianGlutenFree).availableToppings(toppingNames)
                .availableSizes(createAllPizzaSizes());
        stores.add(storeTwo);

        Store storeThree = new Store();
        storeThree.id(BRANCH_THREE_ID).branchName(BRANCH_THREE_NAME).address(BRANCH_THREE_ADDRESS)
                .dietaryRestrictions(veganVegetarianGlutenFree).availableToppings(toppingNames)
                .availableSizes(createAllPizzaSizes());
        stores.add(storeThree);

        return stores;
    }

    public static void backfillStoresRepository(StoreRepository storeRepository) {
        storeRepository.insert(createAllStores());
    }

    private static List<Breadstick> createAllBreadsticks() {
        initializeDietaryProperties();
        List<Breadstick> breadsticks = new LinkedList<>();

        Breadstick breadstickSmall = new Breadstick();
        breadstickSmall.name(GARLIC_BREADSTICKS).size(Breadstick.SizeEnum.SMALL).withCheese(false)
                .dietaryProperties(vegetarian).price(SMALL_BREADSTICK_PRICE);
        breadsticks.add(breadstickSmall);

        Breadstick breadstickLarge = new Breadstick();
        breadstickLarge.name(GARLIC_BREADSTICKS).size(Breadstick.SizeEnum.LARGE).withCheese(false)
                .dietaryProperties(vegetarian).price(LARGE_BREADSTICK_PRICE);
        breadsticks.add(breadstickLarge);

        Breadstick cheeseSmall = new Breadstick();
        cheeseSmall.name(CHEESY_BREADSTICKS).size(Breadstick.SizeEnum.SMALL).withCheese(true)
                .dietaryProperties(vegetarian).price(SMALL_CHEESE_BREADSTICK_PRICE);
        breadsticks.add(cheeseSmall);

        Breadstick cheeseLarge = new Breadstick();
        cheeseLarge.name(CHEESY_BREADSTICKS).size(Breadstick.SizeEnum.LARGE).withCheese(true)
                .dietaryProperties(vegetarian).price(LARGE_CHEESE_BREADSTICK_PRICE);
        breadsticks.add(cheeseLarge);

        return breadsticks;
    }

    public static void backfillBreadsticksRepository(BreadstickRepository repository) {
        repository.insert(createAllBreadsticks());
    }

    private static List<Dessert> createAllDesserts() {
        initializeDietaryProperties();
        List<Dessert> desserts = new LinkedList<>();

        Dessert chocolateChipCookies = new Dessert();
        chocolateChipCookies.dessertName(CHOCOLATE_CHIP_COOKIE_NAME).description(CHOCOLATE_CHIP_COOKIE_DESCRIPTION)
                .dietaryProperties(vegetarian).price(CHOCOLATE_CHIP_COOKIE_PRICE);
        desserts.add(chocolateChipCookies);

        Dessert brownies = new Dessert();
        brownies.dessertName(BROWNIE_NAME).description(BROWNIE_DESCRIPTION)
                .dietaryProperties(vegetarian).price(BROWNIE_PRICE);
        desserts.add(brownies);

        return desserts;
    }

    public static void backfillDessertsRepository(DessertRepository dessertRepository) {
        dessertRepository.insert(createAllDesserts());
    }

    private static List<Soda> createAllSodas() {
        initializeDietaryProperties();
        List<Soda> sodas = new LinkedList<>();

        Soda cokeSixPack = new Soda();
        cokeSixPack = cokeSixPack.sodaName(COCA_COLA_PRODUCT_NAME).price(SIX_PACK_PRICE)
                .size(Soda.SizeEnum.SIX_PACK).dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(cokeSixPack);

        Soda cokeTwoLiter = new Soda();
        cokeTwoLiter = cokeTwoLiter.sodaName(COCA_COLA_PRODUCT_NAME).price(TWO_LITER_PRICE)
                .size(Soda.SizeEnum.TWO_LITER).dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(cokeTwoLiter);

        Soda cokeTwentyOunce= new Soda();
        cokeTwentyOunce = cokeTwentyOunce.sodaName(COCA_COLA_PRODUCT_NAME).price(TWENTY_OUNCE_PRICE)
                .size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE).dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(cokeTwentyOunce);

        Soda spriteTwentyOunce= new Soda();
        spriteTwentyOunce = spriteTwentyOunce.sodaName(SPRITE_PRODUCT_NAME).price(TWENTY_OUNCE_PRICE)
                .size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE).dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(spriteTwentyOunce);

        Soda spriteTwoLiter = new Soda();
        spriteTwoLiter = spriteTwoLiter.sodaName(SPRITE_PRODUCT_NAME).price(TWO_LITER_PRICE)
                .size(Soda.SizeEnum.TWO_LITER).dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(spriteTwentyOunce);

        return sodas;
    }

    public static void backfillSodaRepository(SodaRepository repository) {
        repository.insert(createAllSodas());
    }
}
