package com.sia.siassistant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentPerson extends Fragment implements View.OnClickListener {
    private Button join;
    FrameLayout frameLayout;
    int IMAGE_REFRESH=5;
    public ImageView imageView_1;
    public static CircleImageView headportrait;
    public Button register;
    public TextView name;
    private FloatingActionButton floatingActionButton;
  private   MyBoardCasst myBoardCasst=new MyBoardCasst();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_person, container, false);
        // headportrait=(view).findViewById(R.id.headportrait);
        register = (view).findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.activity.ACTION_START");
                //startActivity(intent);
                startActivityForResult(intent,1);

            }
        });
        // name=(view).findViewById(R.id.name);
        //name.setOnClickListener(this);
        Button underway=(view).findViewById(R.id.pressbutton1);
        Button off=(view).findViewById(R.id.pressbutton2);
        Button unoff=(view).findViewById(R.id.pressbutton3);
      //  Button addFriend=(view).findViewById(R.id.addFriend);
        join=(view).findViewById(R.id.denglu);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),JoinActivity.class);
                startActivity(intent);
            }
        });

        underway.setOnClickListener(this);
        off.setOnClickListener(this);
        unoff.setOnClickListener(this);
        name=(view).findViewById(R.id.name);
        /*---------------------*/



        floatingActionButton=(view).findViewById(R.id.floatButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"fuck",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(view.getContext(),"Fuck",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        headportrait=(view).findViewById(R.id.headportrait);
        Glide.with(getContext()).load(R.drawable.tupian_7).into(headportrait);

        /*------------------------------------------------------------*/


        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("REGISTER");

        getActivity().registerReceiver(myBoardCasst,intentFilter);

        frameLayout=(view).findViewById(R.id.personselect);
        imageView_1=(view).findViewById(R.id.photo_main);
        Glide.with(view.getContext()).load(R.drawable.tupian).into(imageView_1);
        SharedPreferences preferences=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
        String nam1=preferences.getString("user_name","");
        name.setText(nam1);

        return view;
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:String user_name=data.getStringExtra("user_name");
            name.setText(user_name);break;
        }
    }*/
   class MyBoardCasst extends BroadcastReceiver{
       @Override
       public void onReceive(Context context, Intent intent) {
           String username=intent.getStringExtra("refrechtext");
           name.setText(username);
       }
   }


    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.pressbutton1:imageView_1.setImageDrawable(null); replaceFragment(new FragmentPhoto());break;
          case R.id.pressbutton2: imageView_1.setImageDrawable(null);replaceFragment(new Commend_fragment());break;
          case R.id.pressbutton3: imageView_1.setImageDrawable(null);replaceFragment(new FragmentSet());break;
          default:break;
      }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.personselect,fragment);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
       getActivity().unregisterReceiver(myBoardCasst);
        super.onDestroy();
    }
}

