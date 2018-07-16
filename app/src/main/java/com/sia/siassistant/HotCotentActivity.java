package com.sia.siassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.Random;

public class HotCotentActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView,imageView_2,imageView_3,imageView_4;
    TextView textView;
    Button button;
    private int x=-1,n;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:textView.setText(n+"赞");break;
                case 2:button.setText("已关注");
                default:break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_cotent);
        Intent intent=getIntent();
        int position=intent.getIntExtra("position",0);
        //Toast.makeText(HotCotentActivity.this,"ad",Toast.LENGTH_SHORT).show();
        imageView=(ImageView)findViewById(R.id.hot_content_imageview);
        ImageView imageView_5=(ImageView)findViewById(R.id.hot_pinlun);
        imageView_5.setOnClickListener(this);
       imageView_2=(ImageView)findViewById(R.id.hot_check);
       imageView_3=(ImageView)findViewById(R.id.a);
       imageView_4=(ImageView)findViewById(R.id.fenxiang);
       button=(Button)findViewById(R.id.c);
       imageView_4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               shareSingleImage(v);
           }
       });
       imageView_3.setOnClickListener(this);
       imageView_2.setOnClickListener(this);
       textView=(TextView)findViewById(R.id.hot_zhan);
       n=(int)Math.random()*13+23;
       textView.setText(n+"赞");
       button.setOnClickListener(this);
         initImage(position);
    }
    private  void initImage(int n){
        switch (n){
            case 0: case 5: case 10:
                Glide.with(HotCotentActivity.this).load(R.drawable.tu_7).into(imageView);break;
            case 1: case 6: case 11: Glide.with(HotCotentActivity.this).load(R.drawable.tu_8).into(imageView);break;
            case 2: case 7: case 12:Glide.with(HotCotentActivity.this).load(R.drawable.tu_9).into(imageView);break;
            case 3: case 8: case 13:Glide.with(HotCotentActivity.this).load(R.drawable.tu_10).into(imageView);break;
            case 4: case 9: case 14:Glide.with(HotCotentActivity.this).load(R.drawable.tu_11).into(imageView);break;
        }
    }

    @Override
    public void onClick(View v) {
        x=-x;
        switch (v.getId()){
            case R.id.hot_check: if (x>0){imageView_2.setImageResource(R.drawable.ic_favorite_border_black_24dp);n=n+1;new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
            }).start();}
            else if(x<0){imageView_2.setImageResource(R.drawable.ic_favorite_border_black_24dp_1);n=n-1;new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
            }).start();}break;
            case R.id.a:finish();break;
            case R.id.c:new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message=new Message();
                    message.what=2;
                    handler.sendMessage(message);
                }
            }).start();

        }
    }
    public void shareSingleImage(View view){
        String imagePath= Environment.getExternalStorageDirectory()+ File.separator+"text.jpg";
        Uri imageUri=Uri.fromFile(new File(imagePath));
        Intent shareIntent=new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent,"分享到"));
    }
}
