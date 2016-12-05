package com.example.mr_time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.example.domain.Item;
import com.example.service.ItemService;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AutoWrite extends Service {
	 private  String  date;
	 private  String  year;
	 private  String  month;
	 private  String  day;
	 private String week;
	 private int sjd;
	 private ItemService AtuoWriteService;
	public AutoWrite() {
		// TODO Auto-generated constructor stub
		 //Log.i("AutoWrite", "1");
	}

	@SuppressLint("SimpleDateFormat") @SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		 AtuoWriteService=new ItemService(this);
		 Log.i("AutoWrite", "arrive");
		 SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyyMMdd");  
		
		 SimpleDateFormat weekformatter  = new SimpleDateFormat("EEE");
		 SimpleDateFormat yearformatter  = new SimpleDateFormat("yyyy");
		 SimpleDateFormat monthformatter  = new SimpleDateFormat("MM");
		 SimpleDateFormat dayformatter  = new SimpleDateFormat("dd");
		 Date  curDate =   new    Date(System.currentTimeMillis());//获取当前时间 
		 date    =   formatter.format(curDate); 
		 
		 week=weekformatter .format(curDate);
		 year=yearformatter .format(curDate);
		 month=monthformatter .format(curDate);
		 day=dayformatter .format(curDate);
//		 date="20150404";
//		 week="周六";
//		 year="2015";
//		 month="04";
//		 day="04";
		 for(int i=0;i<4;i++){
			 Item	item=new Item( date+"0"+i, ""+0, year, month,  day, week, i);
			 if(AtuoWriteService.find(item.getTime())==false){
				 AtuoWriteService.save(item);
			 }
		 }
		 for(int i=4;i<6;i++){
			 Item	item=new Item( date+"0"+i, ""+1, year, month,  day, week, i);
			 if(AtuoWriteService.find(item.getTime())==false){
				 AtuoWriteService.save(item);
			 }
		 }
		 for(int i=6;i<8;i++){
			 Item	item=new Item( date+"0"+i, ""+3, year, month,  day, week, i);
			 if(AtuoWriteService.find(item.getTime())==false){
				 AtuoWriteService.save(item);
			 }
		 }
		 for(int i=8;i<10;i++){
			 Item	item=new Item( date+"0"+i, ""+2, year, month,  day, week, i);
			 if(AtuoWriteService.find(item.getTime())==false){
				 AtuoWriteService.save(item);
			 }
		 }
		 for(int i=10;i<12;i++){
			 Item	item=new Item( date+i, ""+2, year, month,  day, week, i);
			 if(AtuoWriteService.find(item.getTime())==false){
				 AtuoWriteService.save(item);
			 }
		 }
//		 List<Item> items=AtuoWriteService.getScrollData(0, 11);
//			for(Item item:items)
//			{
//				Log.i("AutoWrite", item.toString());
//			}
	 Log.i("AutoWrite2", "alreadyAutowrite");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
