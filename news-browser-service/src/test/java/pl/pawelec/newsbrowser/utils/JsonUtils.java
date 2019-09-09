package pl.pawelec.newsbrowser.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Object> parseToMap(String jsonString) {
        Map<String, Object> jsonMap = new HashMap<>();
        try{
            jsonMap = OBJECT_MAPPER.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return jsonMap;
    }
}
