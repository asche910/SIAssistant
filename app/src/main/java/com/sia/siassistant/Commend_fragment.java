package com.sia.siassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static com.sia.siassistant.FragmentNew.goalBeanList;
import static com.sia.siassistant.MainActivity.resourceIdToUri;

public class Commend_fragment extends Fragment implements View.OnClickListener{
    private ImageView imageView_1,imageView_2;
    private View view;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.commend_fragment,container,false);

        imageView_1=(view).findViewById(R.id.commend_image_1);
        imageView_2=(view).findViewById(R.id.commend_image_2);
        Glide.with(getContext()).load(R.drawable.bg_1).into(imageView_1);
        Glide.with(getContext()).load(R.drawable.bg_2).into(imageView_2);
        Button recommend_button_1=(view).findViewById(R.id.commend_button_1);
        Button recommend_button_2=(view).findViewById(R.id.commend_button_2);
        recommend_button_1.setOnClickListener(this);
        recommend_button_2.setOnClickListener(this);
        pref=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        editor=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commend_button_1:   goalBeanList.add(new GoalBean("早睡早起","365","0","", resourceIdToUri(getActivity().getApplicationContext(), R.drawable.bg_1).toString(),"设置闹钟",0));
                Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();
            case R.id.commend_button_2:  Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();goalBeanList.add(new GoalBean("坚持学习","365","now","学习永无止境",resourceIdToUri(getActivity().getApplicationContext(),R.drawable.bg_2).toString(),"设置闹钟",0));break;

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }
}
