package com.sia.siassistant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private List<AlarmBean> list;
    OnItemClickListener onItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTime;
        TextView textDays;
        Switch aSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            textTime = itemView.findViewById(R.id.item_text_alarm_time);
            textDays = itemView.findViewById(R.id.item_text_alarm_days);
            aSwitch = itemView.findViewById(R.id.item_switch_alarm);

        }
    }


    public AlarmAdapter(List<AlarmBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final AlarmBean alarmBean = list.get(position);
        holder.textTime.setText(alarmBean.getTime());
        holder.textDays.setText(alarmBean.getDays());

        holder.aSwitch.setChecked(alarmBean.isOn());

        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(position);
                    return true;
                }
            });
        }

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isPressed()){
                    if(isChecked){
                        alarmBean.setOn(true);
                    }else{
                        alarmBean.setOn(false);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
