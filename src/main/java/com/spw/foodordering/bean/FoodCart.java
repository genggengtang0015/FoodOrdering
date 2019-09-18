package com.spw.foodordering.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 购物车中的产品
 */
public class FoodCart extends DataSupport implements Serializable {
    private Integer foodId;
    private String foodName;
    private Integer price; // 原价，单位分
    public Integer count;//购买数量
    private String imageUrl;//图片
    private Double priceView;
    private Integer foodTab; // 菜品TAB标签

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

//    public Integer getPrice() {
//        return price;
//    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPriceView() {
        priceView = (double) price/ 100;
        return priceView;
    }

    public void setPriceView(Double priceView) {
        this.priceView = priceView;
    }

    public Integer getFoodTab() {
        return foodTab;
    }

    public void setFoodTab(Integer foodTab) {
        this.foodTab = foodTab;
    }
}
