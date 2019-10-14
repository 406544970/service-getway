package com.lh.servicegetway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceGetwayApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ServiceGetwayApplication.class, args);
        new SpringApplicationBuilder(ServiceGetwayApplication.class).web(WebApplicationType.SERVLET).run(args);
        System.out.println("http://localhost:2005/swagger-ui.html");

    }

}
