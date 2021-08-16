package com.mm.service;

import com.mm.controller.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xmly
 */
@FeignClient(name = "producer", path = "/producer")
public interface HelloRemoteService extends HelloService {

}
