package io.swagger.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.CannotGetMongoDbConnectionException;

@Configuration
public class DatabaseConfiguration {

  @Bean
  public MongoDatabase getDb() throws CannotGetMongoDbConnectionException, MongoException {
    MongoClientURI uri = new MongoClientURI(
        "mongodb://heroku_8pxpg2s0:lir27gp5amlm7fn0eufv3rta8i@ds141248.mlab.com:41248/heroku_8pxpg2s0");
    MongoClient client = new MongoClient(uri);
    MongoDatabase db = client.getDatabase(uri.getDatabase());

    return db;
  }
}
