package com.liumulin.daos;

import com.liumulin.ConfigsExample;
import com.liumulin.entity.Config;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigDao {
    long countByExample(ConfigsExample example);

    long count();

    int deleteByExample(ConfigsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    List<Config> selectByExample(ConfigsExample example);

    Config selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigsExample example);

    int updateByExample(@Param("record") Config record, @Param("example") ConfigsExample example);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}