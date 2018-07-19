package com.sia.siassistant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Random;

import static com.sia.siassistant.ActivityAlarm.alarmAdapter;
import static com.sia.siassistant.FragmentNew.alarmBeanList;
import static com.sia.siassistant.MainActivity.alarmLogic;

public class ActivityAlarmDetail extends Activity implements CompoundButton.OnCheckedChangeListener,
        View.OnClickListener,  TimePickerDialog.OnTimeSetListener{

    Switch aSwitch;
    Button btnTime, btnSave;
    TextView textTime;
    CheckBox checkBox_0, checkBox_1, checkBox_2, checkBox_3, checkBox_4, checkBox_5, checkBox_6;

    private int position;
    private int[] dayArray = new int[8];
    private AlarmBean alarmBean;

    private String tempTime;
    private AlarmBean tempAlarm = new AlarmBean();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);

        aSwitch = findViewById(R.id.switch_detail);
        btnTime = findViewById(R.id.alarm_btn_time);
        btnSave = findViewById(R.id.alarm_btn_save);
        textTime = findViewById(R.id.alarm_text_time);
        checkBox_0 = findViewById(R.id.check_day_0);
        checkBox_1 = findViewById(R.id.check_day_1);
        checkBox_2 = findViewById(R.id.check_day_2);
        checkBox_3 = findViewById(R.id.check_day_3);
        checkBox_4 = findViewById(R.id.check_day_4);
        checkBox_5 = findViewById(R.id.check_day_5);
        checkBox_6 = findViewById(R.id.check_day_6);

        btnTime.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        checkBox_0.setOnCheckedChangeListener(this);
        checkBox_1.setOnCheckedChangeListener(this);
        checkBox_2.setOnCheckedChangeListener(this);
        checkBox_3.setOnCheckedChangeListener(this);
        checkBox_4.setOnCheckedChangeListener(this);
        checkBox_5.setOnCheckedChangeListener(this);
        checkBox_6.setOnCheckedChangeListener(this);

        position = getIntent().getIntExtra("alarmItem", 0);

        if (position > -1) {
            alarmBean = alarmBeanList.get(position);
            tempTime = alarmBean.getTime();

            for(int i = 0; i < 7; i++){
                int n = Integer.parseInt(alarmBean.getDays().charAt(i) + "");
                dayArray[i] = n;

                if (n == 1) {
                    switch (i){
                        case 0:
                            checkBox_0.setChecked(true);
                            break;
                        case 1:
                            checkBox_1.setChecked(true);
                            break;
                        case 2:
                            checkBox_2.setChecked(true);
                            break;
                        case 3:
                            checkBox_3.setChecked(true);
                            break;
                        case 4:
                            checkBox_4.setChecked(true);
                            break;
                        case 5:
                            checkBox_5.setChecked(true);
                            break;
                        case 6:
                            checkBox_6.setChecked(true);
                            break;
                    }
                }
            }

            aSwitch.setChecked(alarmBean.isOn());

            textTime.setText(alarmBean.getTime());
        }else{
            aSwitch.setChecked(true);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alarm_btn_save:
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i < 7; i++){
                    stringBuilder.append(dayArray[i]);
                }

                if (position > -1) {
                    alarmBean.setTime(tempTime);
                    alarmBean.setOn(aSwitch.isChecked());
                    alarmBean.setDays(stringBuilder.toString());
                    Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
                }else{

                    if(tempTime == null || tempTime.equals("")){
                        Toast.makeText(this, "请设置闹钟时间！", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    tempAlarm.setOn(aSwitch.isChecked());
                    tempAlarm.setDays(stringBuilder.toString());
                    tempAlarm.setTime(tempTime);
                    tempAlarm.setId(new Random(System.currentTimeMillis()).nextInt());
                    alarmBeanList.add(tempAlarm);
                    Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
                }
                alarmAdapter.notifyDataSetChanged();
                finish();
                alarmLogic();
                break;
            case R.id.alarm_btn_time:
                Calendar now = Calendar.getInstance();
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                        this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                timePickerDialog.show(getFragmentManager(), "TimePickerDialog");
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.check_day_0:
                if(isChecked){
                    dayArray[0] = 1;
                }else{
                    dayArray[0] = 0;
                }
                break;
            case R.id.check_day_1:
                if(isChecked){
                    dayArray[1] = 1;
                }else{
                    dayArray[1] = 0;
                }
                break;
            case R.id.check_day_2:
                if(isChecked){
                    dayArray[2] = 1;
                }else{
                    dayArray[2] = 0;
                }
                break;
            case R.id.check_day_3:
                if(isChecked){
                    dayArray[3] = 1;
                }else{
                    dayArray[3] = 0;
                }
                break;
            case R.id.check_day_4:
                if(isChecked){
                    dayArray[4] = 1;
                }else{
                    dayArray[4] = 0;
                }
                break;
            case R.id.check_day_5:
                if(isChecked){
                    dayArray[5] = 1;
                }else{
                    dayArray[5] = 0;
                }
                break;
            case R.id.check_day_6:
                if(isChecked){
                    dayArray[6] = 1;
                }else{
                    dayArray[6] = 0;
                }
                break;
        }
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = String.format("%02d:%02d", hourOfDay, minute);
        tempTime = time;
        textTime.setText(time);
    }
}
