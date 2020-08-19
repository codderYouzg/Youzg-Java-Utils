package edu.youzg.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数存取池(用于“网络传输”)
 * @author Youzg
 * @Description: 1. 优化采用fastjson 和 Gson 结合使用，<br/>2. 提高运行效率，<br/>3. 解决了Gson无法转换void的情况，<br/>4. 解决了fastjson的集合无法转换为值的问题
 */
public class ArgumentMaker {
    private static final TypeReference<Map<String, String>> type = new TypeReference<Map<String, String>>() {}; // 防止“泛型擦除机制”
    public static final Gson gson = new GsonBuilder().create();
    
    private Map<String, String> argPool;

    public ArgumentMaker() {
        argPool = new HashMap<String, String>();
    }

    /**
     * 将json字符串，转换回 “参数pool”
     * @param json 目标字符串
     */
    public ArgumentMaker(String json) {
        argPool = JSON.parseObject(json, type);
    }

    public Object getValue(String name, Class<?> type) {
    	String valueJson = argPool.get(name);
		return gson.fromJson(valueJson, type);
    }

    public Object getValue(String name, Type type) {
    	String valueJson = argPool.get(name);
		return gson.fromJson(valueJson, type);
    }

    /**
     * 向 “参数pool” 中 存储 参数的json字符串
     * @param name 目标参数的名称
     * @param value 目标参数的值
     * @return 当前对象，方便链式调用
     */
    public ArgumentMaker add(String name, Object value) {
        argPool.put(name, JSON.toJSONString(value));
        return this;
    }

    /**
     * 将每个参数都转换为相应的json，存储在一个map中，
     * 再将该map转换为json
     * @return 当前 参数pool的 json字符串
     */
    @Override
    public String toString() {
        return JSON.toJSONString(argPool);
    }

}
