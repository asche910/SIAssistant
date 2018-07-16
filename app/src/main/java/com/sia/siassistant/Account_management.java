package com.sia.siassistant;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Account_management extends AppCompatActivity implements View.OnClickListener{
private Button button_shi,button_fou;
private Handler handler=new Handler(){
    public void handleMessage(Message msg){
        switch (msg.what){
            case 1:button_shi.setText("否");button_fou.setText("是");break;
            case 2:button_fou.setText("否");button_shi.setText("是");break;
            default:break;
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        TextView textView=(TextView)findViewById(R.id.account_user_name);
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        String user_name=sharedPreferences.getString("user_name","");
        textView.setText("您的用户名是--->"+user_name+"\n"+"是否要更改密码?");
        button_shi=(Button)findViewById(R.id.shi);
        button_fou=(Button)findViewById(R.id.fou);
        button_shi.setOnClickListener(this);
        button_fou.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shi:if (button_shi.getText().equals("否")){finish();}
            else new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }
                }).start();break;
            case R.id.fou:if (button_fou.getText().equals("否")){finish();}
            else new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=2;
                        handler.sendMessage(message);
                    }
                }).start();break;
        }
    }
}
