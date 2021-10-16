package com.project.laaldairy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.project.laaldairy.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.Holder> {
    private List<Integer> imageData;
    private int scale = 50;

    public ImageSliderAdapter() {
        this.imageData = new ArrayList<>();
        imageData.add(R.drawable.sign_in_image_1);
        imageData.add(R.drawable.sign_in_image_2);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if(position%2==0) holder.imageView.setImageResource(imageData.get(0));
        else if(position%2==1) holder.imageView.setImageResource(imageData.get(1));
    }

    @Override
    public int getCount() {
        return imageData.size()*scale;
    }

    class Holder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSliderView);
        }
    }
}

