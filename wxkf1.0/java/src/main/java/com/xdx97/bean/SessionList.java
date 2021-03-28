package com.xdx97.bean;

import java.io.Serializable;

/**
 * session_list
 * @author 
 */
public class SessionList implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * kf_id
     */
    private String kfId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 会话名称
     */
    private String listName;

    /**
     * 会话状态（1 活跃，0关闭）
     */
    private Integer status;

    /**
     * 接入次数
     */
    private Integer connectCount;

    /**
     * 未读消息数
     */
    private Integer unReadCount;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(Integer connectCount) {
        this.connectCount = connectCount;
    }

    public Integer getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
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
        SessionList other = (SessionList) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getKfId() == null ? other.getKfId() == null : this.getKfId().equals(other.getKfId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getListName() == null ? other.getListName() == null : this.getListName().equals(other.getListName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getConnectCount() == null ? other.getConnectCount() == null : this.getConnectCount().equals(other.getConnectCount()))
            && (this.getUnReadCount() == null ? other.getUnReadCount() == null : this.getUnReadCount().equals(other.getUnReadCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getKfId() == null) ? 0 : getKfId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getListName() == null) ? 0 : getListName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getConnectCount() == null) ? 0 : getConnectCount().hashCode());
        result = prime * result + ((getUnReadCount() == null) ? 0 : getUnReadCount().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", listName=").append(listName);
        sb.append(", status=").append(status);
        sb.append(", connectCount=").append(connectCount);
        sb.append(", unReadCount=").append(unReadCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getKfId() {
        return kfId;
    }

    public void setKfId(String kfId) {
        this.kfId = kfId;
    }
}