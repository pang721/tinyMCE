package com.example.demo.collection;

import java.util.List;

/**
 * @author pxh
 * @date 2021-12-24
 */
public class BaseService<T> {

    public <T> List<T> queryByParam(Class<T> classOfT, String sql, Object... objs) {
        System.out.println(classOfT);
        return null;
    }
}
