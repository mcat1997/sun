package com.sun.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.entity.Answer;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public final class JsonUtil {
    private static ObjectMapper jsonMapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static String toJson(Object object) {
        String jsonResult = "";

        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonMapper.setDateFormat(smt);
        jsonMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));//解决时区差8小时问题
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            jsonResult = jsonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return jsonResult;
        }
        return jsonResult;
    }

    public static Object fromJson(String json, Object object) {
        Object objectResult = new Object();
        try {
            objectResult = jsonMapper.readValue(json, object.getClass());
        } catch (IOException e1) {
            return objectResult;
        }
        return objectResult;
    }

    public static Object fromJson2(String json) {
//        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Object objectResult = new Gson().fromJson(json, new TypeToken<List<Answer>>() {}.getType());
        return objectResult;
    }
}
