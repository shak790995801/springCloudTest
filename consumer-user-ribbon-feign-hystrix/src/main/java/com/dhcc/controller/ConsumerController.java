package com.dhcc.controller;

import com.dhcc.feign.FeignClient001;
import com.dhcc.po.UserPo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${user.url}")
    private String url;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private FeignClient001 feignClient001;
    /*@Autowired
    private EurekaClient discoveryClient;*/
    @GetMapping("/order/{id}")
    public UserPo getOrder(@PathVariable int id){
        /*return restTemplate.getForObject(url+id,UserPo.class);*/
        /*InstanceInfo instance = discoveryClient.getNextServerFromEureka("PROVIDER-USER", false);
        String homePageUrl = instance.getHomePageUrl();
        System.out.println("==="+homePageUrl);*/
        return restTemplate.getForObject("http://PROVIDER-USER/user/"+id,UserPo.class);
    }
    @GetMapping("/test")
    public String getTest(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("PROVIDER-USER");
        System.out.println("111111:"+serviceInstance.getServiceId()+"==="+serviceInstance.getHost()+"==="+serviceInstance.getPort());
        return "111111:"+serviceInstance.getServiceId()+"==="+serviceInstance.getHost()+"==="+serviceInstance.getPort();
    }
    @GetMapping("/test00/{id}")
    @HystrixCommand(fallbackMethod = "fallMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
    })
    public UserPo getFeign(@PathVariable("id") int id){
        return feignClient001.getUser(id);
    }
    public UserPo fallMethod(int id){
        return new UserPo(12);
    }
}
