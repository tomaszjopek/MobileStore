package com.example.tomek.mobilestore;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomek.mobilestore.Model.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tomek on 2017-04-13.
 */

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder> {

    private static List<Product> mDataSet;
    private static Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View view;
        public ImageView image;
        public TextView name;
        public TextView price;
        public ImageButton basketBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.product_image);
            name = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.product_price);
            basketBtn = (ImageButton) itemView.findViewById(R.id.addToBasket);
            view = itemView;
            basketBtn.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = ((Activity)mContext).getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    Details fragment = new Details();
                    fragment.setProduct(mDataSet.get(getAdapterPosition()));
                    fragment.setmContext(mContext);

                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.container, fragment);
                    ft.addToBackStack("DETAIL");
                    ft.commit();
                }
            });

        }

        @Override
        public void onClick(View v) {
            ((MainActivity) mContext).addToBasket(mDataSet.get(getLayoutPosition()));
            Snackbar.make(view, "Dodano produkt do koszyka", Snackbar.LENGTH_LONG).show();
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
        holder.price.setText(String.format(Locale.ENGLISH, "%.2f", product.getPrice()));

        startAnimation(holder.view, position);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    private void startAnimation(View view, int position) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_left);
        if (position > 4) {
            animation.setDuration(0);
        } else {
            animation.setStartOffset(150 * position);
        }
        view.startAnimation(animation);
    }

}
