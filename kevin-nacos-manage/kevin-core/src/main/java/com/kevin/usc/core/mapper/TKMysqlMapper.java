package com.kevin.usc.core.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TKMysqlMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
