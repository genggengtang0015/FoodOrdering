package com.spw.foodordering.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spw.foodordering.Activity_FoodsDetails;
import com.spw.foodordering.bean.FoodCart;
import com.spw.foodordering.fragment.Fragment_home;
import com.spw.foodordering.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;


/**
 * Created by xch on 2017/3/3.
 */


public class Adapter_Foods extends RecyclerView.Adapter<Adapter_Foods.ViewHolder> implements View.OnClickListener {
//    private Adapter_Address.OnItemClickListener mOnItemClickListener = null;
    private Fragment_home mContext;
//    private List<Foods> mFoodsList;
    private NumberFormat nf;
    private List<FoodCart> mFoodsList;

//    public Adapter_Foods(List<Foods> foodsList, Fragment_home mContext) {
//        nf = NumberFormat.getCurrencyInstance();
//        nf.setMaximumFractionDigits(2);
//        this.mFoodsList = foodsList;
//        this.mContext = mContext;
//    }

    public Adapter_Foods(List<FoodCart> foodsList, Fragment_home mContext) {
        nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
        nf.setMaximumFractionDigits(2);
        this.mFoodsList = foodsList;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(Adapter_Address.OnItemClickListener onItemClickListener) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View foods_item_view;
        ImageView food_img;
        TextView food_name, minus, tvCount, add, food_price,btnAdd;
//        TextView item_number_per_month;
//        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            foods_item_view = itemView.findViewById(R.id.foods_item_view);
            food_img = (ImageView) itemView.findViewById(R.id.food_img);
            food_name = (TextView) itemView.findViewById(R.id.food_name);
            food_price = (TextView) itemView.findViewById(R.id.food_price);
            minus = (TextView) itemView.findViewById(R.id.tvMinus);
            tvCount = (TextView) itemView.findViewById(R.id.count);
            add = (TextView) itemView.findViewById(R.id.tvAdd);
            btnAdd = (TextView) itemView.findViewById(R.id.btnAdd);
//            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
//            item_number_per_month= (TextView) itemView.findViewById(R.id.item_number_per_month);
        }
    }

    /**
     * 显示减号的动画
     */
    private Animation getShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 2f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    /**
     * 隐藏减号的动画
     */
    private Animation getHiddenAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 2f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext.getActivity()).inflate(R.layout.item_foods, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.btnAdd.setOnClickListener(this);
//        holder.foods_item_view.setOnClickListener(this);//为每个item添加点击事件
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//减号
                Fragment_home fragment = mContext;
                int position = holder.getAdapterPosition();
//                Foods foods = mFoodsList.get(position);
                FoodCart foods = mFoodsList.get(position);

//                int count = fragment.getSelectedItemCountById(foods.getId());
                int count = fragment.getSelectedItemCountById(foods.getFoodId());
                if (count < 2) {
                    holder.minus.setAnimation(getHiddenAnimation());
                    holder.minus.setVisibility(View.GONE);
                    holder.tvCount.setVisibility(View.GONE);
                }
                count--;
                fragment.remove(foods, false);
                holder.tvCount.setText(String.valueOf(count));
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//添加商品
                Fragment_home fragment = mContext;
                int position = holder.getAdapterPosition();
//                Foods foods = mFoodsList.get(position);
                FoodCart foods = mFoodsList.get(position);
                int count = fragment.getSelectedItemCountById(foods.getFoodId());
                if (count < 1) {
                    holder.minus.setAnimation(getShowAnimation());
                    holder.minus.setVisibility(View.VISIBLE);
                    holder.tvCount.setVisibility(View.VISIBLE);
                }
                fragment.add(foods, false);

                count++;
                holder.tvCount.setText(String.valueOf(count));
                //首先点击加号图标，拿到控件在屏幕上的绝对坐标，回调activity显示动画
                int[] loc = new int[2];
                v.getLocationInWindow(loc);
                fragment.playAnimation(loc);
            }
        });
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FoodCart foods = mFoodsList.get(position);
                int foodId=foods.getFoodId();
                String name = foods.getFoodName();
                String imageUrl = foods.getImageUrl();
                double price = foods.getPriceView();
//                String ingredients = foods.getIngredients();
//                String description = foods.getDescription();
//                int rating = foods.getRating();

                Intent intent = new Intent(mContext.getActivity(), Activity_FoodsDetails.class);
                intent.putExtra("foodId", foodId);
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("name", name);
                intent.putExtra("price", price);
                intent.putExtra("ingredients", "");
                intent.putExtra("description", "");
                intent.putExtra("rating", 0);
                mContext.startActivityForResult(intent,1);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.foods_item_view.setTag(position);
//        final Foods foods = mFoodsList.get(position);
        final FoodCart foods = mFoodsList.get(position);
        Fragment_home fragment = mContext;
        foods.count = fragment.getSelectedItemCountById(foods.getFoodId());
        holder.food_name.setText(foods.getFoodName());
//        holder.food_price.setText("￥"+foods.getPriceView());
        holder.food_price.setText(nf.format(foods.getPriceView()));
//        holder.ratingBar.setProgress(foods.getRating());
        holder.tvCount.setText(String.valueOf(foods.count));
        Glide.with(mContext).load(foods.getImageUrl()).into(holder.food_img);

        if(foods.getFoodTab() < 3){
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.add.setVisibility(View.GONE);
        }else{
            holder.btnAdd.setVisibility(View.GONE);
            holder.add.setVisibility(View.VISIBLE);

            if (foods.count < 1) {
                holder.tvCount.setVisibility(View.GONE);
                holder.minus.setVisibility(View.GONE);
            } else {
                holder.tvCount.setVisibility(View.VISIBLE);
                holder.minus.setVisibility(View.VISIBLE);
            }
        }

        Random random=new Random();
        int i=random.nextInt(100);
//        holder.item_number_per_month.setText("月售"+i+"单");
//        holder.item_number_per_month.setVisibility(View.GONE);
    }


    @Override
    public int getItemCount() {
        return mFoodsList.size();
    }


    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

//    public void setOnItemClickListener(Adapter_Address.OnItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }

    @Override
    public void onClick(View v) {
//        if (mOnItemClickListener != null) {
//            //使用getTag方法获取position
//            mOnItemClickListener.onItemClick(v, (int) v.getTag());
//        }
    }
}
