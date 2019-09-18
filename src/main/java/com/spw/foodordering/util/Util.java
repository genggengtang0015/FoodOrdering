package com.spw.foodordering.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.spw.foodordering.Activity_Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xch on 2017/6/5.
 */

public class Util {
    private static AlertDialog mAlertDialog;
    public static String Url = "http://111.231.191.26/FoodOrdering/";
    private static Toast toast;
    private static boolean IsConnect = true;

    public final static String BASE_URL = "https://www-openapi.hualala.com/";
    public final static String APP_KEY = "1668";
    public final static String GROUP_ID = "130992";
    public final static String API_VERSION = "2.0";
    public final static String APP_SECRET = "hVS0GsaN";
    public final static String PRE_SIGNATURE = "key";
    public final static String POST_SIGNATURE = "secret";
    public final static String AES_KEY = "hVS0GsaNhVS0GsaN";
    public final static String AES_IV = "hVS0GsaNhVS0GsaN";
    public final static String SHOP_ID = "76169191";
    public final static String FOOD_FILE_NAME = "food_list.json";

//    public final static String BASE_LOCAL_URL = "http://192.168.1.7:8282/"; // 家里服务器
    public final static String BASE_LOCAL_URL = "http://134.192.36.128:8282/"; // 公司服务器
//    public final static String BASE_LOCAL_URL = "http://10.10.0.149:8282/"; // 三品服务器

    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            //toast.setGravity(Gravity.CENTER, 0, -20);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(text);
        }
        toast = null;
    }

    public static boolean checkNetwork(Context context) {
        IsConnect = true;
        if (Activity_Main.networkState == 0) {
            IsConnect = false;
            Util.showToast(context, "网络连接失败,请检查网络设置");
            return IsConnect;
        }
        return IsConnect;
    }



    /**
     * 显示用户自定义的对话框
     *
     * @param context
     * @param message
     * @param listener
     */
    public static void showDialog(Context context, String title, String message, final IAlertDialogButtonListener listener) {
        // 在创建Dialog的时候设置样式为透明的，并且要求api最低为11
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAlertDialog != null) { // 关闭对话框：判断对话框是否为空
                    mAlertDialog.cancel();
                }
                if (listener != null) {
                    // 设置回调，OnClick（）就是IAlertDialogButtonListener接口中的方法
                    listener.onDialogOkButtonClick();// 执行接口的确定方法
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAlertDialog != null) {
                    mAlertDialog.cancel();
                }

                if (listener != null) {
                    // 设置回调，OnClick（）就是IAlertDialogButtonListener接口中的方法
                    listener.onDialogCancelButtonClick();// 执行接口的取消方法
                }
            }
        });
        // 创建对话
        mAlertDialog = builder.create();
        // 显示对话框
        mAlertDialog.show();
    }

    /**
     * 字符串非空串判断
     * @param s
     * @return
     */
    public static boolean isTrimEmpty(final String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 得到json文件中的菜单内容
     * @param context
     * @return
     */
    public static String getFoodListFromJson(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(FOOD_FILE_NAME), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line.trim());
            }
        } catch (IOException e) {
            Log.e("postFoodList",e.getMessage(),e);
        }
        return stringBuilder.toString();

    }
}
