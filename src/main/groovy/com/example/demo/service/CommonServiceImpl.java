package com.example.demo.service;


import com.example.demo.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.IdGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommonServiceImpl<T> implements CommonService<T> {

    @Autowired
    private CommonDao<T> dao;

    @Override
    public int insertSelective(T record) {
        try {
            Method setId = record.getClass().getMethod("setId", String.class);
            setId.invoke(record, "");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return dao.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public T selectByPrimaryKey(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAllConditionally(T record, int pageNum, int pageSize) {
        if (record == null) {
            return new ArrayList<>();
        }
        return dao.selectAllConditionally(record, pageNum, pageSize);
    }
}
