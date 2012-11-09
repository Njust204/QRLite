package com.demo.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DataManager {

	private DataHelper helper;
	private SQLiteDatabase db;

	public DataManager(Context context) {
		helper = new DataHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}

	/**
	 * add
	 */
	public int addItem(String key, String value, String groupname, String img) {

		if (searchItem(key) == null) {
			db.execSQL("INSERT INTO items (name,info,groupname,img) VALUES(?, ?, ?, ?)", new String[] { key, value,
					groupname, img});
			return 1;
		}
		return 0;
	}

	/**
	 * update item's info
	 */
	public int updateItemInfo(String key, String value) {
		if (searchItem(key) != null) {
			db.execSQL("UPDATE items SET info = ? WHERE name = ? ", new String[] { value, key });
			return 1;
		}
		return 0;
	}

	public int updateItemGroup(String key, String value) {
		if (searchItem(key) != null) {
			db.execSQL("UPDATE items SET groupname = ? WHERE name = ? ", new String[] { value, key });
			return 1;
		}
		return 0;
	}

	public int updateItemImgInfo(String key, String value) {
		if (searchItem(key) != null) {
			db.execSQL("UPDATE items SET img = ? WHERE name = ? ", new String[] { value, key });
			return 1;
		}
		return 0;
	}
	
	/**
	 * delete
	 */
	public int deleteItem(String key) {
		if (searchItem(key) != null) {
			db.execSQL("DELETE FROM items WHERE name = ?", new String[] { key });
			// this.db.close();
			return 1;
		}
		return 0;

	}

	public List<String> queryByName() {
		ArrayList<String> itemsList = new ArrayList<String>();
		Cursor c = db.rawQuery("SELECT name FROM items", null);
		if (c != null) {
			while (c.moveToNext()) {
				String itemNameTemp = new String();
				itemNameTemp = c.getString(c.getColumnIndex("name"));
				// item.info = c.getString(c.getColumnIndex("info"));
				itemsList.add(itemNameTemp);
			}
		}
		c.close();
		return itemsList;
	}

	/**
	 * query all items, return cursor
	 * public Cursor queryTheCursor() {
		Cursor c = db.rawQuery("SELECT name FROM items", null);
		return c;
	}
	 */
	

	public List<String> queryByGroup(String groupName) {
		ArrayList<String> itemsList = new ArrayList<String>();
		Cursor c = db.rawQuery("SELECT name FROM items WHERE groupname = ?", new String[] { groupName });
		if (c != null) {
			while (c.moveToNext()) {
				String itemNameTemp = new String();
				itemNameTemp = c.getString(c.getColumnIndex("name"));
				itemsList.add(itemNameTemp);
			}
		}
		c.close();
		return itemsList;
	}
	
	public String searchItem(String key) {
		Cursor c = db.rawQuery("SELECT info FROM items WHERE name =?", new String[] { key });
		if (c != null) {
			if (c.moveToFirst()) {
				int numColnum = c.getColumnIndex("info");
				String result = c.getString(numColnum);
				c.close();
				return result;
			} else {
				c.close();
				return null;
			}
		} else {
			return null;
		}
	}

	
	public String searchItemGroup(String key) {
		Cursor c = db.rawQuery("SELECT groupname FROM items WHERE name =?", new String[] { key });
		if (c != null) {
			if (c.moveToFirst()) {
				int numColnum = c.getColumnIndex("groupname");
				String result = c.getString(numColnum);
				c.close();
				return result;
			} else {
				c.close();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public String searchItemImg(String key) {
		Cursor c = db.rawQuery("SELECT img FROM items WHERE name =?", new String[] { key });
		if (c != null) {
			if (c.moveToFirst()) {
				int numColnum = c.getColumnIndex("img");
				String result = c.getString(numColnum);
				c.close();
				return result;
			} else {
				c.close();
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * close database
	 */
	public void closeDB() {
		db.close();
	}

}
