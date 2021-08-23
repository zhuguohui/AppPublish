package com.zgh.app.data;

import com.google.gson.Gson;


import java.lang.reflect.Type;
import java.util.Base64;

public class DataUtil {
    public static <T> T getData(String str, Type type) throws Exception {
        byte[] encode = Base64.getUrlDecoder().decode(str);
        String beforeStr = new String(encode, "utf-8");
        Gson gson = new Gson();
        System.out.println(beforeStr);
        return gson.fromJson(beforeStr, type);
    }


    public static void   toBase64(Object obj) throws Exception {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj);
        byte[] encode = Base64.getUrlEncoder().encode(jsonStr.getBytes("utf-8"));
        System.out.println(new String(encode));

    }
}
