package com.demo.qrlite;

import com.demo.data.DataHolder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AddActivity extends Activity {
	
	private Button addItemButton;
	private EditText keyEditText;
	private EditText valueEditText;
	private Spinner groupSpinner;
	private String groupName;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_add);
	        addItemButton = (Button)findViewById(R.id.addItemBtn);
	        keyEditText = (EditText)findViewById(R.id.keyToAddET);
	        valueEditText = (EditText)findViewById(R.id.valueToAddET);
	        groupSpinner = (Spinner)findViewById(R.id.groupChoose);
	        addItemButton.setOnClickListener(addClickListener);
	        groupSpinner.setOnItemSelectedListener(groupItemClickListener);
	        
	    }
 
	private OnClickListener addClickListener = new OnClickListener() {
		
		public void onClick(View v) {
			
			String keyString = keyEditText.getText().toString();
			String valueString = valueEditText.getText().toString();
			//TODO ：添加条目进数据库，注意是否已存在key值情况，以及两个String值不能为空，成功后告诉UI
			if(!keyString.equals("")  && !valueString.equals("") && keyString.length()<50 && !groupName.equals("")){
				if( DataHolder.dtManager.addItem( keyString, valueString,groupName,null,null) == 1){
					Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
					AddActivity.this.keyEditText.setText("");
					AddActivity.this.valueEditText.setText("");
				}else {
					Toast.makeText(AddActivity.this, "已存在同名值", Toast.LENGTH_SHORT).show();
				}
//				AddActivity.this.finish();
			}else {
				if(keyString.length() >= 50){
					Toast.makeText(AddActivity.this, "键值超长", Toast.LENGTH_SHORT).show();
				}
				if(valueString.length() >= 150) {
					Toast.makeText(AddActivity.this, "内容超长", Toast.LENGTH_SHORT).show();
				}
				if(keyString.equals("") || valueString.equals("")){
					Toast.makeText(AddActivity.this, "请在输入框内输入内容并选择组别", Toast.LENGTH_SHORT).show();
				}
			}
		}
	};
	
	private OnItemSelectedListener groupItemClickListener= new  OnItemSelectedListener() {

		public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
			groupName =  adapterView.getItemAtPosition(position).toString();
//			Toast.makeText(AddActivity.this, groupName, Toast.LENGTH_SHORT).show();
		}
		

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};

		
		

}
