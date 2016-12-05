package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.example.domain.Item;

import android.R.bool;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ItemService {
	private DBOpenHelper dbOpenHelper;
	public ItemService(Context context) {
		super();
		this.dbOpenHelper =new DBOpenHelper(context);
	}
	/*public void  payment()
	{
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.beginTransaction();//��������
		try{
			db.execSQL("update person set amount=amount-10 where personid=1");
			db.execSQL("update person set amount=amount+10 where personid=2");	
			db.setTransactionSuccessful();
		} finally{
			db.endTransaction();
		//�������� commit,rollback,
		//������ύ��ع�������ı�־������Ĭ�����������ı�־ΪFALSE.�������ı�־��TRUE,���ύ������ع�
		}	
		
	}*/
	public void  save(Item item)
	{
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into item(time,kind,year ,month ,day ,week ,sjd ) values(?,?,?,?,?,?,?)",
				new Object[]{Long.parseLong(item.getTime()),Integer.parseInt(item.getKind()),Integer.parseInt(item.getYear()),Integer.parseInt(item.getMonth()),Integer.parseInt(item.getDay()),item.getWeek(),item.getSjd()});
		//db.close();
			
	}
	public void  delete(String time)
	{
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from item where time=?",
				new Object[]{Long.parseLong(time)});
	}

	public void  update(Item item)
	{
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("update item set kind=? where time=?",
				new Object[]{item.getKind(),item.getTime()});
	}

	public boolean  find(String time)
	{
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from item where time=?", new String[]{time});
		if(cursor.moveToFirst())
		{
			
			/*String time_2=cursor.getString(cursor.getColumnIndex("time"));
			String kind_2=cursor.getString(cursor.getColumnIndex("kind"));
			String year_2=cursor.getString(cursor.getColumnIndex("year"));
			String month_2=cursor.getString(cursor.getColumnIndex("month"));
			String day_2=cursor.getString(cursor.getColumnIndex("day"));
			String week_2=cursor.getString(cursor.getColumnIndex("week"));
			int sjd_2=cursor.getInt(cursor.getColumnIndex("sjd"));
			
			return  new Item( time_2,kind_2,year_2,month_2,day_2,week_2,sjd_2);*/
			cursor.close();
			return true;
		}else{
			cursor.close();
			return false ;
		}
	}
