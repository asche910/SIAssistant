package com.sia.siassistant;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentMessage extends Fragment {
   private View view;
   private List<Find> findList=new ArrayList<>();
   private List<Find> findList_2=new ArrayList<>();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
view=inflater.inflate(R.layout.fragment_message, container, false );
//initFind1 initFind2
        initFind2();
        initFind1();
RecyclerView recyclerView=(view).findViewById(R.id.recyclerview);
RecyclerView recyclerView2=(view).findViewById(R.id.recyclerview_2);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(linearLayoutManager);
        //FindAdapter_1 adapter2=new FindAdapter_1(findList_2);
        FindAdapter_1 adapter2=new FindAdapter_1(findList_2);
        recyclerView2.setAdapter(adapter2);
        FindAdapter_2 adapter=new FindAdapter_2(findList);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.setmOnItemClickListener(new FindAdapter_2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),HotCotentActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        //recyclerView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_CANCEL,0,0,0));
        return view;
    }


private void initFind2(){
        findList_2.add(new Find(R.drawable.tu_1));
    //findList_2.add(new Find(R.drawable.tu_2_2));
    findList_2.add(new Find(R.drawable.tu_3));
    findList_2.add(new Find(R.drawable.tu_4));
    findList_2.add(new Find(R.drawable.tu_5));
    findList_2.add(new Find(R.drawable.tu_6));


}
private void initFind1(){
        for (int i=1;i<=3;i++)
        {
        findList.add(new Find(R.drawable.tu_7));
    findList.add(new Find(R.drawable.tu_8));
    findList.add(new Find(R.drawable.tu_9));
    findList.add(new Find(R.drawable.tu_10));
    findList.add(new Find(R.drawable.tu_11));}
}




}
