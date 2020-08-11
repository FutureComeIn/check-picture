package com.company.singleton;

/**
 * 饿汉式单例：类创建的同时创建一个静态的对象供系统使用，且final不再改变，所以天生是线程安全
 */
public class Singleton1 {

    private Singleton1() {}

    private static final Singleton1 single = new Singleton1();

    //静态工厂方法
    public static Singleton1 getInstance() {
        return single;
    }
}
