package com.example.tomek.mobilestore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomek.mobilestore.Model.Product;

import java.util.List;
import java.util.Locale;

/**
 * Created by Tomek on 2017-04-13.
 */

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder> {

    private List<Product> mDataSet;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView image;
        public TextView name;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.product_image);
            name = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.product_price);
            view = itemView;
        }
    }

    public MyProductsAdapter(List<Product> mDataSet, Context mContext) {
        this.mDataSet = mDataSet;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mDataSet.get(position);

        holder.image.setImageResource(product.getImageResource());
        holder.name.setText(product.getName());
        holder.price.setText(String.format(Locale.ENGLISH,"%.2f", product.getPrice()));

        startAnimation(holder.view, position);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    private void startAnimation(View view, int position) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_left);
        animation.setStartOffset(25 * position);
        view.startAnimation(animation);
    }
}
