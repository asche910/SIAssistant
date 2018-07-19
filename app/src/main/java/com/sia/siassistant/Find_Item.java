package com.sia.siassistant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Find_Item extends AppCompatActivity {
    private List<Find> findList=new ArrayList<>();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__item);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
      View view=layoutInflater.inflate(R.layout.activity_xing_shou,null);
        initImage();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview_3);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FindAdapter_2 adapter_2=new FindAdapter_2(findList);
        recyclerView.setAdapter(adapter_2);
        adapter_2.setmOnItemClickListener(new FindAdapter_2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(Find_Item.this,HotCotentActivity.class);
                intent.putExtra("position1",position);
                intent.putExtra("tuao1",2);
                startActivity(intent);

            }
        });
        /*-----------------------------------------------*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |  View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //透明蓝#320671ab
            getWindow().setStatusBarColor(Color.parseColor("#22000000"));

            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }
    }
    private void initImage(){
        for (int i=1;i<=3;i++){
            findList.add(new Find(R.drawable.t_1));
            findList.add(new Find(R.drawable.t_2));
            findList.add(new Find(R.drawable.t_3));
            findList.add(new Find(R.drawable.t_4));
            findList.add(new Find(R.drawable.t_5));
            findList.add(new Find(R.drawable.t_6));

        }

    }
}
