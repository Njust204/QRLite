package com.demo.data;

import java.io.File;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DataHolder {

	public static DataManager dtManager = null;
//	public static SQLiteDatabase database = null;
	public static String password = "1"; 
//	public static Integer idValue = 1;
	public static String all2SearchName = null;
	public static String all2DeleteName = null;
	public static String all2EditName = null;
	public static String all2ShowPic = null;
	public static final String PICDOCUMENTPATH = Environment.getExternalStorageDirectory()
			.toString()
			+ File.separator
			+ "QRLitePhotos"
			+ File.separator;
}
