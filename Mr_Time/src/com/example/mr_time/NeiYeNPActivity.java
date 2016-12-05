package com.example.mr_time;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.ImageGalleryAdapter.ImageGalleryAdapter;
import com.example.domain.Item;
import com.example.mr_time.NeiYeActivity.OnItemClickListenerimp0;
import com.example.mr_time.NeiYeActivity.OnItemSelectedListener1;
import com.example.service.ItemService;






import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout.LayoutParams;

public class NeiYeNPActivity extends Activity {
	
	private float startX; 
	private float endX; 
	private ViewFlipper viewFlipper;
	private ItemService itemService;
	 private Gallery myGallery12;
	 private Gallery myGallery7;
	 private Gallery myGallery8;
	 private Gallery myGallery9;
	 private Gallery myGallery10;
	 private Gallery myGallery11;
	 private String[]kind={"睡觉觉","打鸡血","还不错","懒懒哒"};
	//private Animation in_lefttoright,out_lefttoright,int_rtl,out_rtl;
	 private SlidingLayout slidingLayout;
	 private Button menuButton;
	 private ListView contentListView;
	 private ArrayAdapter<String> contentListAdapter;
	 private String[] contentItems = { "12:00-14:00","14:00-16:00",
				"16:00-18:00","18:00-20:00","20:00-22:00","22:00-24:00"
				};
	
	 private Gallery[] myGallery;
	 private  String  date;
	 private  String  year;
	 private  String  month;
	 private  String  day;
	 private String week;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//�������
		 setContentView(R.layout.neiyenpactivity);
		 
