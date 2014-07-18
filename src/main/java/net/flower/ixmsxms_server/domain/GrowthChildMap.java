package net.flower.ixmsxms_server.domain;

import java.util.Date;

public class GrowthChildMap extends BaseObject {
    private Long id;
    private Long growthId;
    private Long childId;
    private Date regDatetime;
    private Date modDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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