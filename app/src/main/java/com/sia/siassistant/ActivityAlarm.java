package com.sia.siassistant;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import static android.support.constraint.Constraints.TAG;

public class ActivityAlarm extends Activity {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Button btnStop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        btnStop = findViewById(R.id.btn_stop_alarm);
        btnStop.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        finish();
//                        onDestroy();
                    }
                });

        try {
            mediaPlayer.setDataSource("/system/media/audio/alarms/Ripple.ogg");
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

}
