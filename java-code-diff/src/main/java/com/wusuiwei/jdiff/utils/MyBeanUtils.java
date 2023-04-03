package com.wusuiwei.jdiff.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyBeanUtils extends BeanUtils {
    public static <T> List<T> copyList(List<?> sourceList, Class<T> clz) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.EMPTY_LIST;
        }

        List<T> res = new ArrayList<>();
        for (Object o : sourceList) {
            if (ObjectUtils.isEmpty(o)) {
                break;
            }
            T t = null;
            try {
                t = clz.getConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            BeanUtils.copyProperties(o, t);
            res.add(t);
        }
        return res;
    }
}
