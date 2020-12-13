package com.lancheng.caiusCloud.lru;

import java.util.*;

public class MyLru {


    private static final LinkedList<Integer> list = new LinkedList<>();

    private static final HashMap<Integer, Integer> map = new HashMap<>();

    /**
     * 数据存放的逻辑
     *
     * @param index 索引
     * @param data  数据
     */
    public void put(Integer index, Integer data) {
        //存在则更新数据，并且更新索引的位置
        if (map.containsKey(index)) {
            map.put(index, data);
            //更新索引的位置
            indexSwap(index);
        } else {
            //插入数据，并在list尾部添加索引
            map.put(index, data);
            list.add(0,index);
        }
    }

    /**
     * 如果去查热点数据，也需要去更新热点数据的索引位置
     *
     * @param index 索引位置
     * @return 数据
     */
    public Integer get(Integer index) {
        if (map.containsKey(index)) {
            //将索引的位置进行更新
            indexSwap(index);
            return map.get(index);
        }
        return null;
    }

    /**
     * 将当前索引放在最前面
     *
     * @param index 索引
     */
    private void indexSwap(Integer index) {
        if (list.size() > index) {
            Integer exist = list.get(index);
            if (null != exist) {
                list.remove(index);
                list.add(0, index);
            }
        }
    }

    public List<Integer> getList() {
        return list;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    /**
     * 将当前索引加载链表最后面
     *
     * @param index 索引
     */
    private void indexAdd(Integer index) {
        list.add(index);
    }

    /**
     * 根据位移量进行清理
     *
     * @param offSite 偏移量
     */
    private void cleanCache(Integer offSite) {
        if (list.size() > offSite) {
            int size = list.size();
            for (int i = size - 1; i > offSite - 1; i--) {
                int index = list.getLast();
                list.removeLast();
                map.remove(index);
            }
        }
    }

    public static void main(String[] args) {
        MyLru myLru = new MyLru();
        myLru.put(0, 2);
        myLru.put(1, 3);
        myLru.put(2, 4);
        myLru.get(1);
        myLru.put(3, 2);
        System.out.println(myLru.getList());
        myLru.cleanCache(3);
        System.out.println(myLru.getList());
        System.out.println(myLru.getMap());
    }
}
