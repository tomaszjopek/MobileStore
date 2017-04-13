package com.example.tomek.mobilestore;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomek.mobilestore.Model.Product;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    private static final String TAG = "ProductList";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyProductsAdapter mAdapter;
    private List<Product> mProducts;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.product_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAdapter = new MyProductsAdapter(mProducts, getActivity());

        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public List<Product> getmProducts() {
        return mProducts;
    }

    public void setmProducts(List<Product> mProducts) {
        this.mProducts = mProducts;
    }
}
