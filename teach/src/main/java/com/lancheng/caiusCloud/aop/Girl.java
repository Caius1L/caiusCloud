package com.lancheng.caiusCloud.aop;

import org.springframework.stereotype.Component;

@Component
public class Girl implements IBuy {

    @Override
    public String buy(int price) {
        System.out.println("女生买了一本书，我猜是《跟高兴遇见你》,价格是：" + price);
        return "书";
    }
}
