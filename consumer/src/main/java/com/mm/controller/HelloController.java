package com.mm.controller;

import com.mm.remote.dto.UserDto;
import com.mm.remote.service.HelloRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * hello
 *
 * @author lwl
 */
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

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
    public UserDto hello3(@RequestBody UserDto userDto) {
        log.info("user user:{}", userDto);
        return helloRemoteService.hello3(userDto);
    }
}
