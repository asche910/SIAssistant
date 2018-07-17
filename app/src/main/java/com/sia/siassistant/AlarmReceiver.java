package com.sia.siassistant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.sia.siassistant.alarm".equals(intent.getAction())){
            Intent intent1 = new Intent(context, ActivityAlarm.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }
}
