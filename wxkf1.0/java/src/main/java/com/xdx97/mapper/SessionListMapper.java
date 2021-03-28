package com.xdx97.mapper;


import com.xdx97.bean.SessionList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SessionListMapper  {
    int deleteByPrimaryKey(String id);

    int insert(SessionList record);

    int insertSelective(SessionList record);

    SessionList selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SessionList record);

    int updateByPrimaryKey(SessionList record);

    SessionList selectOne(SessionList sessionList);

    List<SessionList> select(SessionList sessionList);

    int updateByOpenIdSelective(SessionList sessionList);

}