package com.app.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class MapHelper {

    private MapHelper() {}

    private static ObjectMapper mapper = null;

    public static Map<String, Object> convertObject(Object obj) {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        return mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }
}
