package com.company.singleton;

/**
 * 懒汉式单例
 */
public class Singleton {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }




    private static Singleton single=null;

    //静态工厂方法
    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }

    /** 限定为private避免类在外部new ，唯一实例只能通过getInstance()方法访问
     *  事实上，通过Java反射机制是能够实例化构造方法为private的类的，那基本上会使所有的Java单例实现失效。此问题在此处不做讨论
     *  问题：线程不安全,并发环境下很可能出现多个Singleton实例
     *  解决办法：对getInstance改造，保证懒汉式单例的线程安全（3种改造方式如下）
    */
    private Singleton() {}

    /**
     * 1.在getInstance方法上加同步
     */
    public static synchronized Singleton getInstance2() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }

    /**
     * 2.双重检查锁定
     */
    public static Singleton getInstance3() {
        if (single == null) {
            synchronized (Singleton.class) {
                if (single == null) {
                    single = new Singleton();
                }
            }
        }
        return single;
    }

    /**
     * 3. 静态内部类

    private static class LazyHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton (){}
    public static final Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
     */

    public void sys(){
        System.out.println("name="+name);
    }

}
