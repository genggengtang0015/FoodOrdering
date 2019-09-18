package com.spw.foodordering.request;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by xch on 2017/4/2.
 */

public interface RequestManager {
    //对象请求
    <T>T request(String url, Type typeOfT, String... params)throws Exception;
    //图片请求
    void getImage(Context context, View view, String url)throws Exception;
    //表单提交
    String postForm(String url, String... args)throws Exception;

    //表单提交
    String postwithHeaders(String url, Map<String,String> headers, Map<String,String> args)throws Exception;

    //对象请求，封装返回对象为gson
    <T> List<T> requestWithHeaders(String url, Type typeOfT, Map<String,String> headers, Map<String,String> params)throws Exception;

    //POST提交
    <T>T requestPost(String url, Type typeOfT, String... args)throws Exception;
}
