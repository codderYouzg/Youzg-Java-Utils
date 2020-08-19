package edu.youzg.util.proxy;

import java.lang.reflect.Method;

/**
 * 设置 “拦截逻辑”
 *
 * @author Youzg
 */
public interface IMethodInvoker {
    /**
     * 代理对象 调用方法
     *
     * @param object 代理对象
     * @param method 要加强的 调用的方法
     * @param args   传递的参数
     * @return 调用结果
     */
    <T> T methodInvoke(Object object, Method method, Object[] args);
}
