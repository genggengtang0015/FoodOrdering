package com.spw.foodordering.bean;

import java.util.List;

public class FoodDetail {
    private String foodID; //菜品ID
    private String foodName; //菜品名称
    private String foodCode; //菜品编号
    private String foodCategoryID; //分类ID
    private String foodCategoryName; //分类的名称
    private String foodCategoryKey; //分类Key
    private Boolean isActive; //是/否启用
    private String setFoodDetailJson; //套餐明细
    private String takeoutPackagingFee;//打包费
    private String foodSortIndex;//菜品排序

    private List<FoodUnit> units;

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getFoodCategoryID() {
        return foodCategoryID;
    }

    public void setFoodCategoryID(String foodCategoryID) {
        this.foodCategoryID = foodCategoryID;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getFoodCategoryKey() {
        return foodCategoryKey;
    }

    public void setFoodCategoryKey(String foodCategoryKey) {
        this.foodCategoryKey = foodCategoryKey;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getSetFoodDetailJson() {
        return setFoodDetailJson;
    }

    public void setSetFoodDetailJson(String setFoodDetailJson) {
        this.setFoodDetailJson = setFoodDetailJson;
    }

    public List<FoodUnit> getUnits() {
        return units;
    }

    public String getFoodSortIndex() {
        return foodSortIndex;
    }

    public void setFoodSortIndex(String foodSortIndex) {
        this.foodSortIndex = foodSortIndex;
    }

    public void setUnits(List<FoodUnit> units) {
        this.units = units;
    }

    public String getTakeoutPackagingFee() {
        return takeoutPackagingFee;
    }

    public void setTakeoutPackagingFee(String takeoutPackagingFee) {
        this.takeoutPackagingFee = takeoutPackagingFee;
    }
}
