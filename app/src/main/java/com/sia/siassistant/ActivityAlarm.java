package com.sia.siassistant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import static com.sia.siassistant.FragmentNew.alarmBeanList;
import static com.sia.siassistant.FragmentNew.goalBeanList;
import static com.sia.siassistant.MyApplication.getContext;

public class ActivityAlarm extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    public static AlarmAdapter alarmAdapter;

    Button btnNew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |  View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //透明蓝#320671ab
            getWindow().setStatusBarColor(Color.parseColor("#22000000"));

            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }

        btnNew = findViewById(R.id.alarm_btn_new);

        recyclerView = findViewById(R.id.recyclerview_alarm);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        alarmAdapter = new AlarmAdapter(alarmBeanList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(alarmAdapter);

        alarmAdapter.setOnItemClickListener(new AlarmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), ActivityAlarmDetail.class);
                intent.putExtra("alarmItem", position);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAlarm.this);
                builder.setTitle("提示");
                builder.setMessage("确认删除此闹钟吗？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alarmBeanList.remove(position);
                        alarmAdapter.notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });


        btnNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityAlarmDetail.class);
                intent.putExtra("alarmItem", -1);
                startActivity(intent);
            }
        });
    }
}
