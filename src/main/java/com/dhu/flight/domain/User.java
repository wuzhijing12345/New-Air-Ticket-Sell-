package com.dhu.flight.domain;

import org.apache.ibatis.annotations.Param;

public class User {
    private long userId;
    private String password;
    private String username;
    private String telnumber;
    private String status;      //登陆状态
    private String avatar;
    private double money;

    public User(){
        userId=0;
        money=0;
    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId=userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money=money;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
