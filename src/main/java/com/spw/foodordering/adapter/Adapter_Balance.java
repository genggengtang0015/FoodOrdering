package com.spw.foodordering.adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spw.foodordering.Activity_Balance;
import com.spw.foodordering.R;
import com.spw.foodordering.bean.FoodCart;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by xch on 2017/3/9.
 */

public class Adapter_Balance extends RecyclerView.Adapter<Adapter_Balance.ViewHolder> {
    private ArrayList<FoodCart> dataList;
    private NumberFormat nf;
    private LayoutInflater mInflater;


    public Adapter_Balance(Activity_Balance activity, ArrayList<FoodCart> dataList) {
        this.dataList = dataList;
//        nf = NumberFormat.getCurrencyInstance();
        nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
        nf.setMaximumFractionDigits(2);
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_selected_foods_balance, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodCart item = dataList.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FoodCart item;
        private TextView tvCost, tvCount,tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCost = (TextView) itemView.findViewById(R.id.tvCost);
            tvCount = (TextView) itemView.findViewById(R.id.count);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bindData(FoodCart item) {
            this.item = item;
            tvName.setText(item.getFoodName());
            tvCost.setText(nf.format(item.count * item.getPriceView()));
            tvCount.setText("×"+String.valueOf(item.count));
        }
    }
}
