package com.dhcc.controller;

import com.dhcc.po.UserPo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${user.url}")
    private String url;
    @Autowired
    private EurekaClient discoveryClient;
    @GetMapping("/order/{id}")
    public UserPo getOrder(@PathVariable int id){
        /*return restTemplate.getForObject(url+id,UserPo.class);*/
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("PROVIDER-USER", false);
        String homePageUrl = instance.getHomePageUrl();
        System.out.println("==="+homePageUrl);
        return restTemplate.getForObject(homePageUrl+"/user/"+id,UserPo.class);
    }
}
