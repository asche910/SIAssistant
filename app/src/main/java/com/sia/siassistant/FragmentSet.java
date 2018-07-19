package com.sia.siassistant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class FragmentSet extends Fragment implements View.OnClickListener{
   private VideoView videoView;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragmentset,container,false);
        videoView=(view).findViewById(R.id.video_view);
        Button play=(view).findViewById(R.id.button_play);
        Button pause=(view).findViewById(R.id.button_pause);
        Button replay=(view).findViewById(R.id.button_replay);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
initVideoPath();

        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_play:if(!videoView.isPlaying()){
                videoView.start();
            }break;
            case R.id.button_pause:if(videoView.isPlaying()){
                videoView.pause();
            }break;
            case R.id.button_replay:if(videoView.isPlaying()){
                videoView.resume();
            }break;
        }
    }
    private void initVideoPath(){
        File file=new File(Environment.getExternalStorageDirectory(),"");/* ******** */
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (videoView!=null){
            videoView.suspend();
        }
    }
}
