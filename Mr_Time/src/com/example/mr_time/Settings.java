package com.example.mr_time;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Settings extends Activity {
	private PendingIntent mAlarmPendingIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//�������
		 setContentView(R.layout.setting);
		 
		 SysApplication.getInstance().addActivity(this);//�������activityջ������һ���˳�  
		 RadioGroup group = (RadioGroup)this.findViewById(R.id.radioGroup);
		 group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			            
			    @Override
			    public void onCheckedChanged(RadioGroup arg0, int arg1) {
			              
			      //��ȡ������ѡ�����ID
			     int radioButtonId = arg0.getCheckedRadioButtonId();
			      //����ID��ȡRadioButton��ʵ��
			      RadioButton rb = (RadioButton)Settings.this.findViewById(radioButtonId);
			      //�����ı����ݣ��Է���ѡ����
			      	 
			         EditText shi = (EditText)Settings.this.findViewById(R.id.shi);
			         EditText fen = (EditText)Settings.this.findViewById(R.id.fen);
			        
			         int hour;
			         int min;
			         if(!TextUtils.isEmpty(shi.getText())&&!TextUtils.isEmpty(fen.getText()))
			         {	 
			         hour=Integer.parseInt(shi.getText().toString());
			         min=Integer.parseInt(fen.getText().toString());
			         Calendar calendar = Calendar.getInstance();
			         if(!(1<=hour&&hour<=24))
			         {
			        	 
			        	 hour=8;
			        	 Toast.makeText(Settings.this, R.string.warnningWhenHourOverflow, Toast.LENGTH_SHORT).show();
			         }
			         if(!(0<=min&&min<60))
			         {
			        	
			        	 min=0;
			        	 Toast.makeText(Settings.this, R.string.warnningWhenMinOverflow, Toast.LENGTH_SHORT).show();
			         }
			         
			         if(rb.getText().equals("run")){
			        	 Intent intent = new Intent();
			        	 intent.setAction("android.intent.action.ALARM_RECEIVER");
			        	 intent.addCategory("android.intent.category.autoalarm");
			        	 mAlarmPendingIntent = PendingIntent.getBroadcast(Settings.this, 0, intent, 0);
			        	 AlarmManager mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			        	 /**************ÿ��(Ĭ��)�˵�����**********************/
			        	 calendar.set(Calendar.HOUR_OF_DAY, hour);
			        	 calendar.set(Calendar.MINUTE, min);
			        	 calendar.set(Calendar.SECOND, 0);
			        	 calendar.set(Calendar.MILLISECOND, 0);
			         
			        	 //mAlarmManager.cancel(mAlarmPendingIntent);
			        	 //mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  10 * 1000,60*1000,mAlarmPendingIntent);
			        	 mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(),86400*1000,mAlarmPendingIntent);//每隔24小时提醒一次
			        	 Toast.makeText(Settings.this, "每天应用会在"+hour+"时"+min+"分"+"自动提醒您记录您的效率等级情况(如果您的闹钟时间早于当前时间，会立刻振动提醒)", Toast.LENGTH_SHORT).show();
			       	
			         }else if(rb.getText().equals("stop"))
			         {
			        	 Intent intent = new Intent("android.intent.action.ALARM_RECEIVER");
			        	 mAlarmPendingIntent = PendingIntent.getBroadcast(Settings.this, 0, intent, 0);
			        	 AlarmManager mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			        	 mAlarmManager.cancel(mAlarmPendingIntent);
			        	 // mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  10 * 1000,600*1000,mAlarmPendingIntent);
			        	 Toast.makeText(Settings.this, R.string.stopAlarm, Toast.LENGTH_SHORT).show();
			         }
			      }
			      else{	Toast.makeText(Settings.this, R.string.warnningWhenAlarmNull, Toast.LENGTH_SHORT).show();}
		 }
		});
	}
	public void returnToNeiYe(View v){
		Intent intent=new Intent();
		intent.setClass(this, NeiYeActivity.class);//����Ҫ��������
    	startActivity(intent);
    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
    	finish();
	}

}
