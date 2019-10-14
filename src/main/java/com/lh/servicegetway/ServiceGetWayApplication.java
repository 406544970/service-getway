package com.lh.servicegetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
public class ServiceGetWayApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ServiceGetWayApplication.class, args);
        new SpringApplicationBuilder(ServiceGetWayApplication.class).web(WebApplicationType.REACTIVE).run(args);
        System.out.println("http://localhost:2005/swagger-ui.html");

    }

}
