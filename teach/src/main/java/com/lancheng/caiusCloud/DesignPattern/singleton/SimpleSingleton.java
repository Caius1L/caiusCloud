package com.lancheng.caiusCloud.DesignPattern.singleton;

/**
 * 最简单的单例模式
 * 这是最简单的单例模式，只是由单个类组成，未确保单实例的唯一性，所有的的单例构造器
 * 都要被申明为private ,然后通过static方法实现全局访问唯一单例实例
 * 但是这个代码在多线程的情况下是不安全的
 */
public class SimpleSingleton {

    private static SimpleSingleton simpleSingleton;

    private SimpleSingleton(){
        System.out.println("SimpleSingleton is Instance!");
    };

    public static SimpleSingleton getSimpleSingleton(){
        if(null == simpleSingleton){
            simpleSingleton = new SimpleSingleton();
        }
        return simpleSingleton;
    }
}
