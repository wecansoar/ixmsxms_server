package net.flower.ixmsxms_server.domain;

import java.util.Date;

public class User extends BaseObject {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String userStatus;
    private Date lastLoginDatetime;
    private Date regDatetime;
    private Date modDatetime;

    public Date getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    public void setLastLoginDatetime(Date lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getUserStatus() {
        return this.userStatus;
    }

    public Date getRegDatetime() {
        return this.regDatetime;
    }

    public Date getModDatetime() {
        return this.modDatetime;
    }

    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User setUserStatus(String userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public User setRegDatetime(Date regDatetime) {
        this.regDatetime = regDatetime;
        return this;
    }

    public User setModDatetime(Date modDatetime) {
        this.modDatetime = modDatetime;
        return this;
    }

}