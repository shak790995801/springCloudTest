package com.dhcc.controller;

import com.dhcc.feign.FeignClient001;
import com.dhcc.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private FeignClient001 feignClient001;
    @GetMapping("/order/{id}")
    public UserPo getUser(@PathVariable("id") int id){
        return feignClient001.getUser(id);
    }
}
