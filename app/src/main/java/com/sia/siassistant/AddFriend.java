package com.sia.siassistant;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class AddFriend extends Fragment implements View.OnClickListener {
    private ImageView imageView;
    private CheckBox checkBox;
    private Button addButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.friendline,container,false);
        imageView=(view).findViewById(R.id.friend1);
        checkBox=(view).findViewById(R.id.queren);
        addButton=(view).findViewById(R.id.addfriend_12);
        addButton.setOnClickListener(this);
        return view;
    }
// 好友里添加数据
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),"添加成功",Toast.LENGTH_SHORT).show();
    }
}
