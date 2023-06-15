package com.ig.affiliatewebsite.model;

public class PostInfo {
    private Long id;
    private Integer userId;
    private Integer affiliateNum;
    private Integer pendingNum;
    private Integer linuxNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAffiliateNum() {
        return affiliateNum;
    }

    public void setAffiliateNum(Integer affiliateNum) {
        this.affiliateNum = affiliateNum;
    }

    public Integer getPendingNum() {
        return pendingNum;
    }

    public void setPendingNum(Integer pendingNum) {
        this.pendingNum = pendingNum;
    }

    public Integer getLinuxNum() {
        return linuxNum;
    }

    public void setLinuxNum(Integer linuxNum) {
        this.linuxNum = linuxNum;
    }
}
