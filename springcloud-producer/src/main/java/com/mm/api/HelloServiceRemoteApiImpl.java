package com.mm.api;

import com.mm.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwl
 * @date 2018/7/3
 */
@Slf4j
@RestController
public class HelloServiceRemoteApiImpl implements HelloServiceRemoteApi {
    @Override
    public String hello1(@RequestParam("name") String name) {
        return name;
    }

    @Override
    public String hello2(@PathVariable("name") String name) {
        return name;
    }

    @Override
    public User hello3(@RequestBody User user) {
        log.info("hello3 user:{}", user);
        return user;
    }
}
