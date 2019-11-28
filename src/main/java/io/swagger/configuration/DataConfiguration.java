package io.swagger.configuration;


import io.swagger.model.*;
import io.swagger.repository.*;

import java.util.*;

public class DataConfiguration {

    private static final Double NON_MEAT_PRICE = new Double(2.00);
    private static final Double MEAT_PRICE = new Double(3.00);

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

    private static void initializeDietaryProperties() {
        notAnything();
        notVegetarianIsGlutenFree();
        vegetarianGlutenFree();
        vegetarian();
        veganVegetarian();
        veganVegetarianGlutenFree();
    }

    public static void backfillToppingsRepository(ToppingRepository toppingRepository) {
        toppingRepository.deleteAll();
        List<Topping> allToppings = createAllToppings();
        toppingRepository.insert(allToppings);
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
        List<PizzaSize> pizzaSizes = createAllPizzaSizes();
        pizzaSizeRepository.insert(pizzaSizes);
    }

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

    private static final String CHOCOLATE_CHIP_COOKIE_NAME = "chocolate chip cookies";
    private static final String CHOCOLATE_CHIP_COOKIE_DESCRIPTION =
            "six large chocolate chip cookies baked fresh in our ovens.";
    private static final Double CHOCOLATE_CHIP_COOKIE_PRICE = new Double(3.99);

    private static final String BROWNIE_NAME = "double chocolate chunk brownies";
    private static final String BROWNIE_DESCRIPTION = "Four large gooey double chocolate brownies.";
    private static final Double BROWNIE_PRICE = new Double(4.99);

    private static final String COCA_COLA_PRODUCT_NAME = "coca cola";
    private static final String SPRITE_PRODUCT_NAME = "sprite";

    private static final String SAUCE_ORIGINAL_NAME = "original";
    private static final String SAUCE_ROBUST_NAME = "robust italian";

    private static final String CRUST_ORIGINAL_NAME = "original crust";
    private static final String CRUST_THIN_NAME = "thin crust";
    private static final String CRUST_GLUTEN_FREE_NAME = "gluten free crust";
    private static final Double CRUST_ORIGINAL_PRICE = new Double(0);
    private static final Double CRUST_THIN_PRICE = new Double(0);
    private static final Double CRUST_GLUTEN_FREE_PRICE = new Double(1.5);
    private static List<PizzaSize> ALL_SIZES = new ArrayList<>();
    private static List<PizzaSize> SMALL_LARGE_SIZE = new ArrayList<>();

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
                .dietaryProperties(notVegetarianIsGlutenFree);
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
        breadstickSmall.size(Breadstick.SizeEnum.SMALL).withCheese(false)
                .dietaryProperties(vegetarian);
        breadsticks.add(breadstickSmall);

        Breadstick breadstickLarge = new Breadstick();
        breadstickLarge.size(Breadstick.SizeEnum.LARGE).withCheese(false)
                .dietaryProperties(vegetarian);
        breadsticks.add(breadstickLarge);

        Breadstick cheeseSmall = new Breadstick();
        cheeseSmall.size(Breadstick.SizeEnum.SMALL).withCheese(true)
                .dietaryProperties(vegetarian);
        breadsticks.add(cheeseSmall);

        Breadstick cheeseLarge = new Breadstick();
        cheeseLarge.size(Breadstick.SizeEnum.LARGE).withCheese(true)
                .dietaryProperties(vegetarian);
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
        chocolateChipCookies.dessertName(CHOCOLATE_CHIP_COOKIE_NAME)
                .description(CHOCOLATE_CHIP_COOKIE_DESCRIPTION)
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
        cokeSixPack = cokeSixPack.sodaName(COCA_COLA_PRODUCT_NAME)
                .size(Soda.SizeEnum.SIX_PACK)
                .dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(cokeSixPack);

