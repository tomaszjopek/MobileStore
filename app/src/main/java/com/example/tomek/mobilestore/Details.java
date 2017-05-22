package com.example.tomek.mobilestore;


import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tomek.mobilestore.Model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class Details extends Fragment {

    private Product product;
    private Context mContext;
    private Button playBtn;
    private Button stopBtn;
    private MediaPlayer mPlayer;

    public Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        ImageView image = (ImageView) rootView.findViewById(R.id.detail_image);
        image.setImageResource(product.getImageResource());

        playBtn = (Button) rootView.findViewById(R.id.btn_play);
        stopBtn = (Button) rootView.findViewById(R.id.stop_btn);
        playBtn.setVisibility(View.INVISIBLE);
        stopBtn.setVisibility(View.INVISIBLE);

        if(product.getPathToSound() != -1) {
            mPlayer = MediaPlayer.create(mContext, product.getPathToSound());
            playBtn.setVisibility(View.VISIBLE);
            stopBtn.setVisibility(View.VISIBLE);
        }

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPlayer != null)
                mPlayer.start();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPlayer != null)
                    mPlayer.stop();
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mPlayer != null)
            mPlayer.release();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
