package com.tju.elmboot.po;

public class Comment {
    private String userId;

    private int businessId;

    private int foodId;

    private String content;

    public int getCmId() {
        return cmId;
    }

    public void setCmId(int cmId) {
        this.cmId = cmId;
    }

    private String img;

    private int cmId;
    private int star;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

}
