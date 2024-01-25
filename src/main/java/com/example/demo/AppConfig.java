package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class AppConfig {

  /*
   * Use the standard Mongo driver API to create a com.mongodb.client.MongoClient instance.
   */
   public @Bean com.mongodb.client.MongoClient mongoClient() {
       return com.mongodb.client.MongoClients.create("mongodb+srv://kaloyangeorgieev:I9YsUcjOTsHszxMA@cluster0.g2hqvx0.mongodb.net/?retryWrites=true&w=majority");
   }
}