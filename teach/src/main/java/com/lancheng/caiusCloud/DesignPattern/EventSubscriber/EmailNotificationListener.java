/*
 * @(#) EmailNotificationListener.java 0.1 2021-01-09
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
 * 收到通知后发送邮件
 *
 * @author Caius
 * @version 0.1 2021-01-09
 */
public class EmailNotificationListener implements EventListener {

    private String email;

    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to" + email + "SomeOne has performed" + eventType);
    }

    public EmailNotificationListener(String email) {
        this.email = email;
    }
}