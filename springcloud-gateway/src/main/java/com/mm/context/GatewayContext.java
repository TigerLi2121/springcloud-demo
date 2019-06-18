package com.mm.context;

import lombok.Data;
import org.springframework.util.MultiValueMap;


@Data
public class GatewayContext {

    /**
     * cache json body
     */
    private String requestBody;
    /**
     * cache form data
     */
    private MultiValueMap<String, String> formData;

}