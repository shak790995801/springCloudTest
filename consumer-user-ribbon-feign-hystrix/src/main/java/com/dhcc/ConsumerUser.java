package com.dhcc;

import com.TestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="PROVIDER-USER",configuration= TestConfiguration.class)
@EnableFeignClients
@EnableCircuitBreaker //开启熔断器
public class ConsumerUser
{
    @Bean
    @LoadBalanced
    public RestTemplate getRest(){
        return  new RestTemplate();
    }
    public static void main( String[] args )
    {
        SpringApplication.run(ConsumerUser.class);
    }
}
