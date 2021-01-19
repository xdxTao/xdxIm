package com.xdx97.socket.controller;

import com.xdx97.socket.bean.SessionList;
import com.xdx97.socket.bean.User;
import com.xdx97.socket.common.bean.AjaxResult;
import com.xdx97.socket.mapper.SeesionListMapper;
import com.xdx97.socket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SeesionListMapper seesionListMapper;

    /**
     * 已建立会话
     */
    @GetMapping("/sessionList/already")
    public AjaxResult<?> sessionListAlready(@RequestParam Integer id){
        List<SessionList> sessionLists = seesionListMapper.selectByUserId(id);
        return  AjaxResult.success(sessionLists);
    }

    // 可建立会话的用户
    @GetMapping("/sessionList/not")
    public AjaxResult<?> sessionListNot(@RequestParam Integer id){
        List<Integer> list = seesionListMapper.selectUserIdByUserId(id);
        list.add(id);
        List<User> cloudList = userMapper.getCloudList(list);
        return AjaxResult.success(cloudList);
    }

    // 创建会话
    @GetMapping("/createSession")
    public AjaxResult<?> createSession(@RequestParam Integer id,@RequestParam Integer toUserId,@RequestParam String toUserName){
        SessionList sessionList = new SessionList();
        sessionList.setUserId(id);
        sessionList.setUnReadCount(0);
        sessionList.setListName(toUserName);
        sessionList.setToUserId(toUserId);
        seesionListMapper.insert(sessionList);
        // 判断对方和我建立会话没有？ 没有也要建立
        Integer integer = seesionListMapper.selectIdByUser(toUserId, id);
        if (integer == null || integer <= 0){
            User user = userMapper.selectByPrimaryKey(id);
            sessionList.setUserId(toUserId);
            sessionList.setToUserId(id);
            sessionList.setListName(user.getName());
            seesionListMapper.insert(sessionList);
        }
        return AjaxResult.success();
    }

    // 删除会话
    @GetMapping("/delSession")
    public AjaxResult<?> delSession(@RequestParam Integer sessionId){
        seesionListMapper.deleteByPrimaryKey(sessionId);
        return AjaxResult.success();
    }

}
