package com.mm.controller;

import com.mm.pojo.User;
import com.mm.service.HelloRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * consumer
 *
 * @author lwlø
 */
@Slf4j
@RestController
public class ConsumerController {

    @Resource
    private HelloRemoteService helloRemoteService;

    @GetMapping("/hello1")
    public String hello1(@RequestParam("name") String name) {
        return helloRemoteService.hello1(name);
    }

    @GetMapping("/hello2/{name}")
    public String hello2(@PathVariable("name") String name) {
        return helloRemoteService.hello2(name);
    }

    @PostMapping("/hello3")
    public User hello3(@RequestBody User user) {
        log.info("user user:{}", user);
        return helloRemoteService.hello3(user);
    }
}
