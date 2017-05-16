package com.example.tomek.mobilestore;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomek.mobilestore.Model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    private final String TAG = "RecyclerView";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyCategoriesAdapter mAdapter;
    private MainActivity mainActivity;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.categories_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());

        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAdapter = new MyCategoriesAdapter(init());

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(mainActivity);

        return rootView;
    }

    public void setmContext(MainActivity mContext) {
        this.mainActivity = mContext;
    }

    private List<Category> init() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("Ksiazki", R.drawable.books));
        list.add(new Category("AGD i RTV", R.drawable.rtvagd));
        list.add(new Category("Muzyka", R.drawable.vinyls));
        list.add(new Category("Elektronika", R.drawable.electronic));
        list.add(new Category("Odziez", R.drawable.odziez));

        return list;
    }

}


