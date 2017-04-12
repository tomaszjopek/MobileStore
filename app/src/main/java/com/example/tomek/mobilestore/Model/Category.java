package com.example.tomek.mobilestore.Model;

/**
 * Created by Tomek on 2017-04-12.
 */

public class Category {
    private String categoryName;
    private int backgroundRes;

    public Category(String categoryName, int backgroundRes) {
        this.categoryName = categoryName;
        this.backgroundRes = backgroundRes;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public void setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
    }
}
