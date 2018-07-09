package com.sia.siassistant;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    FragAdapter fragAdapter;

    //底部Tab标签栏
    RelativeLayout layoutHome, layoutHappen, layoutMessage, layoutPerson;
    ImageView imgHome, imgHappen, imgNew, imgMessage, imgPerson;
    TextView textHome, textHappen, textNew, textMessage, textPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //彩色33B5E5
        //原色666363
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imgHome.setBackground(getDrawable(R.drawable.ic_home_press));
            textHome.setTextColor(Color.parseColor("#33B5E5"));
        }
    }

    void init(){
        fragmentList.add(new FragmentHome());
        fragmentList.add(new FragmentHappen());
        fragmentList.add(new FragmentNew());
        fragmentList.add(new FragmentMessage());
        fragmentList.add(new FragmentPerson());

        viewPager = findViewById(R.id.viewPager_main);
        fragAdapter = new FragAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragAdapter);
        viewPager.setOnPageChangeListener(new MyViewPagerListener());

        layoutHome = findViewById(R.id.tab_layout_home);
        layoutHappen = findViewById(R.id.tab_layout_happen);
        layoutMessage = findViewById(R.id.tab_layout_message);
        layoutPerson = findViewById(R.id.tab_layout_person);

        imgHome = findViewById(R.id.tab_img_home);
        imgHappen = findViewById(R.id.tab_img_happen);
        imgNew = findViewById(R.id.tab_img_new);
        imgMessage = findViewById(R.id.tab_img_message);
        imgPerson = findViewById(R.id.tab_img_person);

        textHome = findViewById(R.id.tab_home);
        textHappen = findViewById(R.id.tab_happen);
        textNew = findViewById(R.id.tab_new);
        textMessage = findViewById(R.id.tab_message);
        textPerson = findViewById(R.id.tab_person);

        layoutHome.setOnClickListener(this);
        layoutHappen.setOnClickListener(this);
        layoutMessage.setOnClickListener(this);
        layoutPerson.setOnClickListener(this);
        imgNew.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_img_new:
                viewPager.setCurrentItem(2);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imgHome.setBackground(getDrawable(R.drawable.ic_home));
                    imgHappen.setBackground(getDrawable(R.drawable.ic_happen));
                    imgNew.setBackground(getDrawable(R.drawable.ic_new_press));
                    imgMessage.setBackground(getDrawable(R.drawable.ic_message));
                    imgPerson.setBackground(getDrawable(R.drawable.ic_person));
                    textHome.setTextColor(Color.parseColor("#666363"));
                    textHappen.setTextColor(Color.parseColor("#666363"));
                    textNew.setTextColor(Color.parseColor("#33B5E5"));
                    textMessage.setTextColor(Color.parseColor("#666363"));
                    textPerson.setTextColor(Color.parseColor("#666363"));
                }
                break;
            case R.id.tab_layout_person:
                viewPager.setCurrentItem(4);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imgHome.setBackground(getDrawable(R.drawable.ic_home));
                    imgHappen.setBackground(getDrawable(R.drawable.ic_happen));
                    imgNew.setBackground(getDrawable(R.drawable.ic_new));
                    imgMessage.setBackground(getDrawable(R.drawable.ic_message));
                    imgPerson.setBackground(getDrawable(R.drawable.ic_person_press));
                    textHome.setTextColor(Color.parseColor("#666363"));
                    textHappen.setTextColor(Color.parseColor("#666363"));
                    textNew.setTextColor(Color.parseColor("#666363"));
                    textMessage.setTextColor(Color.parseColor("#666363"));
                    textPerson.setTextColor(Color.parseColor("#33B5E5"));
                }
                break;
            case R.id.tab_layout_message:
                viewPager.setCurrentItem(3);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imgHome.setBackground(getDrawable(R.drawable.ic_home));
                    imgHappen.setBackground(getDrawable(R.drawable.ic_happen));
                    imgNew.setBackground(getDrawable(R.drawable.ic_new));
                    imgMessage.setBackground(getDrawable(R.drawable.ic_message_press));
                    imgPerson.setBackground(getDrawable(R.drawable.ic_person));
                    textHome.setTextColor(Color.parseColor("#666363"));
                    textHappen.setTextColor(Color.parseColor("#666363"));
                    textNew.setTextColor(Color.parseColor("#666363"));
                    textMessage.setTextColor(Color.parseColor("#33B5E5"));
                    textPerson.setTextColor(Color.parseColor("#666363"));
                }
                break;
            case R.id.tab_layout_happen:
                viewPager.setCurrentItem(1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imgHome.setBackground(getDrawable(R.drawable.ic_home));
                    imgHappen.setBackground(getDrawable(R.drawable.ic_happen_press));
                    imgNew.setBackground(getDrawable(R.drawable.ic_new));
                    imgMessage.setBackground(getDrawable(R.drawable.ic_message));
                    imgPerson.setBackground(getDrawable(R.drawable.ic_person));
                    textHome.setTextColor(Color.parseColor("#666363"));
                    textHappen.setTextColor(Color.parseColor("#33B5E5"));
                    textNew.setTextColor(Color.parseColor("#666363"));
                    textMessage.setTextColor(Color.parseColor("#666363"));
                    textPerson.setTextColor(Color.parseColor("#666363"));
                }
                break;
            case R.id.tab_layout_home:
                viewPager.setCurrentItem(0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imgHome.setBackground(getDrawable(R.drawable.ic_home_press));
                    imgHappen.setBackground(getDrawable(R.drawable.ic_happen));
                    imgNew.setBackground(getDrawable(R.drawable.ic_new));
                    imgMessage.setBackground(getDrawable(R.drawable.ic_message));
                    imgPerson.setBackground(getDrawable(R.drawable.ic_person));
                    textHome.setTextColor(Color.parseColor("#33B5E5"));
                    textHappen.setTextColor(Color.parseColor("#666363"));
                    textNew.setTextColor(Color.parseColor("#666363"));
                    textMessage.setTextColor(Color.parseColor("#666363"));
                    textPerson.setTextColor(Color.parseColor("#666363"));
                }
                break;
        }
    }

    class MyViewPagerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 4:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        imgHome.setBackground(getDrawable(R.drawable.ic_home));
                        imgHappen.setBackground(getDrawable(R.drawable.ic_happen));
                        imgNew.setBackground(getDrawable(R.drawable.ic_new));
                        imgMessage.setBackground(getDrawable(R.drawable.ic_message));
                        imgPerson.setBackground(getDrawable(R.drawable.ic_person_press));
                        textHome.setTextColor(Color.parseColor("#666363"));
                        textHappen.setTextColor(Color.parseColor("#666363"));
                        textNew.setTextColor(Color.parseColor("#666363"));
                        textMessage.setTextColor(Color.parseColor("#666363"));
                        textPerson.setTextColor(Color.parseColor("#33B5E5"));
                    }
                    break;
                case 3:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        imgHome.setBackground(getDrawable(R.drawable.ic_home));
                        imgHappen.setBackground(getDrawable(R.drawable.ic_happen));
                        imgNew.setBackground(getDrawable(R.drawable.ic_new));
                        imgMessage.setBackground(getDrawable(R.drawable.ic_message_press));
                        imgPerson.setBackground(getDrawable(R.drawable.ic_person));
                        textHome.setTextColor(Color.parseColor("#666363"));
                        textHappen.setTextColor(Color.parseColor("#666363"));
                        textNew.setTextColor(Color.parseColor("#666363"));
                        textMessage.setTextColor(Color.parseColor("#33B5E5"));
                        textPerson.setTextColor(Color.parseColor("#666363"));
                    }
                    break;
                case 2:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        imgHome.setBackground(getDrawable(R.drawable.ic_home));
                        imgHappen.setBackground(getDrawable(R.drawable.ic_happen));
                        imgNew.setBackground(getDrawable(R.drawable.ic_new_press));
                        imgMessage.setBackground(getDrawable(R.drawable.ic_message));
                        imgPerson.setBackground(getDrawable(R.drawable.ic_person));
                        textHome.setTextColor(Color.parseColor("#666363"));
                        textHappen.setTextColor(Color.parseColor("#666363"));
                        textNew.setTextColor(Color.parseColor("#33B5E5"));
                        textMessage.setTextColor(Color.parseColor("#666363"));
                        textPerson.setTextColor(Color.parseColor("#666363"));
                    }
                    break;
                case 1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        imgHome.setBackground(getDrawable(R.drawable.ic_home));
                        imgHappen.setBackground(getDrawable(R.drawable.ic_happen_press));
                        imgNew.setBackground(getDrawable(R.drawable.ic_new));
                        imgMessage.setBackground(getDrawable(R.drawable.ic_message));
                        imgPerson.setBackground(getDrawable(R.drawable.ic_person));
                        textHome.setTextColor(Color.parseColor("#666363"));
                        textHappen.setTextColor(Color.parseColor("#33B5E5"));
                        textNew.setTextColor(Color.parseColor("#666363"));
                        textMessage.setTextColor(Color.parseColor("#666363"));
                        textPerson.setTextColor(Color.parseColor("#666363"));
                    }
                    break;
                case 0:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        imgHome.setBackground(getDrawable(R.drawable.ic_home_press));
                        imgHappen.setBackground(getDrawable(R.drawable.ic_happen));
                        imgNew.setBackground(getDrawable(R.drawable.ic_new));
                        imgMessage.setBackground(getDrawable(R.drawable.ic_message));
                        imgPerson.setBackground(getDrawable(R.drawable.ic_person));
                        textHome.setTextColor(Color.parseColor("#33B5E5"));
                        textHappen.setTextColor(Color.parseColor("#666363"));
                        textNew.setTextColor(Color.parseColor("#666363"));
                        textMessage.setTextColor(Color.parseColor("#666363"));
                        textPerson.setTextColor(Color.parseColor("#666363"));
                    }
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
