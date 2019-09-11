package com.dsf.mp.sqlInjector.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyBaseMapper<T> extends BaseMapper<T> {
    int deleteAll();
    int insertBatchSomeColumn(List<T> list);
    int deleteByIdWithFill(T t);
    int alwaysUpdateSomeColumnById(@Param((Constants.ENTITY)) T entity);
}
