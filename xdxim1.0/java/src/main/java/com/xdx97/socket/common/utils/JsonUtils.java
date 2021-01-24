package com.xdx97.socket.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json 相关格式化
 */
public class JsonUtils {

    // 定义jackson对象
    private static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(Include.ALWAYS);
    }

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        String string = null;
        try {
            string = writeValueAsString(data);
        } catch (Exception e) {
            // TODO use 'log' for logging
        }
        return string;
    }

    public static JsonNode stringToJsonNode(String data) {
        JsonNode jsonNode = null;
        try {
            jsonNode = readTree(data);
        } catch (Exception e) {
            // TODO use 'log' for logging
        }
        return jsonNode;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param clazz    对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T objectToPojo(Object jsonData, Class<T> beanType) {
        String string = null;
        try {
            string = writeValueAsString(jsonData);
        } catch (Exception e) {
            // TODO use 'log' for logging
        }
        return jsonToPojo(string, beanType);
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param clazz    对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        T t = null;
        try {
            t = readValue(jsonData, beanType);
        } catch (Exception e) {
            // TODO use 'log' for logging
        }
        return t;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        List<T> list = null;
        try {
            list =  readValueList(jsonData, beanType);
        } catch (Exception e) {
            // TODO use 'log' for logging
        }
        return list;
    }

    /**
     * 将object数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> objectToList(Object jsonData, Class<T> beanType) {
        String string = null;
        try {
            string = writeValueAsString(jsonData);
        } catch (Exception e) {
            // TODO use 'log' for logging
        }
        return jsonToList(string, beanType);
    }

    /**
     * 将JSON数据转换成Map
     *
     * @param jsonData
     * @return
     */
    public static Map<String, Object> jsonToMap(String jsonData) {
        return jsonToPojo(jsonData, Map.class);
    }

    private static String writeValueAsString(Object value) throws Exception {
        return MAPPER.writeValueAsString(value);
    }

    private static JsonNode readTree(String value) throws Exception {
        return MAPPER.readTree(value);
    }

    private static <T> T readValue(String jsonData, Class<T> beanType) throws Exception {
        return MAPPER.readValue(jsonData, beanType);
    }

    private static <T> List<T> readValueList(String jsonData, Class<T> beanType) throws Exception {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        return MAPPER.readValue(writeValueAsString(jsonData), javaType);
    }

}
