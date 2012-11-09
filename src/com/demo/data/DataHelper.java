package com.demo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "QRLite.db";
	private static final int DATABASE_VERSION = 1;

	public DataHelper(Context context) {
		//CursorFactory设置为null,使用默认值
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	
	//数据库第一次被创建时onCreate会被调用  
	@Override
	public void onCreate(SQLiteDatabase db) {
//		
		db.execSQL("CREATE TABLE IF NOT EXISTS items"
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+"name NVARCHAR(50) NOT NULL,"
				+"info NTEXT NOT NULL,"
				+"groupname NVARCHAR(50),"
				+"img NVARCHAR(150))");
	}

	
	//如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade  
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS items");
		this.onCreate(db);
	}
	
}
