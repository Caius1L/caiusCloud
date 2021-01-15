package com.lancheng.caiusCloud.DesignPattern.Singleton;

public class SynchronizedSingleton {

    private static SynchronizedSingleton synchronizedSingleton;

    private SynchronizedSingleton() {
        System.out.println("SynchronizedSingleton is Instance!");
    }

    public static SynchronizedSingleton getSimpleSingleton() {
        if (null == synchronizedSingleton) {
            synchronized (SynchronizedSingleton.class) {
                if (null == synchronizedSingleton) {
                    synchronizedSingleton = new SynchronizedSingleton();
                }
            }
        }
        return synchronizedSingleton;
    }
}