        Soda cokeTwoLiter = new Soda();
        cokeTwoLiter = cokeTwoLiter.sodaName(COCA_COLA_PRODUCT_NAME)
                .size(Soda.SizeEnum.TWO_LITER)
                .dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(cokeTwoLiter);

        Soda cokeTwentyOunce = new Soda();
        cokeTwentyOunce = cokeTwentyOunce.sodaName(COCA_COLA_PRODUCT_NAME)
                .size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE)
                .dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(cokeTwentyOunce);

        Soda spriteTwentyOunce = new Soda();
        spriteTwentyOunce = spriteTwentyOunce.sodaName(SPRITE_PRODUCT_NAME)
                .size(Soda.SizeEnum.TWENTY_OUNCE_BOTTLE)
                .dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(spriteTwentyOunce);

        Soda spriteTwoLiter = new Soda();
        spriteTwoLiter = spriteTwoLiter.sodaName(SPRITE_PRODUCT_NAME)
                .size(Soda.SizeEnum.TWO_LITER)
                .dietaryProperties(veganVegetarianGlutenFree);
        sodas.add(spriteTwoLiter);

        return sodas;
    }

    public static void backfillSodaRepository(SodaRepository repository) {
        repository.insert(createAllSodas());
    }

    private static List<Sauce> createAllSauces() {
        initializeDietaryProperties();
        List<Sauce> sauces = new LinkedList<>();

        Sauce sauceOriginal = new Sauce();
        sauceOriginal.sauceName(SAUCE_ORIGINAL_NAME)
                .dietaryProperties(veganVegetarianGlutenFree);
        sauces.add(sauceOriginal);

        Sauce sauceRobust = new Sauce();
        sauceRobust.sauceName(SAUCE_ROBUST_NAME)
                .dietaryProperties(veganVegetarianGlutenFree);
        sauces.add(sauceRobust);

        return sauces;
    }

    public static void backfillSauceRepository(SauceRepository sauceRepository) {
        sauceRepository.insert(createAllSauces());
    }

    private static void initializeAvailableSizes() {
        PizzaSize pizzaSizeSmall = new PizzaSize(SMALL_DESCRIPTION, SMALL_SIZE_INCHES);
        SMALL_LARGE_SIZE.add(pizzaSizeSmall);
        ALL_SIZES.add(pizzaSizeSmall);

        PizzaSize pizzaSizeMedium = new PizzaSize(MEDIUM_DESCRIPTION, MEDIUM_SIZE_INCHES);
        ALL_SIZES.add(pizzaSizeMedium);

        PizzaSize pizzaSizeLarge = new PizzaSize(LARGE_DESCRIPTION, LARGE_SIZE_INCHES);
        SMALL_LARGE_SIZE.add(pizzaSizeLarge);
        ALL_SIZES.add(pizzaSizeLarge);
    }

    private static List<Crust> createAllCrusts() {
        initializeDietaryProperties();
        initializeAvailableSizes();
        List<Crust> crusts = new LinkedList<>();

        Crust crustOriginal = new Crust();
        crustOriginal.crustName(CRUST_ORIGINAL_NAME).availableSizes(ALL_SIZES)
                .price(CRUST_ORIGINAL_PRICE).dietaryProperties(vegetarian);
        crusts.add(crustOriginal);

        Crust crustThin = new Crust();
        crustThin.crustName(CRUST_THIN_NAME).availableSizes(ALL_SIZES)
                .price(CRUST_THIN_PRICE).dietaryProperties(vegetarian);
        crusts.add(crustThin);

        Crust crustGlutenFree = new Crust();
        crustGlutenFree.crustName(CRUST_GLUTEN_FREE_NAME).availableSizes(SMALL_LARGE_SIZE)
                .price(CRUST_GLUTEN_FREE_PRICE).dietaryProperties(veganVegetarianGlutenFree);
        crusts.add(crustGlutenFree);
        return crusts;
    }

    public static void backfillCrustRepository(CrustRepository crustRepository) {
        crustRepository.insert(createAllCrusts());
    }

    public static void backfillSpecialsRepository(SpecialsRepository repository) {
        Special freeItem = new Special().specialId("OneFree").description("Get one item free. The cost"
                + " of the cheapest item will be discounted from the order.");
        repository.insert(freeItem);
        Special buyOneGetOne = new Special().specialId("BOGO").description("Purchase two or more items"
                + " and receive the cheapest item free!");
        repository.insert(buyOneGetOne);
        Special flatDiscount = new Special().specialId("FlatDiscount").description("Receive $20"
                + " off your order of $50 or more.");
        repository.insert(flatDiscount);
    }

    private static List<Pizza> createAllPizzas() {
        initializeDietaryProperties();
        initializeAvailableSizes();

        Topping pepperoniTopping = new Topping();
        pepperoniTopping.toppingName("pepperoni").price(MEAT_PRICE)
                .dietaryProperties(notVegetarianIsGlutenFree);
        Topping shreddedProvoloneCheese = new Topping();
        shreddedProvoloneCheese.toppingName("shredded provolone cheese").price(NON_MEAT_PRICE)
                .dietaryProperties(vegetarianGlutenFree);
        Topping shreddedParmesan = new Topping();
        shreddedParmesan.toppingName("shredded parmesan asiago").price(NON_MEAT_PRICE)
                .dietaryProperties(vegetarianGlutenFree);
        Topping jalapenoPeppers = new Topping();
        jalapenoPeppers.toppingName("jalapeno peppers").price(NON_MEAT_PRICE)
                .dietaryProperties(veganVegetarianGlutenFree);
        Topping onions = new Topping();
        onions.toppingName("onions").price(NON_MEAT_PRICE)
                .dietaryProperties(veganVegetarianGlutenFree);
        Topping bananaPeppers = new Topping();
        bananaPeppers.toppingName("banana peppers").price(NON_MEAT_PRICE)
                .dietaryProperties(veganVegetarianGlutenFree);

        Crust crustOriginal = new Crust();
        crustOriginal.crustName(CRUST_ORIGINAL_NAME).availableSizes(ALL_SIZES)
                .price(CRUST_ORIGINAL_PRICE).dietaryProperties(vegetarian);
        Crust crustGlutenFree = new Crust();
        crustGlutenFree.crustName(CRUST_GLUTEN_FREE_NAME).availableSizes(SMALL_LARGE_SIZE)
                .price(CRUST_GLUTEN_FREE_PRICE).dietaryProperties(veganVegetarianGlutenFree);

        Sauce sauceOriginal = new Sauce();
        sauceOriginal.sauceName(SAUCE_ORIGINAL_NAME)
                .dietaryProperties(veganVegetarianGlutenFree);

        PizzaSize pizzaSizeLarge = new PizzaSize(LARGE_DESCRIPTION, LARGE_SIZE_INCHES);

        Pizza pepperoni = new Pizza();
        Pizza cheese = new Pizza();
        Pizza vegetarian = new Pizza();

        pepperoni.pizzaName("pepperoni").crust(crustOriginal).sauce(sauceOriginal)
                .toppings(Arrays.asList(pepperoniTopping, shreddedProvoloneCheese))
                .size(pizzaSizeLarge);
        cheese.pizzaName("cheese").crust(crustOriginal).sauce(sauceOriginal)
                .toppings(Arrays.asList(shreddedProvoloneCheese, shreddedParmesan))
                .size(pizzaSizeLarge);
        vegetarian.pizzaName("vegetarian").crust(crustGlutenFree).sauce(sauceOriginal)
                .toppings(Arrays.asList(shreddedParmesan, bananaPeppers, onions, jalapenoPeppers))
                .size(pizzaSizeLarge);

        return Arrays.asList(pepperoni, cheese, vegetarian);
    }

    public static void backfillPizzaRepository(PizzaRepository repository) {
        repository.insert(createAllPizzas());
    }
}
