package com.xdx97.socket.mapper;

import com.xdx97.socket.bean.MsgInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MsgInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MsgInfo msgInfo);

    int insertSelective(MsgInfo msgInfo);

    MsgInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgInfo msgInfo);

    int updateByPrimaryKey(MsgInfo msgInfo);

    List<MsgInfo> select(MsgInfo msgInfo);

    void msgRead(@Param("fromUserId") Integer fromUserId,@Param("toUserId") Integer toUserId);

    List<MsgInfo> selectMsgList(@Param("fromUserId") Integer fromUserId,@Param("toUserId") Integer toUserId);
}
