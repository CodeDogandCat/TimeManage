package com.example.mr_time;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class StartOnStateBarActivity extends Service {

	private NotificationManager nm;
	private PendingIntent pd;
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		
		super.onStart(intent, startId);
			 //Toast.makeText(this, "Automatic Reminding Service Start ", Toast.LENGTH_LONG).show();
		
			 nm = (NotificationManager) getSystemService (Context.NOTIFICATION_SERVICE);
		         
			 Intent openActivity = new Intent(this,WelcomeActivity.class);
				         
		     pd = PendingIntent.getActivity(this, 0, openActivity, 0);
			
			  //�½�״̬��֪ͨ
			  Notification baseNF = new Notification();
			 	  
			 	//����֪ͨ��״̬����ʾ��ͼ��
			 baseNF.icon = R.drawable.zhuangtai;
			 	 
			 	//֪ͨʱ��״̬����ʾ������
			 baseNF.tickerText = "wow! This is Mr.Time~~";
			 	
		
			  	
			 	 	                     
			//��������������ѭ����ֱ���û���Ӧ
			 // baseNF.flags |= Notification.FLAG_INSISTENT;  
			// baseNF.flags |= Notification.;
			  //֪ͨ��������Զ���ʧ
			 baseNF.flags |= Notification.FLAG_AUTO_CANCEL;
			  //���'Clear'ʱ���������֪ͨ(QQ��֪ͨ�޷�����������õ����)
			 baseNF.flags |= Notification.FLAG_NO_CLEAR;
			 	 
			 	//֪ͨ��Ĭ�ϲ��� DEFAULT_SOUND, DEFAULT_VIBRATE, DEFAULT_LIGHTS.
			 	//���Ҫȫ������Ĭ��ֵ, �� DEFAULT_ALL.
			 	//�˴�����Ĭ������
				// baseNF.defaults |= Notification.DEFAULT_SOUND;  
                baseNF.defaults |= Notification.DEFAULT_VIBRATE;  
                //baseNF.defaults |= Notification.DEFAULT_LIGHTS;  
				 
			 	//�ڶ������� ������״̬��ʱ��ʾ����Ϣ���� expanded message title
			 	//����������������״̬��ʱ��ʾ����Ϣ���� expanded message text
			 	//���ĸ������������֪ͨʱִ��ҳ����ת
			 baseNF.setLatestEventInfo(this, "Nice to meet you~(^?^)~", "Click here to save your efficiency!", pd);
				 
			 	//����״̬��֪ͨ
			 	//The first parameter is the unique ID for the Notification
			 	// and the second is the Notification object.
			 nm.notify(10, baseNF);
			
		}
	

	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}

}
