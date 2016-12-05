package com.example.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "mrtime.db", null,1);//Ĭ�ϱ�����<��>/databases/
		
	}

	public void onCreate(SQLiteDatabase db) {//���ݿ��һ�α�������ʱ��ĵ���
		db.execSQL("CREATE TABLE item(time long primary key unique,kind int,year int,month int,day int,week varchar(15),sjd int)");
				 

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//���ݿ�汾�����ı��ʱ�����
		//db.execSQL("ALTER TABLE person ADD amount integer ");

	}

}
