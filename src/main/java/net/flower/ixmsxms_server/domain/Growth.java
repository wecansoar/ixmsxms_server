package net.flower.ixmsxms_server.domain;

import java.util.Date;

public class Growth extends BaseObject {
    private Long growthId;
    private Long childId;
    private String growDate;
    private Long stature;
    private Long weight;
    private String note;
    private Date regDatetime;
    private Date modDatetime;

    public Long getGrowthId() {
        return growthId;
    }

    public void setGrowthId(Long growthId) {
        this.growthId = growthId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public String getGrowDate() {
        return growDate;
    }

    public void setGrowDate(String growDate) {
        this.growDate = growDate;
    }

    public Long getStature() {
        return stature;
    }

    public void setStature(Long stature) {
        this.stature = stature;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
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