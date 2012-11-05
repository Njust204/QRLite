package com.demo.qrlite;

import com.demo.data.DataHolder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends Activity {
	
	private Button deleteItemBtn;
	private EditText deleteKeyET;
	private String initString = null;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_delete);
	        deleteItemBtn = (Button)findViewById(R.id.deleteItemBtn);
	        deleteKeyET = (EditText)findViewById(R.id.toDelKeyET);
	        deleteItemBtn.setOnClickListener(deleteItemlListener);
	        
	        initString = DataHolder.all2DeleteName;
	        if( initString != null){
	        	deleteKeyET.setText(initString);
	        	DataHolder.all2DeleteName = null;
	        }
	    }

	 private OnClickListener deleteItemlListener = new OnClickListener() {
		
		public void onClick(View v) {
			
			String toDelKey = deleteKeyET.getText().toString();
			// TODO :从数据库中删除该项，注意，无该项和string为空情况，此外最好弹出alertdialog对话框确认为宜，成功后告诉UI
			if(!toDelKey.equals("")){
				if( DataHolder.dtManager.deleteItem(toDelKey) == 1){
					Toast.makeText(DeleteActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(DeleteActivity.this, "不存在该值", Toast.LENGTH_SHORT).show();
				}
			}else {
				Toast.makeText(DeleteActivity.this, "请在输入框内输入内容", Toast.LENGTH_SHORT).show();
			}
		}
	};
}
