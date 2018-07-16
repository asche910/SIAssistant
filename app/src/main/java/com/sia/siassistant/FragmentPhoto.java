package com.sia.siassistant;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FragmentPhoto extends Fragment {
    ImageView imageView_1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.photo,container,false);
       // imageView_1=(view).findViewById(R.id.photo_1);
      //  Glide.with(getContext()).load(R.drawable.tupian_6).into(imageView_1);
        return  view;
    }

}
