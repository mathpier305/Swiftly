package com.example.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListModel {
    @SerializedName("canvasUnit")
    @Expose
    private int canvasUnit;
    @SerializedName("managerSpecials")
    @Expose
    private List<ManagerSpecial> managerSpecials = null;

    public int getCanvasUnit() {
        return canvasUnit;
    }

    public void setCanvasUnit(int canvasUnit) {
        this.canvasUnit = canvasUnit;
    }

    public List<ManagerSpecial> getManagerSpecials() {
        return managerSpecials;
    }
}
