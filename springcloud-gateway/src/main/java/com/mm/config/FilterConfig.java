package com.mm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.cloud.gateway.filter.AdaptCachedBodyGlobalFilter.CACHED_REQUEST_BODY_KEY;

/**
 * ip访问限制
 *
 * @author lwl
 * @date 2019/6/17
 */
@Slf4j
@Configuration
public class FilterConfig {

    private static final List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();

    @Bean
    @Order(1)
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            MediaType contentType = request.getHeaders().getContentType();
            if(request.getHeaders().getContentLength()>0){
                if(MediaType.APPLICATION_JSON.equals(contentType) || MediaType.APPLICATION_JSON_UTF8.equals(contentType)){
                    request.getBody().flatMap(m -> {
                        return ServerRequest.create(exchange, messageReaders)
                                .bodyToMono(String.class)
                                .doOnNext(objectValue -> {
                                    log.info("body :{}", objectValue);
                                }).then(chain.filter(exchange));
                    });
                }
                if(MediaType.APPLICATION_FORM_URLENCODED.equals(contentType)){
                    exchange.getFormData().doOnNext(m -> log.info("formData:{}", m));
                }
            }

            String name = request.getQueryParams().getFirst("name");
            log.info("name:{}", name);
            String ip = request.getRemoteAddress().getAddress().getHostAddress();
            log.info("ip:{}", ip);
            // 此处写死了，实际中需要采取配置的方式
            if (!ip.equals("127.0.0.1")) {
                ServerHttpResponse response = exchange.getResponse();
                String json = "{\"msg\":\"非法请求\",\"code\":401}";
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(response.bufferFactory().wrap(json.getBytes())));
            }
            return chain.filter(exchange);
        };
    }
}
