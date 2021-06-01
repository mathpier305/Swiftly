package com.example.product;

import android.content.Context;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<ManagerSpecial> arrayList;
    Context mcontext;
    int canvas;
    public ProductAdapter(Context context, int canvas) {
        this.mcontext = context;
        this.canvas = canvas;
        arrayList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.constraintLayout.getLayoutParams().width = arrayList.get(position).getWidth()*canvas;
        holder.constraintLayout.getLayoutParams().height = arrayList.get(position).getHeight()*canvas;
        holder.constraintLayout.requestLayout();

        holder.txtProduct.setText(arrayList.get(position).getDisplayName());

        holder.txtOriginalPrice.setText("$");
        holder.txtOriginalPrice.append(arrayList.get(position).getOriginalPrice());
        holder.txtOriginalPrice.setPaintFlags(holder.txtOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.txtPrice.setText("$");
        holder.txtPrice.append(arrayList.get(position).getPrice());

        if (arrayList.get(position).getWidth()<=7){
            holder.txtOriginalPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            holder.txtPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            holder.txtProduct.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        }else if (arrayList.get(position).getWidth()<=8){
            holder.txtOriginalPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            holder.txtPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            holder.txtProduct.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        }else if (arrayList.get(position).getWidth()<=10){
            holder.txtOriginalPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            holder.txtPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            holder.txtProduct.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        }else {
            holder.txtOriginalPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            holder.txtPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            holder.txtProduct.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }
        if (arrayList.get(position).getWidth()>=10 && arrayList.get(position).getHeight()>=10) {
            holder.productImage.getLayoutParams().width = arrayList.get(position).getWidth() * 12;
            holder.productImage.getLayoutParams().height = arrayList.get(position).getHeight() * 12;
            holder.productImage.requestLayout();

            holder.txtOriginalPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            holder.txtPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            holder.txtProduct.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }else {
            holder.productImage.getLayoutParams().width = 100;
            holder.productImage.getLayoutParams().height = 100;
            holder.productImage.requestLayout();
        }

        Picasso.get().load(arrayList.get(position).getImageUrl()).into(holder.productImage);
    }

    public void add(ManagerSpecial r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size() - 1);
    }

    public void addAll(List<ManagerSpecial> moveResults) {
        for (ManagerSpecial result : moveResults) {
            add(result);
        }
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProduct,txtOriginalPrice,txtPrice;
        CardView constraintLayout;
        ImageView productImage;
        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            txtProduct = itemView.findViewById(R.id.txtProduct);
            txtOriginalPrice = itemView.findViewById(R.id.txtOriginalPrice);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }

}

