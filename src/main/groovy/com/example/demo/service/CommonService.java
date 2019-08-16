package com.example.demo.service;


import java.util.List;

public interface CommonService<T> {

    int insertSelective(T record);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    T selectByPrimaryKey(String id);

    List<T> selectAllConditionally(T record, int pageNum, int pageSize);

}
