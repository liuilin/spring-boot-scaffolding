package com.liumulin.daos;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liumulin.ConfigsExample;
import com.liumulin.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConfigMapper extends BaseMapper<Config> {
}