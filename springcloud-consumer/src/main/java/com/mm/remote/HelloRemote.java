package com.mm.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-cloud-producer")
public interface HelloRemote {
    @GetMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);

    @GetMapping("/hello2/{name}")
    String hello2(@PathVariable("name") String name);
}
