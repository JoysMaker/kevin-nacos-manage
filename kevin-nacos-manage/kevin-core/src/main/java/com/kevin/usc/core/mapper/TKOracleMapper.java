package com.kevin.usc.core.mapper;

import tk.mybatis.mapper.additional.dialect.oracle.OracleMapper;
import tk.mybatis.mapper.common.Mapper;

public interface TKOracleMapper<T> extends OracleMapper<T>,Mapper<T> {
}
