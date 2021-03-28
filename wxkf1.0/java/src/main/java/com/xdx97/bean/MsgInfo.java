package com.xdx97.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * msg_info
 * @author 
 */
public class MsgInfo implements Serializable {
    /**
     * 消息id
     */
    private String id;

    /**
     * 客服账号
     */
    private String kfId;

    /**
     * 客服名称
     */
    private String kfName;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息发送时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 是否已读（1 已读）
     */
    private Integer unReadFlag;

    /**
     * 来源1客服 0用户
     */
    private Integer source;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKfId() {
        return kfId;
    }

    public void setKfId(String kfId) {
        this.kfId = kfId;
    }

    public String getKfName() {
        return kfName;
    }

    public void setKfName(String kfName) {
        this.kfName = kfName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
            && (this.getKfId() == null ? other.getKfId() == null : this.getKfId().equals(other.getKfId()))
            && (this.getKfName() == null ? other.getKfName() == null : this.getKfName().equals(other.getKfName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUnReadFlag() == null ? other.getUnReadFlag() == null : this.getUnReadFlag().equals(other.getUnReadFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getKfId() == null) ? 0 : getKfId().hashCode());
        result = prime * result + ((getKfName() == null) ? 0 : getKfName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
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
        sb.append(", kfId=").append(kfId);
        sb.append(", kfName=").append(kfName);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", unReadFlag=").append(unReadFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}