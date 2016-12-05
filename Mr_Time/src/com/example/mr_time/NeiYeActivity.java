package com.example.mr_time;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.ImageGalleryAdapter.ImageGalleryAdapter;
import com.example.domain.Item;



import com.example.service.ItemService;





import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;

public class NeiYeActivity extends Activity {
	private float startX; 
	private float endX; 
	private ViewFlipper viewFlipper;
	private ItemService itemService;
	 private Gallery myGallery1;
	 private Gallery myGallery2;
	 private Gallery myGallery3;
	 private Gallery myGallery4;
	 private Gallery myGallery5;
	 private Gallery myGallery6;
	 private String[]kind={"睡觉觉","打鸡血","还不错","懒懒哒"};
	//private Animation in_lefttoright,out_lefttoright,int_rtl,out_rtl;
	 private SlidingLayout slidingLayout;
	 private Button menuButton;
	 private ListView contentListView;
	 private ArrayAdapter<String> contentListAdapter;
	 private String[] contentItems = { "00:00-02:00", "02:00-04:00", "04:00-06:00",
				"06:00-08:00", "08:00-10:00", "10:00-12:00"
				};
	
	 private Gallery[] myGallery;
	 private  String  date;
	 private  String  year;
	 private  String  month;
	 private  String  day;
	 private String week;
	 private int sjd;
	@Override
protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//后来添加
		 setContentView(R.layout.neiyeactivity);
		 SysApplication.getInstance().addActivity(this);//加入程序activity栈，用于一键退出  
		 
		 
		 
		 
		 
		 itemService=new ItemService(this);
		 //viewFlipper=(ViewFlipper) this.findViewById(R.id.viewFlipper1);
		 TextView weektextview=(TextView)this.findViewById(R.id.textView1);
		 TextView datetextview=(TextView)this.findViewById(R.id.textView2);
		//左侧浮动menu的实现
			slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
			menuButton = (Button) findViewById(R.id.menuButton);
			contentListView = (ListView) findViewById(R.id.contentList);
			contentListAdapter = new ArrayAdapter<String>(this, R.layout.item,
					contentItems);
		    contentListView.setAdapter(contentListAdapter);
		   
		    slidingLayout.setScrollEvent(contentListView);
//		    menuButton.setOnClickListener(new OnClickListener() {
//			@Override
//				public void onClick(View v) {
//					if (slidingLayout.isLeftLayoutVisible()) {
//					slidingLayout.scrollToRightLayout();
//				} else {
//					}
//				}
//			});
			
			
			
			
//			 LayoutInflater inflater = LayoutInflater.from(this);      
//	            // 引入窗口配置文件      
//	           View view = inflater.inflate(R.layout.menulayout, null);      
//	            // 创建PopupWindow对象      
//	        final PopupWindow pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);      
//	            Button btn = (Button) findViewById(R.id.menuButton);      
//	            // 需要设置一下此参数，点击外边可消失      
//	            pop.setBackgroundDrawable(new BitmapDrawable());      
//	            //设置点击窗口外边窗口消失      
//	            pop.setOutsideTouchable(true);      
//	            // 设置此参数获得焦点，否则无法点击      
//	           pop.setAnimationStyle(R.style.mypopwindow_anim_style); 
//	            pop.setFocusable(true); 
//	            pop.update(); 
//	            menuButton.setOnClickListener(new OnClickListener() {
//	    			@Override
//	    			public void onClick(View v) {
//	    				pop.showAsDropDown(v,-10,7);
//	    			}
//	    		});
				
	        
			//listView的点击事件
			contentListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String text = contentItems[position];
//					Toast.makeText(NeiYeActivity.this, text, Toast.LENGTH_SHORT).show();
				}
			});
			 //页头的日期和星期
			SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyyMMdd");  
			SimpleDateFormat    formatter2    =   new    SimpleDateFormat    ("yyyy/MM/dd");  
			 SimpleDateFormat weekformatter  = new SimpleDateFormat("EEE");
			 SimpleDateFormat yearformatter  = new SimpleDateFormat("yyyy");
			 SimpleDateFormat monthformatter  = new SimpleDateFormat("MM");
			 SimpleDateFormat dayformatter  = new SimpleDateFormat("dd");
			 Date    curDate =   new    Date(System.currentTimeMillis());//获取当前时间       
		  date    =   formatter.format(curDate); 
		  String date2    =   formatter2.format(curDate); 
		  week=weekformatter .format(curDate);
		  year=yearformatter .format(curDate);
		  month=monthformatter .format(curDate);
		  day=dayformatter .format(curDate);
