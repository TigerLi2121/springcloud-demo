package com.mm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index(@RequestParam String name){
        return "hello "+ name;
    }

    @GetMapping("/hello2/{name}")
    public String index2(@PathVariable("name") String name){
        return  "hello2: "+ name;
    }

    @GetMapping("/user")
    public String user(UserEntity userEntity){
        return  userEntity.toString();
    }
}
