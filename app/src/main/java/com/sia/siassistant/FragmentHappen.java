package com.sia.siassistant;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentHappen extends Fragment {

    public static List<EventBean> eventBeanList = new ArrayList<>();
    RecyclerView recycHappen;
    LinearLayoutManager layoutManager;
    EventAdapter eventAdapter;

    static String str_1 = "早起的鸟儿有虫吃， 首先做到23：50前躺下，6：30早起打卡！充满元气的一天！！！";
    static String str_2 = "2016年10月28日 - 这篇文章主要为大家详细介绍了Android RefreshLayout实现下拉刷新布局,具有一定的参考价值,感兴趣的小伙伴们可以参考一下项目中需要下拉刷新的功能,但...";

    static {
        List<Uri> list = new ArrayList<>();
        for (int n = 0; n < 2; n++) {
            String strUri = String.format("content://media/external/images/media/31422");
            list.add(Uri.parse(strUri + 4));
            list.add(Uri.parse(strUri + 5));
            list.add(Uri.parse(strUri + 6));
            list.add(Uri.parse("content://media/external/images/media/860774"));
        }

        EventBean eventBean_1 = new EventBean("content://media/external/images/media/860775","会飞的企鹅", str_1, "上午10：24", true, list);
        EventBean eventBean_2 = new EventBean("content://media/external/images/media/860774", "会飞的肥猪", str_2, "上午12：24 ", false, null);
        EventBean eventBean_3 = new EventBean("content://media/external/images/media/860776","会飞的企鹅", str_1, "刚刚", true, list);

        eventBeanList.add(eventBean_1);
        eventBeanList.add(eventBean_2);
        eventBeanList.add(eventBean_3);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_happen, container, false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        eventAdapter = new EventAdapter(eventBeanList);

        recycHappen = getActivity().findViewById(R.id.recyclerview_happen);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recycHappen.setLayoutManager(layoutManager);
        recycHappen.setAdapter(eventAdapter);


    }
}
