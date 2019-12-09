package com.myshop.product;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
    	return new Docket(DocumentationType.SWAGGER_2)
    			.select()
    			.apis(RequestHandlerSelectors.basePackage("com.myshop"))
    			.build()
    			.apiInfo(apiDetails());
    }
    
    private ApiInfo apiDetails() {
    	return new ApiInfo(
    			"My Shop - Products",
    			"Creating this basic api for assignment",
    			"1.0",
    			"Free to Use",
    			new Contact("User","https://www.google.com/","abc@xyz.com"),
    			"API License",
    			"https://www.google.com/",
    			Collections.emptyList());
    }
}
