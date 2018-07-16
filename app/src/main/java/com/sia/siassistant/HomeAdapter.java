package com.sia.siassistant;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<GoalBean> list;
    private Context context;
    private OnItemClickListener onItemClickListener;

//    static int bgIndex = 0;

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textName, textNum, textDays;
        Button btnClick;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img_home);
            textName = itemView.findViewById(R.id.item_text_name);
            textNum = itemView.findViewById(R.id.item_text_num);
            textDays = itemView.findViewById(R.id.item_text_days);
            btnClick = itemView.findViewById(R.id.item_btn_click);
        }
    }

    public HomeAdapter(List<GoalBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final GoalBean goalBean = list.get(position);

        if(goalBean.getUri() == null || goalBean.getUri().equals("")){

            Log.e(TAG, "onBindViewHolder: +++++++++++++++++>" );
            switch (goalBean.getCurDay() % 3) {
                case 2:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Glide.with(context)
                                .load(context.getDrawable(R.drawable.bg_3))
                                .into(holder.imageView);
//                        holder.imageView.setImageDrawable(context.getDrawable(R.drawable.bg_3));
                    }
                    break;
                case 1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Glide.with(context)
                                .load(context.getDrawable(R.drawable.bg_2))
                                .into(holder.imageView);
//                        holder.imageView.setImageDrawable(context.getDrawable(R.drawable.bg_2));
                    }
                    break;
                case 0:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Glide.with(context)
                                .load(context.getDrawable(R.drawable.bg_1))
                                .into(holder.imageView);
//                        holder.imageView.setImageDrawable(context.getDrawable(R.drawable.bg_1));
                    }
                    break;
            }
        }else{
            try {
                holder.imageView.setImageBitmap(BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(goalBean.getUri()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        holder.textName.setText(goalBean.getName());
        holder.textNum.setText(goalBean.getCurDay() + "");
        holder.textDays.setText("" + goalBean.getDays());

        holder.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalBean.setCurDay(goalBean.getCurDay() + 1);
                holder.btnClick.setText("打卡成功！");
                holder.btnClick.setTextColor(Color.WHITE);
                holder.btnClick.setBackgroundColor(Color.parseColor("#FF0661FF"));
                onBindViewHolder(holder, position);

            }
        });


        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "onClick: +++++++++++++++++++>" );
                    onItemClickListener.onItemClick(position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        Log.e(TAG, "setOnItemClickListener: ----------> " );
        this.onItemClickListener = onItemClickListener;
    }

}
