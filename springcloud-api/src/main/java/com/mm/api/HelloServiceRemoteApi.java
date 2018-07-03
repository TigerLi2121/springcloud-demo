package com.mm.api;

import com.mm.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author lwl
 * @date 2018/7/3
 */
@RequestMapping("/producer-remote")
public interface HelloServiceRemoteApi {

    @GetMapping("/hello1")
    String hello1(@RequestParam("name") String name);

    @GetMapping("/hello2/{name}")
    String hello2(@PathVariable("name") String name);

    @PostMapping("/hello3")
    User hello3(@RequestBody User user);
}