		 SysApplication.getInstance().addActivity(this);//�������activityջ������һ���˳�  
		 itemService=new ItemService(this);
		 //viewFlipper=(ViewFlipper) this.findViewById(R.id.viewFlipper1);
		 TextView weektextview=(TextView)this.findViewById(R.id.textView1_2);
		 TextView datetextview=(TextView)this.findViewById(R.id.textView2_2);
		//��ม��menu��ʵ��
			slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout_2);
			menuButton = (Button) findViewById(R.id.menuButton_2);
			contentListView = (ListView) findViewById(R.id.contentList_2);
			contentListAdapter = new ArrayAdapter<String>(this, R.layout.item,
					contentItems);
		    contentListView.setAdapter(contentListAdapter);
			
		    
		    slidingLayout.setScrollEvent(contentListView);
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
//			LayoutInflater inflater = LayoutInflater.from(this);      
//            // ���봰�������ļ�      
//            View view = inflater.inflate(R.layout.menulayout, null);      
//            // ����PopupWindow����      
//            final PopupWindow pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);      
//            //Button btn = (Button) findViewById(R.id.menuButton);      
//            // ��Ҫ����һ�´˲����������߿���ʧ      
//            pop.setBackgroundDrawable(new BitmapDrawable());      
//            //���õ��������ߴ�����ʧ      
//            pop.setOutsideTouchable(true);      
//            // ���ô˲�����ý��㣬�����޷����      
//            pop.setAnimationStyle(R.style.mypopwindow_anim_style); 
//            pop.setFocusable(true); 
//            pop.update();  
//            menuButton.setOnClickListener(new OnClickListener() {
//    			@Override
//    			public void onClick(View v) {
//    				pop.showAsDropDown(v,-10,7);
//    			}
//    		});
			//listView�ĵ���¼�
			contentListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String text = contentItems[position];
					//Toast.makeText(NeiYeNPActivity.this, text, Toast.LENGTH_SHORT).show();
				}
			});
			 //ҳͷ�����ں�����
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
			 //ѡ��Ч�ʵȼ���ͼƬ gallery
			  	myGallery7=(Gallery)this.findViewById(R.id.gallery7);
			  	myGallery8=(Gallery)this.findViewById(R.id.gallery8);
			  	myGallery9=(Gallery)this.findViewById(R.id.gallery9);
		        myGallery10=(Gallery)this.findViewById(R.id.gallery10);
		        myGallery11=(Gallery)this.findViewById(R.id.gallery11);
		        myGallery12=(Gallery)this.findViewById(R.id.gallery12);
		       
		       
		        Gallery[] myGallery={myGallery7,myGallery8,myGallery9,myGallery10,myGallery11,myGallery12
		        		};
		        for(int i=0;i<6;i++)
		        { 
		        	
		        	myGallery[i].setAdapter(new ImageGalleryAdapter(this));
		        	myGallery[i].setSelection(400);//�Ľ�
		        	
		        	myGallery[i].setOnItemSelectedListener(new OnItemSelectedListener1());
		        	myGallery[i].setSpacing(0);}
		       myGallery[0].setOnItemClickListener(new OnItemClickListenerimp6() );
		        myGallery[1].setOnItemClickListener(new OnItemClickListenerimp7() );
		    	myGallery[2].setOnItemClickListener(new OnItemClickListenerimp8 () );
		    	myGallery[3].setOnItemClickListener(new OnItemClickListenerimp9() );
		    	myGallery[4].setOnItemClickListener(new OnItemClickListenerimp10() );
		    	myGallery[5].setOnItemClickListener(new OnItemClickListenerimp11() );
		    	
	}

	
	//gallery�� ����¼�
	 private class OnItemClickListenerimp6 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
			
				
				Item item=new Item( date+"06", ""+(position%4), year, month,  day, week, 6);
				
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
	 private class OnItemClickListenerimp7 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"07", ""+( position%4), year, month,  day, week,7);
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
	 private class OnItemClickListenerimp8 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"08", ""+( position%4), year, month,  day, week, 8);
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
	 private class OnItemClickListenerimp9 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"09",""+( position%4), year, month,  day, week, 9);
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
	 private class OnItemClickListenerimp10 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"10", ""+( position%4), year, month,  day, week, 10);
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
	 private class OnItemClickListenerimp11 implements  OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Item item=new Item( date+"11", ""+( position%4), year, month,  day, week, 11);
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
	 
	//gallery�� �����¼�
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
	 //ViewFlipper�ķ�ҳ����
	/*public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			startX= event.getX();
		}else if(event.getAction()==MotionEvent.ACTION_UP){
			endX=event.getX();
			if(endX>startX){//��ҳ�������
				//viewFlipper.setInAnimation(R.anim.abc_fade_in);
				//viewFlipper.setOutAnimation(out_lefttoright);
				viewFlipper.showNext();//��ʾ��һҳ
			}
			else if(endX<startX){
				//viewFlipper.setInAnimation(int_rtl);
				//viewFlipper.setOutAnimation(out_rtl);
				viewFlipper.showNext();//��ʾǰһҳ
			}
			return true;
		}
		return super.onTouchEvent(event);
	}*/
	//ҳ�׵�help��ʾ�˵�
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
	    	
	    	intent.setClass(this, Jiesuan.class);//����Ҫ��������
	    
	    	Bundle bundle=new Bundle();
	    	bundle.putString("kind", "week");
	    	bundle.putString("year_t", year);
	    	bundle.putString("month_t", month);
	    	bundle.putString("week_t", week);
	    	intent.putExtras(bundle);
	    	startActivityForResult(intent,0);
	    	//startActivity(intent);
	    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	    	finish();
		}
		public void  openMonthActivity(View v)
		{
			Intent intent=new Intent();
	    	
	    	intent.setClass(this, Jiesuan.class);//����Ҫ��������
	    
	    	Bundle bundle=new Bundle();
	    	bundle.putString("kind", "month");
	    	bundle.putString("year_t", year);
	    	bundle.putString("month_t", month);
	    	bundle.putString("week_t", week);
	    	intent.putExtras(bundle);
	    	startActivityForResult(intent,0);
	    	//startActivity(intent);
	    	this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
	    	finish();
		}
	public void openHelp(View v)
	{
		Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle(R.string.help);
		dialog.setMessage(R.string.helpcontent);
		dialog.create();
		dialog.show();
		/*Intent intent = new Intent();  
        intent.setComponent(new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity"));  
        startActivity(intent);  */
	}
	public void openlastpage(View v)
	{
		
		startActivity(new Intent(this,NeiYeActivity.class));
		this.overridePendingTransition(R.anim.enter_ltr, R.anim.exit_ltr);
		finish();
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
//		Uri uri = Uri.parse("www.baidu.com");
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(uri);
//        startActivity(intent);
	}
	
}
