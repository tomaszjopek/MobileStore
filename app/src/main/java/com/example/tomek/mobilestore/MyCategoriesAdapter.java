package com.example.tomek.mobilestore;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomek.mobilestore.Model.Category;

import java.util.List;

/**
 * Created by Tomek on 2017-04-12.
 */

class MyCategoriesAdapter extends RecyclerView.Adapter<MyCategoriesAdapter.ViewHolder> {
    private List<Category> mDataSet;
    private final int FADE_DURATION = 500;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;
        public ImageView labelImage;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            categoryName = (TextView) view.findViewById(R.id.category_name);
            labelImage = (ImageView) view.findViewById(R.id.category_label_image);
            cardView = (CardView) view.findViewById(R.id.category_cardView);
        }
    }

    public MyCategoriesAdapter(List<Category> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public MyCategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_recycler_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.categoryName.setText(mDataSet.get(position).getCategoryName());
        holder.labelImage.setBackgroundResource(mDataSet.get(position).getBackgroundRes());

        setScaleAnimation(holder.cardView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

}


