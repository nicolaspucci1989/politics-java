package com.nicolas.politics.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

public class TestHelper {

    static public <T> List<T> fromJsonToList(String json, Class<T> expectedType) throws JsonProcessingException {
        var type = mapper().getTypeFactory().constructCollectionType(List.class, expectedType);
        return mapper().readValue(json, type);
    }

    static public <T> T fromJson(String json, Class<T> expectedType) throws JsonProcessingException {
        return mapper().readValue(json, expectedType);
    }

    static ObjectMapper mapper() {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper;
    }
}
