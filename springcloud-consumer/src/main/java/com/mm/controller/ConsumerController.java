package com.mm.controller;

import com.mm.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private HelloRemote helloRemote;

    @GetMapping("/hello/{name}")
    public String index(@PathVariable("name") String name){
        return helloRemote.hello(name);
    }
}
