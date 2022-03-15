package ru.tigran.cardcollector.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
    private static ObjectMapper converter = new ObjectMapper();

    public static String SerializeObject(Object obj){
        try {
            return converter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T DeserializeObject(String json, Class<T> tClass){
        try {
            return converter.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
