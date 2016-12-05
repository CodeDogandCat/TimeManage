package com.example.mr_time;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
	private NotificationManager nm;
	private PendingIntent pd;
	@Override
	public void onReceive(Context context, Intent intent) {
	//	Toast.makeText(context, "�������ӣ���Ҫ������...", Toast.LENGTH_SHORT).show();
		
//		 Intent activityIntent = new Intent(context,NeiYeActivity.class);
//		 
//         activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
//         // ����Activity  
//         context.startActivity(activityIntent);  
		 Intent serviceIntent = new Intent(context, StartOnStateBarActivity.class);  
		 serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
         // ����Service  
         context.startService(serviceIntent);  
       
     		 

	}

}
