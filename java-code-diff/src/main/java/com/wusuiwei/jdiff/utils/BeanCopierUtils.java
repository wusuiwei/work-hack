package com.wusuiwei.jdiff.utils;

import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用 cglib来进行数据类型转换
 */
public class BeanCopierUtils {

    private BeanCopierUtils() {
    }

    /**
     * 这里使用Map来作为 beanCopier的缓存（享元设计模式)
     */
    public static Map<String, BeanCopier> beanCopierCacheMap = new HashMap<>();

    /**
     * 拷贝source的 属性 到 target中
     *
     * @param source 基类
     * @param target 目标类
     */
    public static void copy(Object source, Object target) {
        try {
            //设置map的key的值
            String cacheKey = source.getClass().toString() + target.getClass().toString();
            BeanCopier beanCopier = null;
            //双重检测锁
            if (!beanCopierCacheMap.containsKey(cacheKey)) {
                synchronized (BeanCopierUtils.class) {
                    if (!beanCopierCacheMap.containsKey(cacheKey)) {
                        beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
                        beanCopierCacheMap.put(cacheKey, beanCopier);
                    } else {
                        beanCopier = beanCopierCacheMap.get(cacheKey);
                    }
                }
            } else {
                beanCopier = beanCopierCacheMap.get(cacheKey);
            }
            beanCopier.copy(source, target, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}