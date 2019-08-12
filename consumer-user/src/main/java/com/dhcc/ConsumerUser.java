package com.dhcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerUser
{
    @Bean
    public RestTemplate getRest(){
        return  new RestTemplate();
    }
    public static void main( String[] args )
    {
        SpringApplication.run(ConsumerUser.class);
    }
}
