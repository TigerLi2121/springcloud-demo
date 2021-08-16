package com.mm.controller;

import com.mm.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * hello
 *
 * @author lwl
 */
@Slf4j
@RestController
public class ProducerController {

    @RequestMapping("/hello1")
    public String hello1(@RequestParam("name") String name) {
        return name;
    }

    @RequestMapping("/hello2")
    public String hello2(@PathVariable("name") String name) {
        return name;
    }

    @RequestMapping("/hello3")
    public User hello3(@RequestBody User user) {
        log.info("hello3 user:{}", user);
        return user;
    }
}
