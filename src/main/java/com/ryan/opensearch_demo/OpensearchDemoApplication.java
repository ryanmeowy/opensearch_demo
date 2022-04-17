package com.ryan.opensearch_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ryan
 */
@SpringBootApplication
@EnableConfigurationProperties
public class OpensearchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpensearchDemoApplication.class, args);
    }

}
