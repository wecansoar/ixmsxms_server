package net.flower.ixmsxms_server.domain;

import java.util.Date;

public class Growth extends BaseObject {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long userId;
    private Long growthId;

    public String getGrowthDate() {
        return growthDate;
    }

    public void setGrowthDate(String growthDate) {
        this.growthDate = growthDate;
    }

    private String growthDate;
    private Float stature;
    private Float weight;
    private String note;


    private Date regDatetime;
    private Date modDatetime;

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getStature() {
        return stature;
    }

    public void setStature(Float stature) {
        this.stature = stature;
    }

    public Long getGrowthId() {
        return growthId;
    }

    public void setGrowthId(Long growthId) {
        this.growthId = growthId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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