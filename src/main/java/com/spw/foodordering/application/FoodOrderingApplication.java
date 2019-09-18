package com.spw.foodordering.application;

import com.spw.foodordering.bean.User;

import org.litepal.LitePalApplication;

/**
 * Created by xch on 2018/1/25.
 */

//public class FoodOrderingApplication extends Application {
public class FoodOrderingApplication extends LitePalApplication {
    public String versionName;
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
//        AssetsCopyUtil.copyEmbassy2Databases(this, "data/data/" + this.getPackageName() + "/databases/",
//                "location.db");
    }
}
