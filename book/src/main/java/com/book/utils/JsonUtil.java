package com.book.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.book.model.Remember;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

public class JsonUtil {

    //1、读取本地json文件
    //获取json
    public static String getJson() {
        String jsonStr = "";
        try {
            File file =  ResourceUtils.getFile("classpath:words.json");
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
            } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String json = getJson();
        Object parse = JSONObject.parse(json);
        JSONArray objects = JSONArray.parseArray(json);

        List<Remember> list = JSONObject.parseArray(json,  Remember.class);


    }
}
