package com.sia.siassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class FragmentPerson extends Fragment implements View.OnClickListener {

    public ImageView headportrait;
    public Button register;
    public TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        // headportrait=(view).findViewById(R.id.headportrait);
        register = (view).findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.activity.ACTION_START");
                startActivity(intent);

            }
        });
        // name=(view).findViewById(R.id.name);
        //name.setOnClickListener(this);
        Button underway=(view).findViewById(R.id.pressbutton1);
        Button off=(view).findViewById(R.id.pressbutton2);
        Button unoff=(view).findViewById(R.id.pressbutton3);
        underway.setOnClickListener(this);
        off.setOnClickListener(this);
        unoff.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.pressbutton1:replaceFragment(new FragmentUnderWay());break;
          case R.id.pressbutton2:replaceFragment(new FragmentOff());break;
          case R.id.pressbutton3:replaceFragment(new FragmentUnoff());break;
          default:break;
      }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.personselect,fragment);
        transaction.commit();
    }
}

