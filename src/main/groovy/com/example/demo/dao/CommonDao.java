package com.example.demo.dao;



import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonDao<T> {

    int insertSelective(T record);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    T selectByPrimaryKey(String id);

    List<T> selectAllConditionally(@Param("record") T record, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

}
