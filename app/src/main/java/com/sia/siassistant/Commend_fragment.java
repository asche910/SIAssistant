package com.sia.siassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import com.sia.siassistant.Text.MyDatabase;

import java.util.Random;

import static com.sia.siassistant.FragmentNew.goalBeanList;
import static com.sia.siassistant.MainActivity.resourceIdToUri;

public class Commend_fragment extends Fragment implements View.OnClickListener{
    private ImageView imageView_1,imageView_2;
    private MyDatabase myDatabase;
    private View view;
    private Button recommend_button_1;
    private Button recommend_button_2;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:recommend_button_1.setText("已添加");break;
                case 2:recommend_button_2.setText("已添加");break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.commend_fragment,container,false);

        imageView_1=(view).findViewById(R.id.commend_image_1);
        imageView_2=(view).findViewById(R.id.commend_image_2);
        Glide.with(getContext()).load(R.drawable.bg_1).into(imageView_1);
        Glide.with(getContext()).load(R.drawable.bg_2).into(imageView_2);
         recommend_button_1=(view).findViewById(R.id.commend_button_1);
         recommend_button_2=(view).findViewById(R.id.commend_button_2);
        recommend_button_1.setOnClickListener(this);
        recommend_button_2.setOnClickListener(this);
        pref=getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        editor=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        String button1_string=pref.getString("button1","添加");
        String button2_string=pref.getString("button2","添加");
        recommend_button_1.setText(button1_string);
        recommend_button_2.setText(button2_string);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.commend_button_1: if (recommend_button_1.getText().toString()=="已添加"){break;} else{ goalBeanList.add(new GoalBean(new Random(System.currentTimeMillis()).nextInt(),"早睡早起","365","2017-06-26","", resourceIdToUri(getActivity().getApplicationContext(), R.drawable.bg_1).toString(),"设置闹钟",0, false));
                Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();editor.putString("button1","已添加");editor.apply();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);
                   

                    }
                }).start();break;}
            case R.id.commend_button_2: if (recommend_button_2.getText().toString()=="已添加"){break;}else{editor.putString("button2","已添加");editor.apply();
                Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();goalBeanList.add(new GoalBean(new Random(System.currentTimeMillis()).nextInt() , "坚持学习","365","2018-07-22","学习永无止境",resourceIdToUri(getActivity().getApplicationContext(),R.drawable.bg_2).toString(),"设置闹钟",0, false));
new Thread(new Runnable() {
    @Override
    public void run() {
        Message message=new Message();
        message.what=2;
        handler.sendMessage(message);
    }
}).start();break;}
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }
}
