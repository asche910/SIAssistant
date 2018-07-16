package com.sia.siassistant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ActivityDetail extends Activity implements View.OnClickListener{

    private ImageView img;
    private TextView textName, textContent,  textNum, textDays;
    private Button btnBack, btnClick;

    GoalBean goalBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        goalBean = (GoalBean) getIntent().getSerializableExtra("goalBean");

        img = findViewById(R.id.detail_img);
        textName = findViewById(R.id.detail_text_name);
        textContent = findViewById(R.id.detail_text_content);
        textNum = findViewById(R.id.detail_text_num);
        textDays = findViewById(R.id.detail_text_days);
        btnBack = findViewById(R.id.detail_btn_back);
        btnClick = findViewById(R.id.detail_btn_click);

        btnBack.setOnClickListener(this);
        btnClick.setOnClickListener(this);

        Glide.with(getApplicationContext())
                .load(goalBean.getUri())
                .into(img);

        textName.setText(goalBean.getName());
        textContent.setText(goalBean.getNote());
        textNum.setText(goalBean.getCurDay() + "");
        textDays.setText(goalBean.getDays());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_btn_click:
                btnClick.setText("已打卡！");
                goalBean.setCurDay(goalBean.getCurDay() + 1);

                break;
            case R.id.detail_btn_back:
                finish();
                break;
        }
    }
}
