/*
 * @(#) ThirdPartyYouTubeLib.java 0.1 2021-01-14
 *
 * Copyright (c) 2021 Ruhr Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Ruhr Co., Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with Ruhr.
 */
package com.lancheng.caiusCloud.DesignPattern.Proxy;

import java.util.HashMap;

/**
 * 远程服务接口
 * @author Caius
 * @version 0.1 2021-01-14
 */
public interface ThirdPartyYouTubeLib {

    /**
     *
     * 获取所有的视频流
     * @return 视频流文件集合
     */
    HashMap<String, Video> popularVideos();

    /**
     * 根据视频流编号获取视频流文件
     * @param videoId 视频编号
     * @return 视频流文件
     */
    Video getVideo(String videoId);

}