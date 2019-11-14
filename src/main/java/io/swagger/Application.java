package io.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.model.Breadstick;
import io.swagger.model.Crust;
import io.swagger.model.Dessert;
import io.swagger.model.PizzaSize;
import io.swagger.model.Sauce;
import io.swagger.model.Soda;
import io.swagger.model.Special;
import io.swagger.model.Store;
import io.swagger.model.Topping;
import io.swagger.repository.BreadstickRepository;
import io.swagger.repository.CrustRepository;
import io.swagger.repository.DessertRepository;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SauceRepository;
import io.swagger.repository.SodaRepository;
import io.swagger.repository.SpecialsRepository;
import io.swagger.repository.StoreRepository;
import io.swagger.repository.ToppingRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"io.swagger", "io.swagger.api", "io.swagger.configuration", "io.swagger.repository"})
public class Application implements CommandLineRunner {

  @Autowired
  private ToppingRepository toppingRepository;

  @Autowired
  private PizzaSizeRepository pizzaSizeRepository;

  @Autowired
  private SpecialsRepository specialsRepository;
  
  @Autowired
  private StoreRepository storeRepository;

  @Autowired
  private BreadstickRepository breadstickRepository;

  @Autowired
  private DessertRepository dessertRepository;

  @Autowired
  private SodaRepository sodaRepository;

  @Autowired
  private SauceRepository sauceRepository;

  @Autowired
  private CrustRepository crustRepository;

  @Override
  public void run(String... arg0) throws Exception {
    if (arg0.length > 0 && arg0[0].equals("exitcode")) {
      throw new ExitException();
    }
    //TODO: Remove the delete all statements before submission.
    toppingRepository.deleteAll();
    Topping.initialize(toppingRepository);
    pizzaSizeRepository.deleteAll();
    PizzaSize.initialize(pizzaSizeRepository);
    storeRepository.deleteAll();
    Store.initialize(storeRepository);
    breadstickRepository.deleteAll();
    Breadstick.initialize(breadstickRepository);
    dessertRepository.deleteAll();
    Dessert.initialize(dessertRepository);
    sodaRepository.deleteAll();
    Soda.initialize(sodaRepository);
    sauceRepository.deleteAll();
    Sauce.initialize(sauceRepository);
    crustRepository.deleteAll();
    Crust.initialize(crustRepository);
    specialsRepository.deleteAll();
    Special.initialize(specialsRepository);

  }

  public static void main(String[] args) throws Exception {
    new SpringApplication(Application.class).run(args);
  }

  class ExitException extends RuntimeException implements ExitCodeGenerator {

    private static final long serialVersionUID = 1L;

    @Override
    public int getExitCode() {
      return 10;
    }
  }
}
