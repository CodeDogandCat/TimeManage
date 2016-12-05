package com.example.mr_time;





import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.os.Build;

public class WelcomeActivity extends Activity {
	String filepath = "/mnt/sdcard/PicOfMrTime(不要删除)";
	String filepathimg = "";
	Bitmap bmp = null;
	private PendingIntent mAlarmPendingIntent;
	Calendar calendar = Calendar.getInstance();
	int[] drab = new int[] { R.drawable.icon
			   };
	String[] imgnames = new String[] { "icon"
			  };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//�������
        setContentView(R.layout.welcomeactivity);
        
        SysApplication.getInstance().addActivity(this);//�������activityջ������һ���˳�  
        autoWrite();
        for (int i = 0; i < drab.length; i++) {
     	   Resources res = getResources();
     	   bmp = BitmapFactory.decodeResource(res, drab[i]);
     	   storeImageToSDCARD(bmp, imgnames[i], filepath);
     	  }
       
      
       //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       new CountDownTimer(500,200){

    			@Override
    			public void onFinish() {
//    				Intent intent=new Intent();
//    				intent.setClass(WelcomeActivity.this, NeiYeActivity.class);
//    				startActivity(intent);
    				startActivity(new Intent(WelcomeActivity.this,NeiYeActivity.class));
    				WelcomeActivity.this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
    				finish();//��ת���������ǰActivity 
    			}

    			@Override
    			public void onTick(long arg0) {
    				// TODO Auto-generated method stub
    				
    			}
            	
            }.start();
       /* if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }
    public void autoWrite(){
     Intent intent = new Intent();
   	 intent.setAction("android.intent.action.ALARM_RECEIVER");
   	 intent.addCategory("android.intent.category.autowrite");
   	 mAlarmPendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
   	 AlarmManager mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
   	 //每天23点59分自动补充填写您遗漏的ITEM
   	 int hour=23;
   	 int min=59;
   	 calendar.set(Calendar.HOUR_OF_DAY, hour);
   	 calendar.set(Calendar.MINUTE, min);
   	 calendar.set(Calendar.SECOND, 0);
   	 calendar.set(Calendar.MILLISECOND, 0);
    
   	 //mAlarmManager.cancel(mAlarmPendingIntent);
   	 //mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  10 * 1000,60*1000,mAlarmPendingIntent);
   	mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(),86400*1000,mAlarmPendingIntent);//每隔24小时自动填写一次
   	// mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(),10*1000,mAlarmPendingIntent);
   	 //Toast.makeText(this, "已经定时", Toast.LENGTH_SHORT).show();
   	 //Toast.makeText(this, "每天应用会在23时59分自动填写(如果您在此之前没有填写的话)", Toast.LENGTH_SHORT).show();
   	 Log.i("WelcomeActivity", "alreadySetTime");
    }
    // 图片存入到SD卡
 	 public void storeImageToSDCARD(Bitmap colorImage, String ImageName,
 	   String path) {
 	  File file = new File(path);
 	  if (!file.exists()) {
 	   file.mkdir();
 	  }
 	  File imagefile = new File(file, ImageName + ".jpg");
 	  if (!imagefile.exists()) {
 	   try {
 	    imagefile.createNewFile();
 	    FileOutputStream fos = new FileOutputStream(imagefile);
 	    
 	    colorImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
 	    fos.flush();
 	    fos.close();
 	   } catch (Exception e) {
 	    e.printStackTrace();
 	   }
 	  } else {
 		 
 	  }
 	 }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }*/

    
   /* public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
   /* public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
            return rootView;
        }
    }*/

}
