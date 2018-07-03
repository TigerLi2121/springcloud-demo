package com.mm.service;

import com.mm.api.HelloServiceRemoteApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("springcloud-producer")
public interface HelloRemoteService extends HelloServiceRemoteApi {

}
