package com.sia.siassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.sia.siassistant.FragmentHappen.str_1;
import static com.sia.siassistant.FragmentHappen.str_2;
import static com.sia.siassistant.FragmentHome.clickTable;
import static com.sia.siassistant.FragmentNew.alarmBeanList;
import static com.sia.siassistant.FragmentNew.goalBeanList;
import static com.sia.siassistant.FragmentNew.pendingIntent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static ViewPager viewPager;
   // FragmentPerson fragmentPerson=(FragmentPerson) getFragmentManager().findFragmentByTag()
    List<Fragment> fragmentList = new ArrayList<>();
    FragAdapter fragAdapter;
   // RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);


    //底部Tab标签栏
    RelativeLayout layoutHome, layoutHappen, layoutMessage, layoutPerson;
    ImageView imgHome, imgHappen, imgNew, imgMessage, imgPerson;
    TextView textHome, textHappen, textNew, textMessage, textPerson;
    //Button button=(Button)findViewById(R.id.addfriend_12);



    //从资源文件获得对应Uri
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    //闹钟放入此处初始化
    public static AlarmManager alarmManager;

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        DataSupport.deleteAll("GoalBean");
        for(GoalBean goalBean: goalBeanList){
            goalBean.save();
        }

        saveToSD(clickTable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        final Intent intent=getIntent();
        String user__name=intent.getStringExtra("extre");
        Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
        CheckBox checkBox=(CheckBox)findViewById(R.id.checkBox);
        Button button=(Button)findViewById(R.id.addfriend_12);

        //彩色33B5E5
        //原色666363
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imgHome.setBackground(getDrawable(R.drawable.ic_home_press));
            textHome.setTextColor(Color.parseColor("#33B5E5"));
        }

    }

    void init(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                clickTable =  retriveFromSD();
                alarmLogic();
            }
        }).start();


        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

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

//        DataSupport.deleteAll("GoalBean");
        goalBeanList = DataSupport.findAll(GoalBean.class);

    /*    GoalBean goalBean_1 = new GoalBean("坚持跑步", "100", "2018-07-11", str_1, resourceIdToUri(getApplicationContext(), R.drawable.bg_2).toString(), "设置闹钟", 0, false);
        GoalBean goalBean_2 = new GoalBean("Test Message!", "365", "2018-07-20", str_2, resourceIdToUri(getApplicationContext(), R.drawable.bg_4_home).toString(), "设置闹钟", 0, false);
        goalBeanList.add(goalBean_1);
        goalBeanList.add(goalBean_2);
        goalBean_1.save();
        goalBean_2.save();
*/

        AlarmBean alarmBean_1 = new AlarmBean(11, "08:15", "1111111", false);
        AlarmBean alarmBean_2 = new AlarmBean(22, "20:30", "1111111", false);
        alarmBeanList.add(alarmBean_1);
        alarmBeanList.add(alarmBean_2);



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

    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    public static  void saveToSD(int[][] a){
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/ArrayData.txt");
        try {
            OutputStream out = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 32; j++) {
                    writer.write(a[i][j] + " ");
                }
                writer.newLine();
            }
            writer.flush();
            writer.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

            Log.e("aaa", "saveToSD: --------------->" );
        }

    }

    public static int[][] retriveFromSD(){
        int[][] b = new int[12][32];
        //读取
        try {
            InputStream in = new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "/ArrayData.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] strs = line.split(" ");
                for (int j = 0; j < 32; j++) {
                    b[i][j] = Integer.parseInt(strs[j].trim());
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("aaa", "retriveFromSD: --------------------->" );
        }
        return b;
    }

    public static void alarmLogic(){
        for(AlarmBean alarmBean: alarmBeanList){
            if(alarmBean.isOn()){
                Intent intent = new Intent("com.sia.siassistant.alarm");
                intent.setComponent(new ComponentName("com.sia.siassistant", "com.sia.siassistant.AlarmReceiver"));

                pendingIntent = PendingIntent.getBroadcast(MyApplication.getContext(), alarmBean.getId(), intent, 0);
                for(int i = 0; i < 7; i++){
                    int n = Integer.parseInt(alarmBean.getDays().charAt(i) + "");
                    Calendar now = Calendar.getInstance();

                    if(i == now.get(Calendar.DAY_OF_WEEK)-1){
                        if(n == 0){
                            alarmManager.cancel(pendingIntent);
                        }else{
                            now.set(Calendar.HOUR_OF_DAY, Integer.parseInt(alarmBean.getTime().substring(0, 2)));
                            now.set(Calendar.MINUTE, Integer.parseInt(alarmBean.getTime().substring(3, 5)));

                            alarmManager.setRepeating(
                                    AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), 24*60*60*1000 , pendingIntent);
                        }
                    }

                }

            }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
}
