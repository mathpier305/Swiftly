package com.example.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManagerSpecial {
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("original_price")
    @Expose
    private String originalPrice;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("width")
    @Expose
    private int width;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
