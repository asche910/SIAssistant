package com.sia.siassistant.Text;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sia.siassistant.Find;
import com.sia.siassistant.FindAdapter_1;
import com.sia.siassistant.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private List<Photo_item> photo_itemList;
    private Context mcontext;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView photoimage;

        public  ViewHolder(View view){
            super(view);
            photoimage=(ImageView)view.findViewById(R.id.photo_imageview_item);
        }
    }
    public PhotoAdapter(List<Photo_item> findList){
        photo_itemList=findList;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        if (mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.photo_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
       /*holder.messageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Message message=mMessageList.get(position);
              //  Toast.makeText(v.getContext(),"dadasd",Toast.LENGTH_SHORT);
            }
        });*/



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, int position) {
        Photo_item find=photo_itemList.get(position);
        //holder.messageImage.setImageResource(message.GetID());
       // Glide.with(mcontext).load(find.getID()).into(holder.photoimage);
       holder.photoimage.setImageBitmap(find.bitmap);
        holder.itemView.setTag(position);



    }

    @Override
    public int getItemCount() {
        return photo_itemList.size();
    }
    public static interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

}
