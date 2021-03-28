package com.xdx97.mapper;


import com.xdx97.bean.MsgInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MsgInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgInfo record);

    int insertSelective(MsgInfo record);

    MsgInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgInfo record);

    int updateByPrimaryKey(MsgInfo record);

    List<MsgInfo> select(MsgInfo msgInfo);
}