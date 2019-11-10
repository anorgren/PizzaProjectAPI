package io.swagger;

import io.swagger.model.PizzaSize;
import io.swagger.model.Special;
import io.swagger.model.Topping;
import io.swagger.repository.PizzaSizeRepository;
import io.swagger.repository.SpecialsRepository;
import io.swagger.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"io.swagger", "io.swagger.api", "io.swagger.configuration"})
public class Application implements CommandLineRunner {

  @Autowired
  private ToppingRepository toppingRepository;

  @Autowired
  private PizzaSizeRepository pizzaSizeRepository;

  @Autowired
  private SpecialsRepository specialsRepository;

  @Override
  public void run(String... arg0) throws Exception {
    if (arg0.length > 0 && arg0[0].equals("exitcode")) {
      throw new ExitException();
    }
    Topping.initialize(toppingRepository);
    PizzaSize.initialize(pizzaSizeRepository);
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
