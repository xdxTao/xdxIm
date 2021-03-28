package com.xdx97.bean;


import java.io.Serializable;

/**
 * kf_info
 * @author 
 */
public class KfInfo implements Serializable {
    /**
     * 客服id
     */
    private String id;

    /**
     * 客服名称
     */
    private String kfName;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKfName() {
        return kfName;
    }

    public void setKfName(String kfName) {
        this.kfName = kfName;
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
        KfInfo other = (KfInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getKfName() == null ? other.getKfName() == null : this.getKfName().equals(other.getKfName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getKfName() == null) ? 0 : getKfName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", kfName=").append(kfName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}