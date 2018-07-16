package com.sia.siassistant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FindAdapter_1 extends RecyclerView.Adapter<FindAdapter_1.ViewHolder>implements View.OnClickListener {
    private List<Find> mfindList;
    private Context mcontext;
   public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView findImage;

        public  ViewHolder(View view){
            super(view);
            findImage=(ImageView)view.findViewById(R.id.find_image);
        }
    }
    public FindAdapter_1(List<Find> findList){
        mfindList=findList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        if (mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.find_item,parent,false);
         ViewHolder holder=new ViewHolder(view);
       /*holder.messageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Message message=mMessageList.get(position);
              //  Toast.makeText(v.getContext(),"dadasd",Toast.LENGTH_SHORT);
            }
        });*/


        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Find find=mfindList.get(position);
        //holder.messageImage.setImageResource(message.GetID());
       Glide.with(mcontext).load(find.getImageId()).into(holder.findImage);
        holder.itemView.setTag(position);



    }

    @Override
    public int getItemCount() {
        return mfindList.size();
    }
    public static interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener=null;

    @Override
    public void onClick(View v) {
       // Intent intent=new Intent(mcontext,ChatActivity.class);
        //Toast.makeText(mcontext,"adsd",Toast.LENGTH_SHORT).show();
        if (mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;
    }
}
