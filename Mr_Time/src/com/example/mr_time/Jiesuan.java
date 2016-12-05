package com.example.mr_time;

import java.io.File;
import java.math.RoundingMode;
import java.text.NumberFormat;






import com.example.powerimageview.PowerImageView;
import com.example.service.ItemService;



import com.example.shareToQQ.Util;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class Jiesuan extends Activity {
	private int kingOfShare;
	private ItemService itemService;
	private String  week;
	private String  month;
	private String  year;
	private SlidingLayout slidingLayout;
	private Button menuButton;
	private Tencent mTencent;
	private String [] percent=new String[]{"","","",""};
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);//�������
	        setContentView(R.layout.jiesuan);
	        SysApplication.getInstance().addActivity(this);//�������activityջ������һ���˳�  
	        kingOfShare=0;
	        mTencent = Tencent.createInstance("1104505942", this.getApplicationContext());
	        itemService=new ItemService(this);
	        
	        Intent intent=getIntent();//���ڼ���������ͼ����
	      //  PowerImageView bingtu=(PowerImageView)this.findViewById(R.id.bingtu);
	        ImageView bingtu=(ImageView)this.findViewById(R.id.bingtu);
	        TextView shuimian=(TextView)this.findViewById(R.id.shuimian);
	        TextView cha=(TextView)this.findViewById(R.id.cha);
	        TextView zhong=(TextView)this.findViewById(R.id.zhong);
	        TextView gao=(TextView)this.findViewById(R.id.gao);
	        double [] result=new double[] {0,0,0,0};
	        
			Bundle bundle=intent.getExtras();
			
			String  kind=bundle.getString("kind");
			 year=bundle.getString("year_t"); 
			 month=bundle.getString("month_t");
			 week=bundle.getString("week_t");
			//Log.i("zhencha", week);//////////////////////////////////////////////////////////////////////1
			TextView textview=(TextView)this.findViewById(R.id.textView1_3);
			
			
			if(kind.equals("week")){
				
				textview.setText("Last Week");
				
				try
				{
					result=itemService.getWeekInfo(week);
					this.kingOfShare =1;
					if(result[1]==0.0)
						throw new Exception();
					
//				for(int i=0;i<4;i++){
//					Log.i("zhencha", String.valueOf(result[i]));
//					 
//				}
				
				if(result[1]>0.33)
				{
		
					bingtu.setImageResource(R.drawable.first);
				}else if(result[1]>0.25){
					
					bingtu.setImageResource(R.drawable.second);
				}
				else if(result[1]>0.167){
					
					bingtu.setImageResource(R.drawable.third);
				}else if(result[1]>0.083){
					
					bingtu.setImageResource(R.drawable.forth);
				}else{
					bingtu.setImageResource(R.drawable.chaolan);
				}
				for(int i=0;i<4;i++){
				NumberFormat nf = NumberFormat.getPercentInstance();
				nf.setMinimumFractionDigits(3);//���ñ���С��λ
				nf.setRoundingMode(RoundingMode.HALF_UP); //��������ģʽ
				percent[i] = nf.format(result[i]);
				}
				shuimian.setText("睡眠"+"   "+">>"+percent[0]);
				gao.setText("高效"+"   "+">>"+percent[1]);
				zhong.setText("一般"+"   "+">>"+percent[2]);
				cha.setText("低效"+"   "+">>"+percent[3]);
				}catch(Exception e)
				{
					bingtu.setImageResource(R.drawable.fifth);
					shuimian.setText("睡眠"+"   "+">>##");
					gao.setText("高效"+"   "+">>##");
					zhong.setText("一般"+"   "+">>##");
					cha.setText("低效"+"   "+">>##");
					this.kingOfShare =3;
					Toast.makeText(getApplicationContext(),R.string.warningforweekjiesuan, 	Toast.LENGTH_LONG).show();
					
				}
				Log.i("kingOfShare", "week"+kingOfShare);
				
			
			
			}
			else if(kind.equals("month")){
				try{
				textview.setText("Last Month");
				
				 result=itemService.getMonthInfo(year,month);
				 this.kingOfShare =2;
				 if(result[1]==0.0)
						throw new Exception();
				
				 if(result[1]>0.4)
					{
			
						bingtu.setImageResource(R.drawable.second);
					}else if(result[1]>0.3){
						
						bingtu.setImageResource(R.drawable.third);
					}
					else if(result[1]>0.2){
						
						bingtu.setImageResource(R.drawable.forth);
					}else {
						
						bingtu.setImageResource(R.drawable.first);
					}
				 for(int i=0;i<4;i++){
						NumberFormat nf = NumberFormat.getPercentInstance();
						nf.setMinimumFractionDigits(3);//���ñ���С��λ
						nf.setRoundingMode(RoundingMode.HALF_UP); //��������ģʽ
						percent[i] = nf.format(result[i]);
						}
				 	shuimian.setText("睡眠"+"   "+">>"+percent[0]);
					gao.setText("高效"+"   "+">>"+percent[1]);
					zhong.setText("一般"+"   "+">>"+percent[2]);
					cha.setText("低效"+"   "+">>"+percent[3]);
				}catch(Exception e)
				{
					bingtu.setImageResource(R.drawable.fifth);
					shuimian.setText("睡眠"+"   "+">>##");
					gao.setText("高效"+"   "+">>##");
					zhong.setText("一般"+"   "+">>##");
					cha.setText("低效"+"   "+">>##");
					this.kingOfShare =3;
					Toast.makeText(getApplicationContext(),R.string.warningformonthjiesuan, Toast.LENGTH_LONG).show();
				}
				Log.i("kingOfShare", "month"+kingOfShare);
				}
			slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout_3);
