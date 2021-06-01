package com.example.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressDialog progressBar;
    private ProductApi productApi;
    private ProductAdapter homeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
//        GridLayoutManager gridLayout= new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayout);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);

        ConnectionClass connectionClass = new ConnectionClass();
        productApi = connectionClass.api;

        //initialize progress bar
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage("Please wait...");

        requestProductListAPI();
    }
    private void requestProductListAPI() {
        progressBar.show();

        Call<ProductListModel> call = productApi.requestProductList();
        call.enqueue(new Callback<ProductListModel>() {
            @Override
            public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response) {
                if (response.isSuccessful()) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int width = displayMetrics.widthPixels;

                    int canvas=width/response.body().getCanvasUnit();

                    Log.e("Width", String.valueOf(width));
                    Log.e("getCanvasUnit", String.valueOf(response.body().getCanvasUnit()));
                    Log.e("Canvas", String.valueOf(canvas));
                    homeAdapter=new ProductAdapter(MainActivity.this,canvas);
                    recyclerView.setAdapter(homeAdapter);

                    homeAdapter.addAll(response.body().getManagerSpecials());
                }
                progressBar.dismiss();
            }

            @Override
            public void onFailure(Call<ProductListModel> call, Throwable t) {
                progressBar.dismiss();
            }
        });
    }


}