package io.swagger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration"})
public class Application implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        List<Document> seedData = new ArrayList<Document>();

        seedData.add(new Document("Topping", "Pepperoni")
            .append("Vegan", "False")
            .append("GlutenFree", "False")
            .append("Vegetarian", "False")
            .append("price", 3)
        );

        seedData.add(new Document("Topping", "Hot Sauce")
            .append("Vegan", "True")
            .append("GlutenFree", "True")
            .append("Vegetarian", "True")
            .append("price", 2)
        );

        MongoClientURI uri = new MongoClientURI(
            "mongodb://heroku_8pxpg2s0:lir27gp5amlm7fn0eufv3rta8i@ds141248.mlab.com:41248/heroku_8pxpg2s0");
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());

        db.getCollection("toppings").drop();

        MongoCollection<Document> toppings = db.getCollection("toppings");
        toppings.insertMany(seedData);

        MongoCursor<Document> cursor = toppings.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(
                    "ToppingName:  " + doc.get("Topping") + ", Price: " + doc.get("price")
                );
            }
        } finally {
            cursor.close();
        }
        client.close();
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
