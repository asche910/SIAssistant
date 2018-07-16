package com.sia.siassistant;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<EventBean> list;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView userHeader;
        private TextView userName;
        private TextView content;
        private RecyclerView recycView;
        private TextView time;

        private Button btnLike;
        public ViewHolder(View itemView) {
            super(itemView);

            userHeader = itemView.findViewById(R.id.item_img_header);
            userName = itemView.findViewById(R.id.item_text_userName);
            content = itemView.findViewById(R.id.item_text_content);
            recycView = itemView.findViewById(R.id.item_recyclerview);
            time = itemView.findViewById(R.id.item_text_time);
            btnLike = itemView.findViewById(R.id.item_btn_like);

            /*RecyclerView.LayoutManager layoutManager = new GridLayoutManager(itemView.getContext(), 3);
            layoutManager.setAutoMeasureEnabled(true);*/

//            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);

            GridLayoutManager layoutManager = new GridLayoutManager(itemView.getContext(), 3);
            recycView.setLayoutManager(layoutManager);

        }
    }

    public EventAdapter(List<EventBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_happen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        EventBean eventBean = list.get(position);

        Glide.with(context)
                .load(Uri.parse(eventBean.getUserHead()))
                .into(holder.userHeader);

        holder.userName.setText(eventBean.getUserName());
        holder.content.setText(eventBean.getContent());
        holder.time.setText(eventBean.getTime());

        if(eventBean.isAddImg()){
            if(holder.recycView.getAdapter() ==null){
                holder.recycView.setAdapter(new GridImgAdapter(eventBean.getUriList()));
            }else{
//                holder.recycView.getAdapter().notifyDataSetChanged();
            }

          /*  Glide.with(context)
                    .load(eventBean.getUriList().get(0))
                    .into(holder.imgs);*/
        }


        holder.btnLike.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.btnLike.setBackground(context.getDrawable(R.drawable.ic_item_like_press));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
