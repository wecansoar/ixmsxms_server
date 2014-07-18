package net.flower.ixmsxms_server.domain;

import java.util.Date;

public class GrowthItem extends BaseObject {

    private Long itemId;
    private Long growthId;
    private String imageUrl;
    private Long progressId;
    private String growthType;
    private Date regDatetime;
    private Date modDatetime;

    public String getGrowthType() {
        return growthType;
    }

    public void setGrowthType(String growthType) {
        this.growthType = growthType;
    }
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getGrowthId() {
        return growthId;
    }

    public void setGrowthId(Long growthId) {
        this.growthId = growthId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
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