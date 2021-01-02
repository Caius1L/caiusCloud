package com.lancheng.caiusCloud.aop;

import org.springframework.stereotype.Component;

@Component
public class Boy implements IBuy {

    @Override
    public String buy(int price) {
        System.out.println("男生购买了一款游戏机吗，我猜是任天堂,价格是" + price);
        return "game boy";
    }
}
