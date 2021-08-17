package com.mm.controller;

import com.mm.dto.UserDto;
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
    public UserDto hello3(@RequestBody UserDto userDto) {
        log.info("hello3 user:{}", userDto);
        return userDto;
    }
}
