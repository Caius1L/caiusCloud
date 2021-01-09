/*
 * @(#) LogOpenListener.java 0.1 2021-01-09
 *
 * Copyright (c) 2021 Ruhr Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Ruhr Co., Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with Ruhr.
 */
package com.lancheng.caiusCloud.DesignPattern.EventSubscriber;

import java.io.File;

/**
 * 收到通知后再日志中记录一条消息
 *
 * @author Caius
 * @version 0.1 2021-01-09
 */
public class LogOpenListener implements EventListener {

    private File log;

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log" + log + ":Someones has performed" + eventType);
    }

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }
}