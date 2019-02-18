package com.finance.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZXY
 * @ClassName: JsonUtils
 * @Description:
 * @date 2017/6/29 13:45
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new JsonMapper();

    /**
     * @param json
     * @param clazz 对象的 class
     * @return 对象
     * @throws IOException
     * @Description: json 转换成对象
     */
    public static <T> T readValue(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * @param json
     * @param name 属性名称
     * @return 属性值
     * @throws IOException
     * @Description: 获取json 的属性值
     */
    public static String readValueByName(String json, String name) throws IOException {
        Map<?, ?> map = objectMapper.readValue(json, Map.class);
        return map.get(name).toString();
    }

    /**
     * @param json
     * @return map
     * @throws IOException
     * @Description: 获取json 的属性 map
     */
    public static Map<?, ?> readMap(String json) throws IOException {
        Map<?, ?> map = objectMapper.readValue(json, Map.class);
        return map;
    }

    /**
     * @param obj 对象
     * @return json
     * @throws IOException
     * @Description: 对象转换成json
     */
    public static String translateToJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * @param json
     * @param type
     * @return
     * @throws IOException
     * @Description: json 转换成对象
     */
    public static <T> T readValueByType(String json, TypeReference<?> type) throws IOException {
        return objectMapper.readValue(json, type);
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String, Object> json2map(String jsonStr)
            throws Exception {
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
            throws Exception {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr,
                new TypeReference<Map<String, T>>() {
                });
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
            throws Exception {
        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr,
                new TypeReference<List<T>>() {
                });
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static String map2json(Map map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }

}
