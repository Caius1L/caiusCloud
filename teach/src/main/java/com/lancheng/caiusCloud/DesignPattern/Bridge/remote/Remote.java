/*
 * @(#) Remote.java 0.1 2021-01-14
 *
 * Copyright (c) 2021 Ruhr Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Ruhr Co., Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with Ruhr.
 */
package com.lancheng.caiusCloud.DesignPattern.Bridge.remote;

/**
 * 通过远程控制器接口
 * @author Caius
 * @version 0.1 2021-01-14
 */
public interface Remote {
    void power();

    void volumeDown();

    void volumeUp();

    void channelDown();

    void channelUp();
}