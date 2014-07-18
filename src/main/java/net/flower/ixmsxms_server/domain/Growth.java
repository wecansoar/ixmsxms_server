package net.flower.ixmsxms_server.domain;

import java.util.Date;

public class Growth extends BaseObject {
    private Long growthId;
    private String growDate;
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

    public String getGrowDate() {
        return growDate;
    }

    public void setGrowDate(String growDate) {
        this.growDate = growDate;
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