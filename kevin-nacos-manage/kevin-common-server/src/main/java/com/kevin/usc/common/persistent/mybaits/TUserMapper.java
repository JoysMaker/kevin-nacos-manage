package com.kevin.usc.common.persistent.mybaits;

import com.kevin.usc.common.persistent.po.TUser;
import com.kevin.usc.core.mapper.TKMysqlMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

public interface TUserMapper extends TKMysqlMapper<TUser> {
}