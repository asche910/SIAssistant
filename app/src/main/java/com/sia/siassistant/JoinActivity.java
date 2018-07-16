package com.sia.siassistant;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_1,editText_2;
   private Button button_1,button_2;
   SharedPreferences pref;
   String username;
   String join_code_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        button_1=(Button)findViewById(R.id.join);
        button_2=(Button)findViewById(R.id.out);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        editText_1=(EditText)findViewById(R.id.join_name);
        editText_2=(EditText)findViewById(R.id.join_code);
        pref=getSharedPreferences("data",MODE_PRIVATE);
        username=pref.getString("user_name","");
        join_code_1=pref.getString("mima","");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.join: if (editText_1.getText().toString().equals(username)&&editText_2.getText().toString().equals(join_code_1)){
                Toast.makeText(JoinActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(JoinActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();break;
            }
            case R.id.out:finish();break;

        }

    }
}
