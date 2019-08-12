package com.dhcc.feign;

import com.dhcc.po.UserPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("PROVIDER-USER")
public interface FeignClient001 {

    @RequestMapping(method = RequestMethod.GET,value = "/user/{id}")
    public UserPo getUser(@PathVariable("id") int id);
}
