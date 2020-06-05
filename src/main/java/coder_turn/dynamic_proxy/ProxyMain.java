package coder_turn.dynamic_proxy;

import java.lang.reflect.Proxy;

public class ProxyMain {
    private static IHelloWorld hw = new HelloWorld();
    private static LoggerHandler handler = new LoggerHandler(hw);
    private static IHelloWorld proxy = (IHelloWorld) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                                                                            hw.getClass().getInterfaces(), handler);
    public static void main(String[] args) {
        proxy.sayHello();
    }
}