package com.sia.siassistant;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.support.constraint.Constraints.TAG;
import static com.sia.siassistant.FragmentNew.goalBeanList;

public class FragmentHome extends Fragment {

    RecyclerView recyclerViewHome;
    LinearLayoutManager layoutManager;
    public static HomeAdapter homeAdapter;
    public static int newGoalBeanIndex = 1;


    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private SwipeRefreshLayout refreshLayout;
    private FloatingActionButton btnFloating;

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
                intent.putExtra("goalBean", goalBeanList.get(position));
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        GoalBean goalBean = new GoalBean("Test No." + newGoalBeanIndex, "365", "2018-07-20", "Tes Tes Test!", "content://media/external/images/media/835163", "设置闹钟", 0);
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
//                viewPager.setCurrentItem(2);

                Intent intent = new Intent(getContext(), ActivityAlarm.class);
                startActivity(intent);
            }
        });
    }
}
