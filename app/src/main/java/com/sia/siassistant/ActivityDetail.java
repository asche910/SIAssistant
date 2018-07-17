package com.sia.siassistant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;
import static com.sia.siassistant.FragmentHome.clickTable;
import static com.sia.siassistant.FragmentHome.homeAdapter;
import static com.sia.siassistant.FragmentNew.goalBeanList;

public class ActivityDetail extends Activity implements View.OnClickListener{

    private ImageView img;
    private TextView textName, textContent,  textNum, textDays;
    private Button btnBack, btnClick;

    private GoalBean goalBean;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        index = getIntent().getIntExtra("index", 0);
//        goalBean = (GoalBean) getIntent().getSerializableExtra("goalBean");

        goalBean = goalBeanList.get(index);
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

        if (goalBean.isClick()) {
            btnClick.setText("已打卡！");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_btn_click:
                Calendar nowTime = Calendar.getInstance();
                if (clickTable[nowTime.get(Calendar.MONTH)][nowTime.get(Calendar.DAY_OF_MONTH)] != 1){
                    goalBean.setClick(false);
                }

                if (!goalBean.isClick()) {
                    btnClick.setText("已打卡！");
                    goalBean.setCurDay(goalBean.getCurDay() + 1);
                    textNum.setText(goalBean.getCurDay() + "");
                    homeAdapter.notifyItemChanged(index);

                    goalBean.setClick(true);

                    Calendar now = Calendar.getInstance();
                    clickTable[now.get(Calendar.MONTH)][now.get(Calendar.DAY_OF_MONTH)] = 1;
                    Log.e(TAG, "onClick: " + now.get(Calendar.MONTH) + now.get(Calendar.DAY_OF_MONTH) );

                    Toast.makeText(this, "打卡成功！", Toast.LENGTH_SHORT).show();
                }else{
                    Calendar now = Calendar.getInstance();

                    int hour = 23 - now.get(Calendar.HOUR_OF_DAY);
                    int minute = 60 - now.get(Calendar.MINUTE);
                    Toast.makeText(this, "距离下次打卡还有" + hour + "小时" + minute + "分钟", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.detail_btn_back:
                finish();
                break;
        }
    }
}
