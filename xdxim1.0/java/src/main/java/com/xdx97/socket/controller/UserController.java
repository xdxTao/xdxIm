package com.xdx97.socket.controller;

import com.xdx97.socket.bean.User;
import com.xdx97.socket.common.bean.AjaxResult;
import com.xdx97.socket.common.utils.CurPool;
import com.xdx97.socket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 注册用户
    @GetMapping("/register")
    public AjaxResult<?> register(@RequestParam String name) {
        String s = userMapper.selectByName(name);
        if (s != null || "".equals(s)){
            return AjaxResult.failure("该用户已存在");
        }
        User user = new User();
        user.setName(name);
        userMapper.insert(user);
        return AjaxResult.success();
    }

    // 登录
    @GetMapping("/login")
    public AjaxResult<?> login(@RequestParam String name){
        if (CurPool.curUserPool.get(name) != null){
            return AjaxResult.failure("该用户已登录");
        }
        User user = userMapper.selectUserByName(name);
        if (user == null || user.getId() == null){
            return AjaxResult.failure("该用户不存在");
        }
        CurPool.curUserPool.put(user.getName(), user);
        return AjaxResult.success(user);
    }

    // 注销
    @GetMapping("/loginOut")
    public AjaxResult<?> loginOut(@RequestParam String name){
        if (name != null && !"".equals(name)){
            CurPool.curUserPool.remove(name);
            User user = userMapper.selectUserByName(name);
            CurPool.sessionPool.remove(user.getId());
            CurPool.webSockets.remove(user.getId());
            System.out.println("【websocket消息】连接断开，总数为:"+CurPool.webSockets.size());
        }
        return AjaxResult.success();
    }


}
