package com.sia.siassistant;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridImgAdapter extends RecyclerView.Adapter<GridImgAdapter.ViewHolder> {

    private List<Uri> uriList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_gridview_img);
        }
    }

    public GridImgAdapter(List<Uri> list){
        uriList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri uri = uriList.get(position);
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions().centerCrop())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

}
