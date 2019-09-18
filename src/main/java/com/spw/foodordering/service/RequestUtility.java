package com.spw.foodordering.service;

import com.spw.foodordering.bean.Address;
import com.spw.foodordering.bean.Business;
import com.spw.foodordering.bean.Comment;
import com.spw.foodordering.bean.FoodCart;
import com.spw.foodordering.bean.Foods;
import com.spw.foodordering.bean.Order;
import com.spw.foodordering.bean.SetMeal;
import com.spw.foodordering.bean.User;
import com.spw.foodordering.request.RequestManager;
import com.spw.foodordering.request.Requests;
import com.spw.foodordering.util.Util;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xch on 2017/11/27.
 */

public class RequestUtility {
    private static RequestManager requestManager = new Requests();
    private static List<Foods> foodsList = new ArrayList<>();
    private static List<Foods> foodsListById = new ArrayList<>();
    private static List<Order> ordersList = new ArrayList<>();
    private static List<User> userList = new ArrayList<>();
    private static List<Business> businessList = new ArrayList<>();
    private static List<Comment> commentList = new ArrayList<>();
    private static List<Address> addressList = new ArrayList<>();//收货地址
    private static List<SetMeal> setMealList = new ArrayList<>();//套餐

    private static String requestUrl = Util.Url;

    /**
     * 获取套餐信息
     * @return
     */
    public static List<SetMeal> getSetMealList() {
        try {
            setMealList = requestManager.request(requestUrl + "caterings/getarrangements", new TypeToken<List<SetMeal>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setMealList;
    }

    /**
     * 获取食品信息
     *
     * @return
     */
    public static List<Foods> getFoodsList() {
        try {
            foodsList = requestManager.request(requestUrl + "UserOpera/getProduct", new TypeToken<List<Foods>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodsList;
    }

    /**
     * 获取食品id对应的食品信息
     *
     * @param foodId 食品id
     * @return
     */
    public static List<Foods> getFoodsListById(String foodId) {
        try {
            String url = requestUrl + "UserOpera/getProductById/" + foodId;
            url = new String(url.getBytes(), "UTF-8");
            foodsListById = requestManager.request(url, new TypeToken<List<Foods>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodsListById;
    }

    /**
     * 获取全部订单
     *
     * @param UserId 用户id
     * @return
     */
    public static List<Order> getOrdersList(String UserId) {
        try {
            ordersList = requestManager.request(requestUrl + "UserOpera/getOrders?userId=" + UserId, new TypeToken<List<Order>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    /**
     * 获取待评价订单
     *
     * @param UserId
     * @return
     */
    public static List<Order> getWaitCommentOrders(String UserId) {
        try {
            ordersList = requestManager.request(requestUrl + "UserOpera/WaitCommentOrders?userId=" + UserId, new TypeToken<List<Order>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    /**
     * 通过用户id查询对应收货地址信息
     *
     * @param UserId
     * @return
     */
    public static List<Address> getAddressListById(String UserId) {
        try {
            addressList = requestManager.request(requestUrl + "Addresses/GetAddress?userId=" + UserId, new TypeToken<List<Address>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addressList;
    }

    /**
     * 根据电话号码查询用户信息
     *
     * @param phoneNumber
     * @return
     */
    public static List<User> getUserListForPhoneNumber(String phoneNumber) {
        try {
            userList = requestManager.request(requestUrl + "UserOpera/getUser?PhoneNumber=" + phoneNumber, new TypeToken<List<User>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * 获取商家信息
     *
     * @return
     */
    public static List<Business> getBusinessList() {
        try {
            businessList = requestManager.request(requestUrl + "UserOpera/getBusiness", new TypeToken<List<Business>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return businessList;
    }


    /**
     * 获取我的评价信息
     *
     * @param userId
     * @return
     */
    public static List<Comment> getCommentList(String userId) {
        try {
            commentList = requestManager.request(requestUrl + "UserOpera/GetMyComment?userId=" + userId, new TypeToken<List<Comment>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentList;
    }

    /**
     * 获取食品信息
     *
     * @return
     */
    public static List<FoodCart> getMainFoods(String shopId,String tabNum) {
        List<FoodCart> foodList = new ArrayList<>();
        try {
//            String retStr = requestManager.postForm(Util.BASE_LOCAL_URL + "main-food-list","shopID",shopId);
//            Log.i("getMainFoods","返回:" + retStr);
//            Gson gson = new Gson();
//            Map<String, List<FoodCart>> retMap = gson.fromJson(retStr,new TypeToken<Map<String, List<FoodCart>>>() {}.getType());
            Map<String, List<FoodCart>> retMap = requestManager.requestPost(Util.BASE_LOCAL_URL + "main-food-list", new TypeToken<Map<String, List<FoodCart>>>() {
            }.getType(),"shopID",shopId);

            if(null != retMap && !retMap.isEmpty())
                foodList = retMap.get(tabNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodList;
    }

    public static Map<String, List<FoodCart>> getAllFoods(String shopId) {
        Map<String, List<FoodCart>> retMap = new HashMap<>();
        try {
//            String retStr = requestManager.postForm(Util.BASE_LOCAL_URL + "main-food-list","shopID",shopId);
//            Log.i("getMainFoods","返回:" + retStr);
//            Gson gson = new Gson();
//            Map<String, List<FoodCart>> retMap = gson.fromJson(retStr,new TypeToken<Map<String, List<FoodCart>>>() {}.getType());
            retMap = requestManager.requestPost(Util.BASE_LOCAL_URL + "main-food-list", new TypeToken<Map<String, List<FoodCart>>>() {
            }.getType(),"shopID",shopId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }
}
