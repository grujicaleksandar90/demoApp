package com.example.demo.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import repositories.impl.DbRepositoryImpl;

@Configuration
public class DemoConfig {

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public DbRepositoryImpl getDbRepositoryImpl(RestTemplate restClient) {
    return new DbRepositoryImpl(restClient);
  }
}