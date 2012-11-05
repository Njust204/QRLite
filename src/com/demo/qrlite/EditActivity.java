package com.demo.qrlite;

import com.demo.data.DataHolder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity {
	
	private Button searchItem2EditBtn;
	private Button editItemBtn;
	private Button pickPicForEditBtn;
	private EditText key2EditET;
	private EditText value2EditET;
	private String value2EditStr = null;
	private String key2EditStr = null;
	private String result = null;
	private String initString = null;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_edit);
	        searchItem2EditBtn = (Button)findViewById(R.id.searchItem2EditBtn);
	        editItemBtn = (Button)findViewById(R.id.editItemBtn);
	        pickPicForEditBtn = (Button)findViewById(R.id.pickItemPicBtn_inEditAct);
	        key2EditET = (EditText)findViewById(R.id.keyToEditET);
	        value2EditET = (EditText)findViewById(R.id.valueToEditET);
	        searchItem2EditBtn.setOnClickListener(editItemClickListener);
	        editItemBtn.setOnClickListener(editItemClickListener);
	        pickPicForEditBtn.setOnClickListener(editItemClickListener);
	        initString = DataHolder.all2EditName;
	        if( initString != null){
	        	key2EditET.setText(initString);
	        	DataHolder.all2EditName = null;
	        }
	    }
	 
	 @Override
	 	public void onPause(){
		 	editItemBtn.setVisibility(View.GONE);
		 	pickPicForEditBtn.setVisibility(View.GONE);
		 	value2EditET.setText("");
		 	super.onPause();
	 	}

	 private OnClickListener editItemClickListener = new OnClickListener() {
		
		public void onClick(View v) {
			
			EditActivity.this.key2EditStr = key2EditET.getText().toString();
//			EditActivity.this.result = DataHolder.dtManager.searchItem(key2EditStr);
			switch (v.getId()) {
			case R.id.searchItem2EditBtn:
				//TODO 根据key值修改，内容ET的text，并可见化editBTN，注意查不到的情况和所输键值为空的情况
				if(!key2EditStr.equals("")){
					result = DataHolder.dtManager.searchItem(key2EditStr);
					if(result != null){
						value2EditET.setText(result);
						editItemBtn.setVisibility(View.VISIBLE);
						pickPicForEditBtn.setVisibility(View.VISIBLE);
						Toast.makeText(EditActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
					}else {
						value2EditET.setText("");
						Toast.makeText(EditActivity.this, "未找到该项值", Toast.LENGTH_SHORT).show();
					}
//					AddActivity.this.finish();
				}else {
					Toast.makeText(EditActivity.this, "请输入键值内容", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.editItemBtn:
				//TODO 根据Key和Value修改条目，搞定后再次清空两个ET以及设置本身不可见
				key2EditStr = key2EditET.getText().toString();
				value2EditStr = value2EditET.getText().toString();
				if(!key2EditStr.equals("") && !value2EditStr.equals("") && key2EditStr.length()<50 && value2EditStr.length()<150){
					if(DataHolder.dtManager.updateItemInfo(key2EditStr, value2EditStr) == 1){
						editItemBtn.setVisibility(View.GONE);
						value2EditET.setText("");
						key2EditET.setText("");
						Toast.makeText(EditActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(EditActivity.this, "修改失败，请检查键值", Toast.LENGTH_SHORT).show();
					}
				}else {
					if(key2EditStr.length() >= 50){
						Toast.makeText(EditActivity.this, "键值超长", Toast.LENGTH_SHORT).show();
					}
					if(value2EditStr.length() >= 150){
						Toast.makeText(EditActivity.this, "内容超长", Toast.LENGTH_SHORT).show();
					}
					if(key2EditStr.equals("") || value2EditStr.equals("")){
						Toast.makeText(EditActivity.this, "请输入待改值", Toast.LENGTH_SHORT).show();
					}
				}
				break;
			case R.id.pickItemPicBtn_inEditAct:
				key2EditStr = key2EditET.getText().toString();
				if(!key2EditStr.equals("")){
					Intent intent = new Intent(EditActivity.this, PickPicActivity.class);
					intent.putExtra("filename", key2EditStr);
					startActivity(intent);
				}
				break;
			}
			
		}
	};

}
