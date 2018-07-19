package com.sia.siassistant;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.sia.siassistant.FragmentPerson.headportrait;

public class Register extends AppCompatActivity implements View.OnClickListener {
    public static final int CHOOSE_PHOTO = 2;
    public static final int TAKE_PHOTO=1;
    private  ImageView picture;
    private Uri imageUri;
    private Button button_1;
    private Button button_2,button_3;
    private EditText editText_1;
    private EditText editText_2;
    private TextView textView_1;
    private TextView textView_2;
   public String username,jobname;
   public SharedPreferences pref;
   public SharedPreferences.Editor editor;
   public Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regster);
        picture=(ImageView)findViewById(R.id.headportait2);
        ImageView choose = (ImageView) findViewById(R.id.bendixiangche);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Register.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Register.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });
       /* ------------------------------------ */
         button_1=(Button)findViewById(R.id.button_1);
         button_2=(Button)findViewById(R.id.button_2);
         button_3=(Button)findViewById(R.id.zhuce);
        editText_1=(EditText)findViewById(R.id.regster_name);
         editText_2=(EditText)findViewById(R.id.regster_job);
         textView_1=(TextView)findViewById(R.id.user_name);
         textView_2=(TextView)findViewById(R.id.jobname);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        /*---------------------*/
     //pref= PreferenceManager.getDefaultSharedPreferences(this);//editor=pref.edit();
        pref=getSharedPreferences("data",MODE_PRIVATE);
        editor=getSharedPreferences("data",MODE_PRIVATE).edit();
      //username=pref.getString("user_name","");
      //jobname=pref.getString("job_name","");
     // textView_1.setText(username);
      //textView_2.setText(jobname);
      Intent intent=new Intent();
      intent.putExtra("user_name",username);
      intent.putExtra("job_name",jobname);
      setResult(RESULT_OK,intent);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1: username=editText_1.getText().toString();
            editor.putString("user_name",username);editor.apply();
            textView_1.setText(username);Intent intent1=new Intent("REGISTER");intent1.putExtra("refrechtext",username);sendBroadcast(intent1);break;
            case R.id.button_2: jobname=editText_2.getText().toString();
            editor.putString("job_name",jobname);editor.apply();
            textView_2.setText(jobname);break;
            case R.id.zhuce: //SendMessege();
                EditText editText=(EditText)findViewById(R.id.shu_ru_mi_ma);
                String mima=editText.getText().toString();
                editor.putString("mima",mima);
                username=editText_1.getText().toString();editor.putString("user_name",username);editor.apply();
               Intent intent=new Intent(Register.this,MainActivity.class);
               /*---*/ Intent intent2=new Intent("REGISTER");intent2.putExtra("refrechtext",username);
               intent.putExtra("extre",username);
                startActivity(intent);break;

        }
    }
    //private void SendMessege(){
        //Intent intent=new Intent(Register.this,MainActivity.class);
       // String messege=editText_1.getText().toString();
       // intent.putExtra("EXTRE",messege);
       // startActivity(intent);
  //  }

     public void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                   // Toast.makeText(Register.this, "打开失败", Toast.LENGTH_SHORT).show();
                    openAlbum();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
               if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
@TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            String docID=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id =docID.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docID));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        displayImage(imagePath);
    }
    private void handleImageBeforKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }
    private  String getImagePath(Uri uri,String selection){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }return  path;
    }
    private void displayImage(String imagePath){
        if (imagePath!=null){
             bitmap= BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);


        }
        else {
            Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show();
        }
    }
}
