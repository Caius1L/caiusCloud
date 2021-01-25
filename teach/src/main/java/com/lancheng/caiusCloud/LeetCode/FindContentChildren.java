/*
 * @(#) FindContentChildren.java 0.1 2021-01-24
 *
 * Copyright (c) 2021 Ruhr Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Ruhr Co., Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with Ruhr.
 */
package com.lancheng.caiusCloud.LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 贪心算法策略
 *
 * @author Caius
 * @version 0.1 2021-01-24
 */
public class FindContentChildren {

    /**
     * 给最小饥饿度的孩子分配最小的饱腹的饼干
     *
     * @param children children集合
     * @param cookies  cookies集合
     * @return 可以饱腹的孩子的数量
     */
    public int find(List<Integer> children, List<Integer> cookies) {
        cookies = cookies.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        children = children.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        int child = 0, cookie = 0;
        while (child < children.size() && cookie < cookies.size()) {
            if (children.get(child) <= cookies.get(cookie)) {
                child++;
            }
            cookie++;
        }
        return child;
    }

    /**
     * 给小孩子发糖果，当前小孩的分数比周围的小孩高，那么它的糖果一定比旁边的多，问最少需要的饼干数量
     *
     * @param score 小孩的分数
     * @return 最小的饼干数量
     */
    public int findCookies(List<Integer> score) {
        int size = score.size();
        if (size < 2) {
            return size;
        }
        for (int i = 1; i < size; i++) {
            if (score.get(i) > score.get(i - 1)) {
                score.set(i, score.get(i - 1) + 1);
            }
        }
        for (int i = size - 1; i > 0; i++) {
            if (score.get(i) < score.get(i - 1)) {
                score.set(i - 1, score.get(i) + 1);
            }
        }
        return (int) score.stream().mapToInt(Integer::intValue).summaryStatistics().getSum();
    }

}
