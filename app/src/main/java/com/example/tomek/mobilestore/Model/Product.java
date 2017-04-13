package com.example.tomek.mobilestore.Model;

/**
 * Created by Tomek on 2017-04-13.
 */

public class Product {
    private int id;
    private String name;
    private int imageResource;
    private double price;

    public Product(int id,String name, int imageResource, double price) {
        this.id = id;
        this.name = name;
        this.imageResource = imageResource;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
