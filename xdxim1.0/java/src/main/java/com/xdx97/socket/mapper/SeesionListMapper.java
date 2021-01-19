package com.xdx97.socket.mapper;

import com.xdx97.socket.bean.SessionList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SeesionListMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SessionList sessionList);

    int insertSelective(SessionList sessionList);

    SessionList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SessionList sessionList);

    int updateByPrimaryKey(SessionList sessionList);

    List<Integer> selectUserIdByUserId(Integer id);

    List<SessionList> selectByUserId(Integer id);

    Integer selectIdByUser(@Param("fromId") Integer fromId,@Param("toId") Integer toId);

    SessionList select(SessionList sessionList);

    void addUnReadCount(@Param("userId") Integer userId,@Param("toUserId") Integer toUserId);

    void delUnReadCount(@Param("fromUserId") Integer fromUserId,@Param("toUserId") Integer toUserId);
}
