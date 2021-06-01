package com.example.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.product.ProductApi.BASE_URL;

public class ConnectionClass {
    public Gson gson;
    public Retrofit retrofit;
    public ProductApi api;
    public OkHttpClient okHttpClient;
    public ConnectionClass() {

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(ProductApi.class);
    }

}