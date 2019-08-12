package com.dhcc.controller;

import com.dhcc.po.UserPo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user/{id}")
    public UserPo getDate(@PathVariable int id){
        return new UserPo(id);
    }
}
