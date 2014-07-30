package net.flower.ixmsxms_server.domain;

import java.util.Date;

public class Child extends BaseObject {
    private Long childId;
    private Long userId;
    private String name;
    private String sex;
    private String birthdayDate;
    private String imageUrl;
    private String status;
    private Float stature;
    private Float weight;
    private Float headCircumference;
    private Date regDatetime;
    private Date modDatetime;

    public Float getStature() {
        return stature;
    }

    public void setStature(Float stature) {
        this.stature = stature;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(Float headCircumference) {
        this.headCircumference = headCircumference;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegDatetime() {
        return regDatetime;
    }

    public void setRegDatetime(Date regDatetime) {
        this.regDatetime = regDatetime;
    }

    public Date getModDatetime() {
        return modDatetime;
    }

    public void setModDatetime(Date modDatetime) {
        this.modDatetime = modDatetime;
    }
}