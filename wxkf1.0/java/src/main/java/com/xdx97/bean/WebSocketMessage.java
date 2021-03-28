package com.xdx97.bean;

import java.io.Serializable;

/**
 * WebSocket消息实体
 */
public class WebSocketMessage implements Serializable {

    private static final long serialVersionUID = -6060343040263809614L;

    // 消息类型 1、会话消息 2、列表消息
    private Integer status;

    // 消息内容
    private Object data;

    @Override
    public String toString() {
        return "WebSocketMessage{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
