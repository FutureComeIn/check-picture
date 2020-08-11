package com.company.singleton;

public class TestSingleton {

    public static void main(String[] args){

        Singleton s1 =Singleton.getInstance();
        Singleton s2 =Singleton.getInstance();
        s1.setName("zs");
        s2.setName("ww");
        s1.sys();
        s2.sys();
        if(s1==s2){
            System.out.println("同一个对象");
        }else{
            System.out.println("非一个对象");
        }
    }
}
