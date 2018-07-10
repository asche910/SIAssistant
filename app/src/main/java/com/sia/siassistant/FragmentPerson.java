package com.sia.siassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentPerson extends Fragment  {

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
        return view;
    }

}