//			date ="20150407";  
//			// week="周日";
//			//week="周一";
//		    week="周二";
//			//week="周三";
//			//week="周四";
//			//week="周五";
//		    //week="周六";
//			 year="2015";
//			 //month="4";
//			 day="7";
			 weektextview.setText(week);
			 datetextview.setText(date2);	
			 //选择效率等级的图片 gallery
			 	myGallery1=(Gallery)this.findViewById(R.id.gallery1);
		        myGallery2=(Gallery)this.findViewById(R.id.gallery2);
		        myGallery3=(Gallery)this.findViewById(R.id.gallery3);
		        myGallery4=(Gallery)this.findViewById(R.id.gallery4);
		        myGallery5=(Gallery)this.findViewById(R.id.gallery5);
		        myGallery6=(Gallery)this.findViewById(R.id.gallery6);
		       
		       
		        Gallery[] myGallery={myGallery1,myGallery2,myGallery3,myGallery4,myGallery5,myGallery6
		        		};
		        for(int i=0;i<6;i++)
		        { 
		        	
		        	myGallery[i].setAdapter(new ImageGalleryAdapter(this));
		        	myGallery[i].setSelection(400);//改进
		        	
		        	myGallery[i].setOnItemSelectedListener(new OnItemSelectedListener1());
		        	myGallery[i].setSpacing(0);
		        }
		    	myGallery[0].setOnItemClickListener(new OnItemClickListenerimp0() );
		    	myGallery[1].setOnItemClickListener(new OnItemClickListenerimp1 () );
		    	myGallery[2].setOnItemClickListener(new OnItemClickListenerimp2() );
		    	myGallery[3].setOnItemClickListener(new OnItemClickListenerimp3() );
		    	myGallery[4].setOnItemClickListener(new OnItemClickListenerimp4() );
		    	myGallery[5].setOnItemClickListener(new OnItemClickListenerimp5() );
			
}

	
	//gallery的 点击事件
	 public class OnItemClickListenerimp0 implements  OnItemClickListener{
		 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
		
					
				Item	item=new Item( date+"00", ""+(position%4), year, month,  day, week, 0);
				if(itemService.find(item.getTime()))
				{
					itemService.update(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经更新", Toast.LENGTH_SHORT).show();
				}
				else{
					itemService.save(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经保存", Toast.LENGTH_SHORT).show();
				}
				
			}
	    	
	    }
	 public class OnItemClickListenerimp1 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Item item=new Item( date+"01", ""+(position%4), year, month,  day, week, 1);
				if(itemService.find(item.getTime()))
				{
					itemService.update(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经更新", Toast.LENGTH_SHORT).show();
				}
				else{
					itemService.save(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经保存", Toast.LENGTH_SHORT).show();
				}
				
				
				
			}
	    	
	    }
	 public class OnItemClickListenerimp2 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"02", ""+(position%4), year, month,  day, week, 2);
				if(itemService.find(item.getTime()))
				{
					itemService.update(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经更新", Toast.LENGTH_SHORT).show();
				}
				else{
					itemService.save(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经保存", Toast.LENGTH_SHORT).show();
				}
				
			}
	    	
	    }
	 public class OnItemClickListenerimp3 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"03", ""+(position%4), year, month,  day, week,3);
				if(itemService.find(item.getTime()))
				{
					itemService.update(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经更新", Toast.LENGTH_SHORT).show();
				}
				else{
					itemService.save(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经保存", Toast.LENGTH_SHORT).show();
				}
				
			}
	    	
	    }
	 public class OnItemClickListenerimp4 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"04", ""+(position%4), year, month,  day, week, 4);
				if(itemService.find(item.getTime()))
				{
					itemService.update(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经更新", Toast.LENGTH_SHORT).show();
				}
				else{
					itemService.save(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经保存", Toast.LENGTH_SHORT).show();
				}
				
			}
	    	
	    }
	 public class OnItemClickListenerimp5 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"05", ""+(position%4), year, month,  day, week, 5);
				if(itemService.find(item.getTime()))
				{
					itemService.update(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经更新", Toast.LENGTH_SHORT).show();
				}
				else{
					itemService.save(item);
					Toast.makeText(getApplicationContext(), kind[position%4]+"已经保存", Toast.LENGTH_SHORT).show();
				}
				
			}
	    	
	    }
	 //gallery的 滑动事件
	 public class OnItemSelectedListener1 implements OnItemSelectedListener
	 {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			//Toast.makeText(getApplicationContext(), kind[position%4], Toast.LENGTH_SHORT).show();
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
	 //ViewFlipper的翻页功能
	/*public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			startX= event.getX();
		}else if(event.getAction()==MotionEvent.ACTION_UP){
			endX=event.getX();
			if(endX>startX){//翻页动画添加
				//viewFlipper.setInAnimation(R.anim.abc_fade_in);
				//viewFlipper.setOutAnimation(out_lefttoright);
				viewFlipper.showNext();//显示下一页
			}
			else if(endX<startX){
				//viewFlipper.setInAnimation(int_rtl);
				//viewFlipper.setOutAnimation(out_rtl);
				viewFlipper.showNext();//显示前一页
			}
			return true;
		}
		return super.onTouchEvent(event);
	}*/
	//页底的help提示菜单
	public void openHelp(View v)
	{
		Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle(R.string.help);
		dialog.setMessage(R.string.helpcontent);
		dialog.create();
		dialog.show();
		
	}
	public void opennextpage(View v)
	{
		startActivityForResult(new Intent(this,NeiYeNPActivity.class),0);
		//startActivity(new Intent(this,NeiYeNPActivity.class));
		this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	}
	/*public void openmenu(View v)
	{
		if (slidingLayout.isLeftLayoutVisible()) {
			slidingLayout.scrollToRightLayout();
		} else {
			slidingLayout.scrollToLeftLayout();
		}
	}*/
	public void openDayActivity(View v)
	{
		startActivityForResult(new Intent(this,NeiYeActivity.class),0);
		//startActivity(new Intent(this,NeiYeActivity.class));
		this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
		finish();
	}
	public void  openWeekActivity(View v)
	{
		Intent intent=new Intent();
    	
    	intent.setClass(this, Jiesuan.class);//设置要激活的组件
    
    	Bundle bundle=new Bundle();
    	bundle.putString("kind", "week");
    	bundle.putString("year_t", year);
    	bundle.putString("month_t", month);
    	bundle.putString("week_t", week);
    	intent.putExtras(bundle);
    	startActivityForResult(intent,0);
    	//startActivity(intent);
    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	}
	public void  openMonthActivity(View v)
	{
		Intent intent=new Intent();
    	
    	intent.setClass(this, Jiesuan.class);//设置要激活的组件
    
    	Bundle bundle=new Bundle();
    	bundle.putString("kind", "month");
    	bundle.putString("year_t", year);
    	bundle.putString("month_t", month);
    	bundle.putString("week_t", week);
    	intent.putExtras(bundle);
    	startActivityForResult(intent,0);
    	//startActivity(intent);
    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	}
	public void openSetting(View v){
	
		Intent intent=new Intent();
    	intent.setClass(this, Settings.class);//设置要激活的组件
    	//startActivity(intent);
    	startActivityForResult(intent,0);
    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	}
	public void exitApp(View v)
	{
		Toast.makeText(getApplicationContext(),R.string.tuichu, Toast.LENGTH_LONG).show();
		SysApplication.getInstance().exit();  
	}
	public void OpenMore(View v)
	{
		Toast.makeText(getApplicationContext(),R.string.OpenMore, Toast.LENGTH_LONG).show();
//		Uri uri = Uri.parse("www.baidu.com");
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(uri);
//        startActivity(intent);
	}
	
	
	
}
