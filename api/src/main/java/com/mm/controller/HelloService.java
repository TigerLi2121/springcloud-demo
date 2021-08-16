package com.mm.controller;

import com.mm.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * hello
 * @author lwl
 */
public interface HelloService {

    @GetMapping("/hello1")
    String hello1(@RequestParam("name") String name);

    @GetMapping("/hello2/{name}")
    String hello2(@PathVariable("name") String name);

    @PostMapping("/hello3")
    User hello3(@RequestBody User user);
}
