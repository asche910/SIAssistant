package com.sia.siassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static android.app.Activity.RESULT_OK;
import static com.sia.siassistant.MainActivity.alarmLogic;
import static com.sia.siassistant.MainActivity.alarmManager;
import static com.sia.siassistant.WheelView.TAG;

public class FragmentNew extends Fragment  implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{


    private final String[] DAYS_STRING = new String[]{"3天", "7天", "21天", "30天", "100天", "365天", "永久"};
    private final int[] DAYS_NUM = new int[]{3, 7, 21, 30, 100, 365, 9999};
    private int dayIndex = -1;


    //须本地化存储的变量
    public static List<GoalBean> goalBeanList = new ArrayList<>();
    public static List<AlarmBean> alarmBeanList = new ArrayList<>();



    Button btnSug, btnDate, btnNote, btnAdd;
    EditText editName, editDays, editNote;
    View view;
    WheelView wheelView;
    TextView textDate, textClock;
    Switch clockSwitch;

    //闹钟
    public static PendingIntent pendingIntent;

    Uri uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new, container, false );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        btnSug = getActivity().findViewById(R.id.btn_add_sug);
        btnDate = getActivity().findViewById(R.id.btn_add_date);
        btnNote = getActivity().findViewById(R.id.btn_add_note);
        btnAdd = getActivity().findViewById(R.id.btn_add);
        textDate = getActivity().findViewById(R.id.text_add_date);
        textClock = getActivity().findViewById(R.id.text_add_clock);

        editName = getActivity().findViewById(R.id.edit_add_name);
        editDays = getActivity().findViewById(R.id.edit_add_days);
        editNote = getActivity().findViewById(R.id.edit_add_note);

        clockSwitch = getActivity().findViewById(R.id.switch_add_clock);

        btnSug.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        btnNote.setOnClickListener(this);
        btnAdd.setOnClickListener(this);


        clockSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!buttonView.isPressed()){
                    return;
                }

                if (isChecked){
                    Calendar now = Calendar.getInstance();
                    TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                            FragmentNew.this,
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            true
                    );
                    timePickerDialog.show(getActivity().getFragmentManager(), "TimePickerDialog");
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:

                String name = editName.getText().toString();
                String days = editDays.getText().toString();
                String start = (String)textDate.getText();
                String note = editNote.getText().toString();

                String uri = null;
                try {
                    uri = this.uri.toString();
                } catch (Exception e) {
                    e.printStackTrace();

                }

                String clock = (String)textClock.getText();

                if(name.isEmpty() || days.isEmpty() || start.charAt(0) == '开' ){
                    Toast.makeText(getActivity(), "请补全前三项空白处信息！", Toast.LENGTH_SHORT).show();
                    return;
                }else if(Integer.parseInt(days) == 0){
                    Toast.makeText(getActivity(), "天数输入有误，请重新输入！", Toast.LENGTH_SHORT).show();
                    editDays.setText("");
                }

                GoalBean goalBean = new GoalBean(new Random(System.currentTimeMillis()).nextInt(), name, days, start, note, uri, clock, 0, false);
                goalBeanList.add(goalBean);

                Toast.makeText(getActivity(), "添加成功！", Toast.LENGTH_SHORT).show();
//                System.out.println(name + days + start + note + uri + clock);

                for(GoalBean goalBean1 : goalBeanList){
                    Log.e(TAG, goalBean1.getName() + goalBean1.getDays() + goalBean1.getStart() +
                    goalBean1.getNote() + goalBean1.getUri() + goalBean1.getClock() + goalBean1.getCurDay()
                    );
                }

                this.uri = null;
                break;
            case R.id.btn_add_note:
                //FragmentNew.this与getActivity()有区别
          /*---*/      Matisse.from(FragmentNew.this)
                        .choose(MimeType.ofAll())
                        .countable(true)
                        .maxSelectable(1)
//                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new Glide4Engine())
                        .forResult(2);
                break;
            case R.id.btn_add_date:
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show(getActivity().getFragmentManager(), "Show");

                break;
            case R.id.btn_add_sug:

                view = LayoutInflater.from(getActivity()).inflate(R.layout.wheelview, null);
                wheelView = view.findViewById(R.id.wheelView);
                wheelView.setOffset(2);
                wheelView.setItems(Arrays.asList(DAYS_STRING));
                wheelView.setSeletion(3);
                wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener(){
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        super.onSelected(selectedIndex, item);
                        selectedIndex -= 2;
                        dayIndex = selectedIndex;
                        Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                    }
                });

                dayIndex = 3;
                new AlertDialog.Builder(getActivity())
                        .setTitle("选择天数")
                        .setView(view)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editDays.setText(DAYS_NUM[dayIndex]+"");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                dayIndex = -1;
                            }
                        })
                        .show();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK){
            List<Uri> list = Matisse.obtainResult(data);
            for(Uri uri : list){
                System.out.println(uri);
            }
            uri = list.get(0);
            ContentResolver contentResolver = getActivity().getContentResolver();

            try {
                btnNote.setBackground(Drawable.createFromStream(contentResolver.openInputStream(list.get(0)), null));
                btnNote.setText("");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR);
        int nowMonth = now.get(Calendar.MONTH);
        int nowDay = now.get(Calendar.DAY_OF_MONTH);

        if((nowYear < year) ||
                ((nowYear==year) && ((nowMonth < monthOfYear) ||
                        (nowMonth == monthOfYear && nowDay <= dayOfMonth))
                )
                ){

            String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
            Log.e(TAG, "onDateSet: " + nowMonth );
            String strDate = String.format("%d-%02d-%02d", year, monthOfYear+1, dayOfMonth);
            textDate.setText(strDate);
        }else{
            Toast.makeText(getActivity(), "请选择未来的日期！", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND, second);

        String time = String.format("%02d:%02d", hourOfDay, minute);
        textClock.setText(time);

        Log.e(TAG, "onTimeSet:  setTime " + time );

//        Intent intent = new Intent("com.sia.siassistant.alarm");
//        intent.setComponent(new ComponentName("com.sia.siassistant", "com.sia.siassistant.AlarmReceiver"));

        Random random = new Random(System.currentTimeMillis());
        int id = random.nextInt();

        alarmBeanList.add(new AlarmBean(id, time, "1111111", true));

        alarmLogic();
//        pendingIntent = PendingIntent.getBroadcast(getContext(), id, intent, 0);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 24*60*60*1000 , pendingIntent);


    }
}
