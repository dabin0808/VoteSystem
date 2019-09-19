package xyz.peter666.util;

import com.google.gson.Gson;

public class JsonUtils {

    public static  String toJson(Object obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
    public static Object toObject(String json,Class entityClass){
        Gson gson = new Gson();
        Object obj = gson.fromJson(json, entityClass);

        return  obj;
    }
}
