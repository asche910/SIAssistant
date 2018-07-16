package com.sia.siassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GoodFriend extends Fragment implements View.OnClickListener{
    private Fragment fragment;
    private ImageView imageView_1;

    private CheckBox checkBox_1;
    private CheckBox checkBox_2,checkBox_3,checkBox_4,checkBox_5;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.friends,container,false);
        imageView_1=(view).findViewById(R.id.friend_1);
        ImageView imageView_2=(view).findViewById(R.id.friend_2);
        ImageView imageView_3=(view).findViewById(R.id.friend_3);
        ImageView imageView_4=(view).findViewById(R.id.friend_4);
        ImageView imageView_5=(view).findViewById(R.id.friend_5);
        ImageView imageView_6=(view).findViewById(R.id.friend_6);
        checkBox_1=(view).findViewById(R.id.checkBox);
         checkBox_2=(view).findViewById(R.id.checkBox_2);
         checkBox_3=(view).findViewById(R.id.checkBox_3);
         checkBox_4=(view).findViewById(R.id.checkBox_4);
         checkBox_5=(view).findViewById(R.id.checkBox_5);
        Button button=(view).findViewById(R.id.addfriend_12);
        button.setOnClickListener(this);
       Glide.with(view.getContext()).load(R.drawable.tupian1).into(imageView_2);
       Glide.with(view.getContext()).load(R.drawable.tupian2).into(imageView_3);
       Glide.with(view.getContext()).load(R.drawable.tupian_3).into(imageView_4);
       Glide.with(view.getContext()).load(R.drawable.tupian_4).into(imageView_5);
       Glide.with(view.getContext()).load(R.drawable.tupian_5).into(imageView_6);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addfriend_12:

                if(checkBox_1.isChecked()||checkBox_2.isChecked()||checkBox_3.isChecked()||checkBox_4.isChecked()||checkBox_5.isChecked()){
                Toast.makeText(v.getContext(),"添加成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent("SELECTFRIENDS");
               // String friends="00000";StringBuilder stringBuilder=new StringBuilder(friends);
                if (checkBox_1.isChecked()){intent.putExtra("check1","a");}
                    if (checkBox_2.isChecked()){intent.putExtra("check2","b");}
                    if (checkBox_3.isChecked()){intent.putExtra("check3","c");}
                    if (checkBox_4.isChecked()){intent.putExtra("check4","d");}
                    if (checkBox_5.isChecked()){intent.putExtra("check5","e");}
                   // intent.putExtra("friendss",friends);

                    LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);





                break;}
                else{
                    Toast.makeText(v.getContext(),"请选择好友",Toast.LENGTH_SHORT).show();break;
                }
                default:break;
        }
    }
}
