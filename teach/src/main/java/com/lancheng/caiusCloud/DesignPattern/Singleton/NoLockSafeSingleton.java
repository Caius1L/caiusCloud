package com.lancheng.caiusCloud.DesignPattern.Singleton;

/**
 * 在单例模式的最佳实践中，类只会加载一次，通过在申明时直接实例化静态成员的方式
 * 来保证一个类是有一个实例，这种方式的实现避免了使用同步机制和判断实例是否被创建的类型检查中
 */
public class NoLockSafeSingleton {

    private static final NoLockSafeSingleton instance = new NoLockSafeSingleton();

    private NoLockSafeSingleton(){
        System.out.println("Singleton is Instances");
    }

    public static synchronized NoLockSafeSingleton getInstance(){
        return instance;
    }
}
