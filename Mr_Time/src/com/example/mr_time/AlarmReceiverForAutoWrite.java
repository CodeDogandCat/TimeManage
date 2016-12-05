package com.example.mr_time;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiverForAutoWrite extends BroadcastReceiver {

	public AlarmReceiverForAutoWrite() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 Intent serviceIntent = new Intent(context, AutoWrite.class);  
		 //serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
         // ����Service  
		
         context.startService(serviceIntent); 
         Log.i("AlarmReceiverForAutoWrite", "alreadyReceive");
	}

}
