package com.example.product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {

    String BASE_URL = "https://raw.githubusercontent.com/Swiftly-Systems/code-exercise-android/";

    @GET("master/backup")
    Call<ProductListModel> requestProductList();
}
