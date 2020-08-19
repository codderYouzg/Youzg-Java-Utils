package edu.youzg.util.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理对象 建造者
 */
public class ProxyBuilder {
	public static final int JDK_PROXY = 0;
	public static final int CGLIB_PROXY = 1;

	private IMethodInvoker methodInvoker;
	private int proxyType = JDK_PROXY;

	public ProxyBuilder() {
	}

	public ProxyBuilder(int proxyType) {
		if (proxyType != CGLIB_PROXY) {    // 防止 使用者胡乱使用
			proxyType = JDK_PROXY;
		}
		this.proxyType = proxyType;
	}

	public void setMethodInvoker(IMethodInvoker methodInvoker) {
		this.methodInvoker = methodInvoker;
	}

	public void setProxyType(int proxyType) {
		this.proxyType = proxyType;
	}

	public <T> T creatProxy(T object) {
		return creatProxy(object.getClass());
	}

	@SuppressWarnings("unchecked")
	public <T> T creatProxy(Class<?> klass) {
		if (klass.isInterface()) {
			return creatJdkProxy(klass, null);
		}

		if (this.proxyType == JDK_PROXY) {
			try {
				Object target = klass.newInstance();
				return (T) creatJdkProxy(klass, target);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return creatCglibProxy(klass);
	}

	/**
	 * 获取cglib代理对象
	 */
	@SuppressWarnings("unchecked")
	private <T> T creatCglibProxy(Class<?> klass) {
		try {
			T target = (T) klass.newInstance();
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(target.getClass());

			enhancer.setCallback(new MethodInterceptor() {

				@Override
				public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
					return invokeMethod(method, target, args);
				}
			});

			return (T) enhancer.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取jdk代理对象
	 *
	 * @param klass  目标对象 对应的 Class对象
	 * @param target 目标对象
	 * @param <T>    目标对象的类型
	 * @return 目标对象的 jdk代理对象
	 */
	@SuppressWarnings("unchecked")
	private <T> T creatJdkProxy(Class<?> klass, T target) {
		ClassLoader classLoader = klass.getClassLoader();
		Class<?>[] interfaces = klass.getInterfaces();
		if (klass.isInterface()) {
			interfaces = new Class<?>[]{klass};
		}
		Object proxy = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return invokeMethod(method, target, args);
			}
		});

		return (T) proxy;
	}

	/**
	 * 执行 被代理对象 的原方法
	 *
	 * @param method 被代理对象 的原方法
	 * @param target 被代理的 对象
	 * @param args   被代理对象 执行原方法 的参数
	 * @return 被代理对象 的原方法 的执行结果
	 * @throws Throwable
	 */
	private Object invokeMethod(Method method, Object target, Object[] args) throws Exception {
		if (methodInvoker == null) {
			return method.invoke(target, args);
		}
		return methodInvoker.methodInvoke(target, method, args);
	}

}