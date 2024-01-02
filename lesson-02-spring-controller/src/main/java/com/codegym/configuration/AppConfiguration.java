package com.codegym.configuration;

import com.codegym.repository.ProductRepository;
import com.codegym.repository.ProductRepositoryImpl;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ProductService productService(){
        return new ProductServiceImpl();
    }

    @Bean
    public ProductRepository productRepository(){
        return new ProductRepositoryImpl();
    }
}
