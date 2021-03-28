package com.xdx97.mapper;


import com.xdx97.bean.KfInfo;
import com.xdx97.bean.MsgInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KfInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(KfInfo record);

    int insertSelective(KfInfo record);

    KfInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(KfInfo record);

    int updateByPrimaryKey(KfInfo record);

    List<KfInfo> select(KfInfo msgInfo);
}