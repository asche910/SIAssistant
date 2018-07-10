package com.sia.siassistant;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;

import static com.sia.siassistant.WheelView.TAG;

public class FragmentNew extends Fragment  implements View.OnClickListener, DatePickerDialog.OnDateSetListener{


    private final String[] PLANETS = new String[]{"3天", "7天", "21天", "30天", "100天", "365天", "永久"};
    private int dayIndex = -1;
    Button btnSug, btnDate;
    View view;
    WheelView wheelView;
    TextView textDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new, container, false );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnSug = getActivity().findViewById(R.id.btn_add_sug);
        btnDate = getActivity().findViewById(R.id.btn_add_date);
        textDate = getActivity().findViewById(R.id.text_add_data);

        btnSug.setOnClickListener(this);
        btnDate.setOnClickListener(this);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.wheelview, null);
        wheelView = view.findViewById(R.id.wheelView);
        wheelView.setOffset(2);
        wheelView.setItems(Arrays.asList(PLANETS));
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
                new AlertDialog.Builder(getActivity())
                        .setTitle("选择天数")
                        .setView(view)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dayIndex = -1;
                            }
                        })
                        .show();
                break;
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
                        (nowMonth == monthOfYear && nowDay < dayOfMonth))
                )
                ){

            String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
            Log.e(TAG, "onDateSet: " + nowMonth );
            textDate.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
        }else{
            Toast.makeText(getActivity(), "请选择未来的日期！", Toast.LENGTH_SHORT).show();
        }


    }
}