//	public boolean  find_2(String ye)
//	{
//		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
//		
//		//Cursor cursor=db.rawQuery("select * from item where time=?", new String[]{time});
//		if(.moveToFirst())
//		{
//			
//			/*String time_2=cursor.getString(cursor.getColumnIndex("time"));
//			String kind_2=cursor.getString(cursor.getColumnIndex("kind"));
//			String year_2=cursor.getString(cursor.getColumnIndex("year"));
//			String month_2=cursor.getString(cursor.getColumnIndex("month"));
//			String day_2=cursor.getString(cursor.getColumnIndex("day"));
//			String week_2=cursor.getString(cursor.getColumnIndex("week"));
//			int sjd_2=cursor.getInt(cursor.getColumnIndex("sjd"));
//			
//			return  new Item( time_2,kind_2,year_2,month_2,day_2,week_2,sjd_2);*/
//			cursor.close();
//			return true;
//		}else{
//			cursor.close();
//			return false ;
//		}
//	
//	
//	
//}
	/**
	 * ��ҳ��ȡ��¼
	 * @param offset����ǰ����ټ�¼
	 * @param maxResultÿҳ��ȡ���ټ�¼
	 * @return List<Person> persons
	 */
	public List<Item> getScrollData(int offset,int maxResult)
	{
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from item order by time  desc limit ?,?", 
				new String[]{String.valueOf(offset),String.valueOf(maxResult)});
		List<Item> items=new ArrayList<Item>();
		while(cursor.moveToNext())
		{

			String time_2=cursor.getString(cursor.getColumnIndex("time"));
			String kind_2=cursor.getString(cursor.getColumnIndex("kind"));
			String year_2=cursor.getString(cursor.getColumnIndex("year"));
			String month_2=cursor.getString(cursor.getColumnIndex("month"));
			String day_2=cursor.getString(cursor.getColumnIndex("day"));
			String week_2=cursor.getString(cursor.getColumnIndex("week"));
			int sjd_2=cursor.getInt(cursor.getColumnIndex("sjd"));
			
			items.add( new Item(time_2,kind_2,year_2,month_2 ,day_2,week_2,sjd_2));
		}
		cursor.close();
		return items;
		
	}/**
	 * ��ҳ��ȡ��¼
	 * @param offset����ǰ����ټ�¼
	 * @param maxResultÿҳ��ȡ���ټ�¼
	 * @return cursor
	 */
	public Cursor getCursorScrollData(int offset,int maxResult)
	{
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select time as _id,kind from item order by pid asc limit ?,?", 
				new String[]{String.valueOf(offset),String.valueOf(maxResult)});
	
		return cursor;
		
	}
	/**
	 * ��ȡ��¼����
	 * @return
	 */
	public long getCount()
	{
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*) from item ",null); 
		cursor.moveToFirst();
		long result=cursor.getLong(0);
		cursor.close();
		return result;
	}
	public double []   getWeekInfo(String week)
	{
		double [] result=new double []{0,0,0,0};
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from item order by time  desc ",null); 
		
		cursor.moveToFirst();
		
//		do
//		{
//
//			String time_2=cursor.getString(cursor.getColumnIndex("time"));
//			String kind_2=cursor.getString(cursor.getColumnIndex("kind"));
//			String year_2=cursor.getString(cursor.getColumnIndex("year"));
//			String month_2=cursor.getString(cursor.getColumnIndex("month"));
//			String day_2=cursor.getString(cursor.getColumnIndex("day"));
//			String week_2=cursor.getString(cursor.getColumnIndex("week"));
//			int sjd_2=cursor.getInt(cursor.getColumnIndex("sjd"));
//			Log.i("zhencha", time_2+kind_2+year_2+month_2 +day_2+week_2+sjd_2);
//			
//		}while(cursor.moveToNext());
//		cursor.moveToFirst();
		if(week.equals("周日"))
		{
		   for(int j=0;j<84;j++)//����һ��
		   {
			   cursor.moveToNext();
		   }
		   for(int j=0;j<84;j++)
		   {
			   	switch(cursor.getInt(cursor.getColumnIndex("kind"))){
				case 0:{result[0]++;break;}
				case 1:{result[1]++;break;}
				case 2:{result[2]++;break;}
				case 3:{result[3]++;break;}}
			
			   	cursor.moveToNext();
		   }
		}
		else{
			//Log.i("zhencha", String.valueOf(cursor.getCount()));/////////////////////////////////////////2
			boolean NotFind=true;
			do
			{		
					if(cursor.getString(cursor.getColumnIndex("week")).equals("周日"))
					{ 
					   for(int j=0;j<84;j++)                                                                                            
					   {
						   	switch(cursor.getInt(cursor.getColumnIndex("kind"))){
							case 0:{result[0]++;break;}
							case 1:{result[1]++;break;}
							case 2:{result[2]++;break;}
							case 3:{result[3]++;break;}}
						
						   	cursor.moveToNext();
					   }
					   NotFind=false;
							
					}
							
			/*	String time_2=cursor.getString(cursor.getColumnIndex("time"));
				String kind_2=cursor.getString(cursor.getColumnIndex("kind"));
				String year_2=cursor.getString(cursor.getColumnIndex("year"));
				String month_2=cursor.getString(cursor.getColumnIndex("month"));
				String day_2=cursor.getString(cursor.getColumnIndex("day"));
				String week_2=cursor.getString(cursor.getColumnIndex("week"));
				int sjd_2=cursor.getInt(cursor.getColumnIndex("sjd"));
				Log.i("zhencha", time_2+kind_2+year_2+month_2 +day_2+week_2+sjd_2);	
				//////////////////////////////////////////////////////////////////////////3	
				 
				 */
			}while(cursor.moveToNext()&&NotFind==true);
					
			}
		
		for(int m=0;m<4;m++)
		{
			//Log.i("zhencha", String.valueOf(result[m]));
			//////////////////////////////////////////////////////////////////////////////4
			result[m]/=84.0;
			Log.i("result_week", String.valueOf(result[m]));
			///////////////////////////////////////////////////////////////////////////////5
		}
		cursor.close();
		
		return result;
		
		
	}
	public boolean isRunNian(int temp){
		if(temp%100==0){
			if(temp%400==0)
				return true;
		}else if(temp%4==0){
			return true;
		}
		return false;
	}
	public double[] getMonthInfo(String year, String month) {
		
		double [] result=new double []{0,0,0,0};
		int month_2=Integer.parseInt(month);
		int year_2=Integer.parseInt(year);
		if(month_2>1)
		{
			month_2--;
		}else if(month_2==1){
			year_2--;
			month_2=12;
		}
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from item where year=? and month=? order by time  desc "
				,new String[]{String.valueOf(year_2),String.valueOf(month_2)}); 
		cursor.moveToFirst();
		if(month_2==1||month_2==3||month_2==5||month_2==7||month_2==8||month_2==10||month_2==12){
			if(cursor.getCount()!=372)//应该12*31=372条记录
			{
				return result;
			}
		}else if(month_2==4||month_2==6||month_2==9||month_2==11){
			if(cursor.getCount()!=360)//应该12*30=360条记录
			{
				return result;
			}
		}else if(month_2==2&&isRunNian(year_2)==true){
			if(cursor.getCount()!=348)//应该12*29=348条记录
			{
				return result;
			}
		}else if(month_2==2&&isRunNian(year_2)==false){
			if(cursor.getCount()!=336)//应该12*28=336条记录
			{
				return result;
			}
		}
		cursor.moveToFirst();
//		do
//		{
//
//			String time_3=cursor.getString(cursor.getColumnIndex("time"));
//			String kind_3=cursor.getString(cursor.getColumnIndex("kind"));
//			String year_3=cursor.getString(cursor.getColumnIndex("year"));
//			String month_3=cursor.getString(cursor.getColumnIndex("month"));
//			String day_3=cursor.getString(cursor.getColumnIndex("day"));
//			String week_3=cursor.getString(cursor.getColumnIndex("week"));
//			int sjd_3=cursor.getInt(cursor.getColumnIndex("sjd"));
//			Log.i("zhencha", time_3+kind_3+year_3+month_3 +day_3+week_3+sjd_3);
//			
//		}while(cursor.moveToNext());
//		cursor.moveToFirst();
		do
		{
			   	switch(cursor.getInt(cursor.getColumnIndex("kind"))){
				case 0:{result[0]++;break;}
				case 1:{result[1]++;break;}
				case 2:{result[2]++;break;}
				case 3:{result[3]++;break;}}
			
		}while(	cursor.moveToNext());	   
		 
		for(int m=0;m<4;m++)
		{
			//Log.i("zhencha", String.valueOf(result[m]));
			//////////////////////////////////////////////////////////////////////////////4
			result[m]/=372.0;
			Log.i("result_month", String.valueOf(result[m]));
			///////////////////////////////////////////////////////////////////////////////5
		}
		cursor.close();
		return result;
	}


}
