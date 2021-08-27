package com.mm.remote.service;

import com.mm.remote.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * hello
 *
 * @author lwl
 */
@FeignClient(name = "producer", path = "/producer/hello")
public interface HelloRemoteService {

    @GetMapping("/hello1")
    String hello1(@RequestParam("name") String name);

    @GetMapping("/hello2/{name}")
    String hello2(@PathVariable("name") String name);

    @PostMapping("/hello3")
    UserDto hello3(@RequestBody UserDto userDto);
}
