package com.market.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.val;

@Component
public class WebClientUtil {

    @Autowired
    protected ObjectMapper objectMapper;

    public MultiValueMap toQueryParams(Object obj) {
        MultiValueMap params = new LinkedMultiValueMap();
        Map<String, Object> objMap = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});

        for (val field : objMap.entrySet()) {
            if (field.getValue() instanceof Iterable) {
                for (val v : ((Iterable) field.getValue())) {
                    params.add(field.getKey(), v.toString());
                }
            } else {
                params.add(field.getKey(), objectMapper.convertValue(field.getValue(), String.class));
            }
        }
        return params;
    }
}
