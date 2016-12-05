package com.example.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "mrtime.db", null,1);//默认保存在<包>/databases/
		
	}

	public void onCreate(SQLiteDatabase db) {//数据库第一次被创建的时候的调用
		db.execSQL("CREATE TABLE item(time long primary key unique,kind int,year int,month int,day int,week varchar(15),sjd int)");
				 

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//数据库版本发生改变的时候调用
		//db.execSQL("ALTER TABLE person ADD amount integer ");

	}

}
