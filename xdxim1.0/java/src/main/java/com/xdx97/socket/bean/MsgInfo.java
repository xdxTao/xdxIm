package com.xdx97.socket.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息实体
 *
 * @author 小道仙
 */
public class MsgInfo implements Serializable {
    /**
     * 消息id
     */
    private Integer id;

    /**
     * 消息发送者id
     */
    private Integer fromUserId;

    /**
     * 消息发送者名称
     */
    private String fromUserName;

    /**
     * 消息接收者id
     */
    private Integer toUserId;

    /**
     * 消息接收者名称
     */
    private String toUserName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息发送时间
     */
    private Date createTime;

    /**
     * 是否已读（1 已读）
     */
    private Integer unReadFlag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUnReadFlag() {
        return unReadFlag;
    }

    public void setUnReadFlag(Integer unReadFlag) {
        this.unReadFlag = unReadFlag;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MsgInfo other = (MsgInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFromUserId() == null ? other.getFromUserId() == null : this.getFromUserId().equals(other.getFromUserId()))
            && (this.getFromUserName() == null ? other.getFromUserName() == null : this.getFromUserName().equals(other.getFromUserName()))
            && (this.getToUserId() == null ? other.getToUserId() == null : this.getToUserId().equals(other.getToUserId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUnReadFlag() == null ? other.getUnReadFlag() == null : this.getUnReadFlag().equals(other.getUnReadFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFromUserId() == null) ? 0 : getFromUserId().hashCode());
        result = prime * result + ((getFromUserName() == null) ? 0 : getFromUserName().hashCode());
        result = prime * result + ((getToUserId() == null) ? 0 : getToUserId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUnReadFlag() == null) ? 0 : getUnReadFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fromUserId=").append(fromUserId);
        sb.append(", fromUserName=").append(fromUserName);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", unReadFlag=").append(unReadFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}