package com.lancheng.caiusCloud.DesignPattern.Strategy;

/**
 * 通用支付的算法接口
 */
public interface PayStrategy {
    /**
     * 支付接口
     * @param paymentAmount 花费
     * @return 是否完成
     */
    boolean pay(int paymentAmount);

    /**
     * 收集支付细节
     */
    void collectPaymentDetails();
}
