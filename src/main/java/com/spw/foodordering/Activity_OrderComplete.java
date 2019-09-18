package com.spw.foodordering;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spw.foodordering.R;
import com.spw.foodordering.bean.FoodCart;
import com.spw.foodordering.control.BaseActivity;
import com.spw.foodordering.bean.Foods;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Activity_OrderComplete extends BaseActivity implements View.OnClickListener {
    private Context context;
    private TextView tv_consignee, tv_receivingAddress, tv_expectSentToTime;//收货人 收货地址 送达时间
    private TextView tv_CostBalance, tv_foodsCost, tvCostDistribution, tv_lunch_box;//实付款 食品总价格 配送费 餐盒费
    private LinearLayout ly_contact_Business, ly_contact_CustomerService;//联系商家 联系客服
    private NumberFormat nf;
    double cost;
//    private String userId, userName, email, sex, phoneNumber, nickname, address, avatar,remarkContent;
    private int statusCode = 0;
    private List<Foods> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        getOrderData();
        initView();
    }

    private void initView() {
        context=Activity_OrderComplete.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("订单完成");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        findViewById();
//        nf = NumberFormat.getCurrencyInstance();
        nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
        nf.setMaximumFractionDigits(2);
        tv_foodsCost.setText(nf.format(cost));
        tv_CostBalance.setText(nf.format(cost));
//        tv_consignee.setText("收件人：" + userName + "-" + phoneNumber);
//        tv_receivingAddress.setText("收货地址：" + address);
        tv_expectSentToTime.setText(getExpectSentToTime());
        ly_contact_Business.setOnClickListener(this);
        ly_contact_CustomerService.setOnClickListener(this);
    }

    /**
     * 通过id找到view
     */
    private void findViewById() {
        tv_CostBalance = (TextView) findViewById(R.id.tv_CostBalance);
        tv_foodsCost = (TextView) findViewById(R.id.tv_foodsCost);
        tvCostDistribution = (TextView) findViewById(R.id.tvCostDistribution);
        tv_lunch_box = (TextView) findViewById(R.id.tv_lunch_box);
        tv_consignee = (TextView) findViewById(R.id.tv_consignee);
        tv_receivingAddress = (TextView) findViewById(R.id.tv_receivingAddress);
        tv_expectSentToTime = (TextView) findViewById(R.id.tv_expectSentToTime);
        ly_contact_Business = (LinearLayout) findViewById(R.id.ly_contact_Business);
        ly_contact_CustomerService = (LinearLayout) findViewById(R.id.ly_contact_CustomerService);
    }

    public static void actionStart(Context context, double cost, int statusCode, ArrayList<FoodCart> foodsList) {
        Intent intent = new Intent(context, Activity_OrderComplete.class);
//        intent.putExtra("userId", userId);
        intent.putExtra("cost", cost);
        intent.putExtra("statusCode", statusCode);
//        intent.putExtra("userName", userName);
//        intent.putExtra("email", email);
//        intent.putExtra("sex", sex);
//        intent.putExtra("phoneNumber", phoneNumber);
//        intent.putExtra("nickname", nickname);
//        intent.putExtra("address", address);
//        intent.putExtra("avatar", avatar);
        intent.putExtra("foods", foodsList);
//        intent.putExtra("remarkContent", remarkContent);
        context.startActivity(intent);
    }

    /**
     * 获得订单数据
     */
    private void getOrderData() {
        Intent intent = getIntent();
        cost = intent.getDoubleExtra("cost", -1);
        statusCode = intent.getIntExtra("statusCode", -1);
//        userId = intent.getStringExtra("userId");
//        userName = intent.getStringExtra("userName");
//        email = intent.getStringExtra("email");
//        sex = intent.getStringExtra("sex");
//        phoneNumber = intent.getStringExtra("phoneNumber");
//        nickname = intent.getStringExtra("nickname");
//        address = intent.getStringExtra("address");
//        avatar = intent.getStringExtra("avatar");
        foodList = (ArrayList<Foods>) intent.getSerializableExtra("foods");
//        remarkContent=intent.getStringExtra("remarkContent");
    }


    /**
     * 获取期望送达时间
     * @return
     */
    private String getExpectSentToTime(){
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        Calendar ca = Calendar.getInstance();//当前时间
        ca.set(Calendar.MINUTE, ca.get(Calendar.MINUTE) + 35);//往后加35分钟
        return format.format(ca.getTime());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                foodList.clear();
                Intent intent = new Intent(Activity_OrderComplete.this, Activity_Main.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //重写返回键
            Intent intent = new Intent(Activity_OrderComplete.this, Activity_Main.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
