/*
 * @(#) demo.java 0.1 2021-01-14
 *
 * Copyright (c) 2021 Ruhr Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Ruhr Co., Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with Ruhr.
 */
package com.lancheng.caiusCloud.DesignPattern.Bridge;

import com.lancheng.caiusCloud.DesignPattern.Bridge.device.Device;
import com.lancheng.caiusCloud.DesignPattern.Bridge.device.Radio;
import com.lancheng.caiusCloud.DesignPattern.Bridge.device.Tv;
import com.lancheng.caiusCloud.DesignPattern.Bridge.remote.AdvancedRemote;
import com.lancheng.caiusCloud.DesignPattern.Bridge.remote.BasicRemote;

/**
 * @author Caius
 * @version 0.1 2021-01-14
 */
public class demo {

    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}