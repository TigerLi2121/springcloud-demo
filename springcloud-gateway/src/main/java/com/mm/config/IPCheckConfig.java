package com.mm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ip访问限制
 *
 * @author lwl
 * @date 2019/6/17
 */
@Slf4j
@Configuration
public class IPCheckConfig {

    @Bean
    @Order(0)
    public GlobalFilter b() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            Flux<DataBuffer> body = request.getBody();
            log.info("body flux:{}", body);
            AtomicReference<String> bodyRef = new AtomicReference<>();
            body.subscribe(buffer -> {
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
                DataBufferUtils.release(buffer);
                bodyRef.set(charBuffer.toString());
            });
            log.info("body:{}", bodyRef.get());

            MultiValueMap<String, String> params = request.getQueryParams();
            log.info("params:{}", params);
            String ip = request.getRemoteAddress().getAddress().getHostAddress();
            log.info("ip:{}", ip);
            // 此处写死了，演示用，实际中需要采取配置的方式
            if (getIp(request.getHeaders()).equals("127.0.0.1")) {
                ServerHttpResponse response = exchange.getResponse();
                String json = "{\"msg\":\"非法请求\",\"code\":401}";
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(response.bufferFactory().wrap(json.getBytes())));
            }
            return chain.filter(exchange);
        };
    }

    private String getIp(HttpHeaders headers) {
        return "127.0.0.1";
    }
}
