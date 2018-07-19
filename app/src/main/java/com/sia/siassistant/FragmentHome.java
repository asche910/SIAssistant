package com.sia.siassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Random;

import static android.support.constraint.Constraints.TAG;
import static com.sia.siassistant.FragmentNew.goalBeanList;
import static com.sia.siassistant.FragmentNew.pendingIntent;
import static com.sia.siassistant.MainActivity.alarmManager;
import static com.sia.siassistant.MainActivity.resourceIdToUri;

public class FragmentHome extends Fragment implements TimePickerDialog.OnTimeSetListener{

    RecyclerView recyclerViewHome;
    LinearLayoutManager layoutManager;
    public static HomeAdapter homeAdapter;
    public static int newGoalBeanIndex = 1;


    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private SwipeRefreshLayout refreshLayout;
    private FloatingActionButton btnFloating;

    //打卡时间
    public static int[][] clickTable = new int[12][32];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = this.getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |  View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //透明蓝#320671ab
            this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#22000000"));

            getActivity().getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }

        return inflater.inflate(R.layout.fragment_home, container, false );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerViewHome = getActivity().findViewById(R.id.recyclerview_home);
        layoutManager = new LinearLayoutManager(getContext());

        homeAdapter = new HomeAdapter(goalBeanList);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setAdapter(homeAdapter);

        toolbar = getActivity().findViewById(R.id.toolbar_home);
        collapsingToolbarLayout = getActivity().findViewById(R.id.colLayout);
        refreshLayout = getActivity().findViewById(R.id.refresh);
        btnFloating = getActivity().findViewById(R.id.floatButton);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light);

        collapsingToolbarLayout.setTitle("Sign In ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setLogo(getActivity().getDrawable(R.drawable.ic_home_press));
        }


        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ActivityDetail.class);
//                intent.putExtra("goalBean", goalBeanList.get(position));
                intent.putExtra("index", position);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("提示");
                builder.setMessage("确认删除此Item吗？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goalBeanList.remove(position);
                        homeAdapter.notifyItemRemoved(position);
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

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        GoalBean goalBean = new GoalBean(new Random(System.currentTimeMillis()).nextInt(), "Test No." + newGoalBeanIndex, "365", "2018-07-20", "Tes Tes Test!", resourceIdToUri(getActivity(), R.drawable.bg_4_home).toString(), "设置闹钟", 0, false);
                        goalBeanList.add(goalBean);
                        newGoalBeanIndex++;

                        homeAdapter.notifyItemChanged(goalBeanList.size()-1);

                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        btnFloating.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ActivityAlarm.class);
                startActivity(intent);

/*
                Calendar now = Calendar.getInstance();
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                        FragmentHome.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                timePickerDialog.show(getActivity().getFragmentManager(), "TimePickerDialog");
*/


            }
        });
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND, second);


//        Log.e(WheelView.TAG, "onTimeSet:  setTime " + time );

        Intent intent = new Intent("com.sia.siassistant.alarm");
//        intent.setAction("com.sia.siassistant.alarm");
        intent.setComponent(new ComponentName("com.sia.siassistant", "com.sia.siassistant.AlarmReceiver"));

        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 24*60*60*1000 , pendingIntent);

        Toast.makeText(getActivity(), "闹钟创建成功！", Toast.LENGTH_SHORT).show();

    }
}
