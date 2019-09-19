package xyz.peter666.entity;

import java.util.Date;



public class Auth {

    private Integer authId;

    private Date createTime;


    private Integer status;

    private Integer userId;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "authId=" + authId +
                ", createTime=" + createTime +
                ", status=" + status +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