//			slidingLayout.setScrollEvent(bingtu);
//			menuButton = (Button) findViewById(R.id.menuButton_3);
//			menuButton.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					if (slidingLayout.isLeftLayoutVisible()) {
//						slidingLayout.scrollToRightLayout();
//					} else {
//						slidingLayout.scrollToLeftLayout();
//					}
//				}
//			});
			
			 LayoutInflater inflater = LayoutInflater.from(this);      
            // ���봰�������ļ�      
	           View view = inflater.inflate(R.layout.menulayout, null);      
	            // ����PopupWindow����      
	        final PopupWindow pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);      
	            Button btn = (Button) findViewById(R.id.menuButton_3);      
	            // ��Ҫ����һ�´˲����������߿���ʧ      
	            pop.setBackgroundDrawable(new BitmapDrawable());      
	            //���õ��������ߴ�����ʧ      
	            pop.setOutsideTouchable(true);      
	            // ���ô˲�����ý��㣬�����޷����      
              pop.setAnimationStyle(R.style.mypopwindow_anim_style); 
	            pop.setFocusable(true); 
            pop.update(); 
            btn.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View v) {
	    				pop.showAsDropDown(v,-23,-65);
	    			}
	    		});
//	       ImageView shareImageView=(ImageView)this.findViewById(R.id.shareImageView);
//	       shareImageView.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View arg0) {
//				
//				
//			}
//	    	   
//	      
//	        });
	      
	      }
	  @Override
      public boolean onCreateOptionsMenu(Menu menu) {
         
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.main, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         switch(item.getItemId()){
         case R.id.qqshare_item :
        	 
        	 Bundle params = new Bundle();
    		 String imageUrl="/mnt/sdcard/PicOfMrTime(不要删除)/icon.jpg";
    		 File file = new File(imageUrl);
    		 
    		 switch (this.kingOfShare){
    		 case 1 : 
    			// Toast.makeText(getApplicationContext(),R.string.sharebutton, 	Toast.LENGTH_LONG).show();
    			 if (!file.exists()) {
    				  Toast.makeText(getApplicationContext(),R.string.warnning, 	Toast.LENGTH_LONG).show();
    				  break;
    			  }
    			params.putString(QQShare.SHARE_TO_QQ_TITLE, "时间先生的效率统计结果");
    			params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "上周--我的的睡眠时间占"+percent[0]+"，高效时间"+percent[1]+",一般时间"+percent[2]+",低效时间"+percent[3]+",还要继续努力！>>>>>点击跳转到应用下载页面");
    	        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.baidu.com");
    	        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageUrl);
    			params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "Mr_Time");
    			mTencent.shareToQQ(this, params, qqShareListener);
    			break;
    		 case 2 : 
    			 //Toast.makeText(getApplicationContext(),R.string.sharebutton, 	Toast.LENGTH_LONG).show();
    			  if (!file.exists()) {
    				  Toast.makeText(getApplicationContext(),R.string.warnning, 	Toast.LENGTH_LONG).show();
    				  break;
    			  }
    			params.putString(QQShare.SHARE_TO_QQ_TITLE, "时间先生的效率统计结果");
    			params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "上月--我的的睡眠时间占"+percent[0]+"，高效时间"+percent[1]+",一般时间"+percent[2]+",低效时间"+percent[3]+",还要继续努力！>>>>>点击跳转到应用下载页面");
    	        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.baidu.com");
    	        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageUrl);
    			params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "Mr_Time");
    			mTencent.shareToQQ(this, params, qqShareListener);
    			break;
    		 case 3 :
    			 Toast.makeText(getApplicationContext(),R.string.warnWhenNullShare,Toast.LENGTH_LONG).show();
    			 break;
    		 default: Toast.makeText(getApplicationContext(),R.string.warnWhenNullShare,Toast.LENGTH_LONG).show();
    		 			break;
    			 
    			 
    		 }
    		 break;
    		
         default: Toast.makeText(getApplicationContext(),R.string.warnWhenNullShare,Toast.LENGTH_LONG).show();
					break;
         }
       return true;
     }

		public void openDayActivity(View v)
		{
			startActivity(new Intent(this,NeiYeActivity.class));
			this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
			finish();
		}
		public void  openWeekActivity(View v)
		{
			this.kingOfShare =0;
			Intent intent=new Intent();
	    	
	    	intent.setClass(this, Jiesuan.class);//����Ҫ��������
	    
	    	Bundle bundle=new Bundle();
	    	bundle.putString("kind", "week");
	    	bundle.putString("week_t", week);
	    	intent.putExtras(bundle);
	    	
	    	startActivity(intent);
	    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	    	finish();
		}
		public void  openMonthActivity(View v)
		{
			this.kingOfShare =0;
			Intent intent=new Intent();
	    	
	    	intent.setClass(this, Jiesuan.class);//����Ҫ��������
	    
	    	Bundle bundle=new Bundle();
	    	bundle.putString("kind", "month");
	    	bundle.putString("year_t", year);
	    	bundle.putString("month_t", month);
	    	
	    	intent.putExtras(bundle);
	    	
	    	startActivity(intent);
	    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	    	finish();
		}
		IUiListener qqShareListener = new IUiListener() {
	        @Override
	        public void onCancel() {
	        	//Util.toastMessage(Jiesuan.this,"您已取消分享");
	        }
	        @Override
	        public void onComplete(Object response) {
	            // TODO Auto-generated method stub
	           // Util.toastMessage(Jiesuan.this, "");
	        }
	        @Override
	        public void onError(UiError e) {
	            // TODO Auto-generated method stub
	           // Util.toastMessage(Jiesuan.this, "onError: " + e.errorMessage, "e");
	        }
	    };
	 public void shareToQQ(View view)
	 {
		 Bundle params = new Bundle();
		 String imageUrl="/mnt/sdcard/PicOfMrTime(不要删除)/icon.jpg";
		 File file = new File(imageUrl);
		 
		 switch (this.kingOfShare){
		 case 1 : 
			 //Toast.makeText(getApplicationContext(),R.string.sharebutton, 	Toast.LENGTH_LONG).show();
			 if (!file.exists()) {
				  Toast.makeText(getApplicationContext(),R.string.warnning, 	Toast.LENGTH_LONG).show();
				  return;
			  }
			params.putString(QQShare.SHARE_TO_QQ_TITLE, "时间先生的效率统计结果");
			params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "上周--我的的睡眠时间占"+percent[0]+"，高效时间"+percent[1]+",一般时间"+percent[2]+",低效时间"+percent[3]+",还要继续努力！>>>>>点击跳转到应用下载页面");
	        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.baidu.com");
	        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageUrl);
			params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "Mr_Time");
			mTencent.shareToQQ(this, params, qqShareListener);
			break;
		 case 2 : 
			 //Toast.makeText(getApplicationContext(),R.string.sharebutton, 	Toast.LENGTH_LONG).show();
			  if (!file.exists()) {
				  Toast.makeText(getApplicationContext(),R.string.warnning, 	Toast.LENGTH_LONG).show();
				  return;
			  }
			params.putString(QQShare.SHARE_TO_QQ_TITLE, "时间先生的效率统计结果");
			params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "上月--我的的睡眠时间占"+percent[0]+"，高效时间"+percent[1]+",一般时间"+percent[2]+",低效时间"+percent[3]+",还要继续努力！>>>>>点击跳转到应用下载页面");
	        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.baidu.com");
	        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageUrl);
			params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "Mr_Time");
			mTencent.shareToQQ(this, params, qqShareListener);
			break;
		 case 3 :
			 Toast.makeText(getApplicationContext(),R.string.warnWhenNullShare,Toast.LENGTH_LONG).show();
			 break;
		 default: Toast.makeText(getApplicationContext(),R.string.warnWhenNullShare,Toast.LENGTH_LONG).show();
		 			break;
			 
			 
		 }
		
	 }
	 public void openSetting(View v){
			
			Intent intent=new Intent();
	    	intent.setClass(this, Settings.class);//����Ҫ��������
	    	startActivity(intent);
	    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	    	finish();
		}
	 public void exitApp(View v)
		{
		    Toast.makeText(getApplicationContext(),R.string.tuichu, Toast.LENGTH_LONG).show();
			SysApplication.getInstance().exit();  
		}
	 public void OpenMore(View v)
		{
			Toast.makeText(getApplicationContext(),R.string.OpenMore, Toast.LENGTH_LONG).show();
//			Uri uri = Uri.parse("www.baidu.com");
//	        Intent intent = new Intent();
//	        intent.setAction(Intent.ACTION_VIEW);
//	        intent.setData(uri);
//	        startActivity(intent);
		}
}
