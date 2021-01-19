package com.xdx97.socket.bean;

import java.io.Serializable;

/**
 * 会话实体
 *
 * @author 小道仙
 */
public class SessionList implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 所属用户
     */
    private Integer toUserId;

    /**
     * 会话名称
     */
    private String listName;

    /**
     * 未读消息数
     */
    private Integer unReadCount;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
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
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getListName() == null ? other.getListName() == null : this.getListName().equals(other.getListName()))
            && (this.getUnReadCount() == null ? other.getUnReadCount() == null : this.getUnReadCount().equals(other.getUnReadCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getListName() == null) ? 0 : getListName().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", listName=").append(listName);
        sb.append(", unReadCount=").append(unReadCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }
}