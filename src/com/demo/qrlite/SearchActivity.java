package com.demo.qrlite;

import java.io.File;

import com.demo.data.DataHolder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity {
	
	private Button searchItemBtn;
	private EditText key2SearchET;
	private ImageView img2ShowIV;
	private TextView value2ShowTV;
	private TextView grouSearchShowTV;
	private String initString;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_search);
	        searchItemBtn = (Button)findViewById(R.id.searchItemBtn);
	        key2SearchET = (EditText)findViewById(R.id.keyToSearchET);
	        img2ShowIV = (ImageView)findViewById(R.id.imgToShow_inSearchAct);
	        value2ShowTV = (TextView)findViewById(R.id.valueToShowTV);
	        grouSearchShowTV = (TextView)findViewById(R.id.groupSearchShowTV);
	        
	        searchItemBtn.setOnClickListener(searchClickListener);
	        
	        initString = DataHolder.all2SearchName;
	        if( initString != null){
	        	key2SearchET.setText(initString);
	        	DataHolder.all2SearchName = null;
	        }
	        
	    }
	 
	 private OnClickListener searchClickListener = new OnClickListener() {
		
		public void onClick(View v) {
			String key2SearchStr = key2SearchET.getText().toString();
			// TODO :搜索出结果，显示在TV中，注意搜不到key情况和所输入key为空值情况
			if(!key2SearchStr.equals("")){
				String result = DataHolder.dtManager.searchItem(key2SearchStr);
				String groupString=DataHolder.dtManager.searchItemGroup(key2SearchStr);
				String imgString = DataHolder.dtManager.searchItemImg(key2SearchStr);
				String filepath = DataHolder.PICDOCUMENTPATH+key2SearchStr+".jpg";
				if(result != null){
					grouSearchShowTV.setText(groupString);
					value2ShowTV.setText(result);
					if( imgString!=null && !imgString.equals("")){
						File file = new File(filepath);
		                if (file.exists()) {
		                	img2ShowIV.setVisibility(View.VISIBLE);
		                    Bitmap bm = BitmapFactory.decodeFile(filepath);
		                        //将图片显示到ImageView中
		                    img2ShowIV.setImageBitmap(bm);
		                }
					}
					Toast.makeText(SearchActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
				}else {
					value2ShowTV.setText("");
					img2ShowIV.setVisibility(View.GONE);
					Toast.makeText(SearchActivity.this, "未找到该值", Toast.LENGTH_SHORT).show();
				}
			}else {
				Toast.makeText(SearchActivity.this, "请在输入框内输入内容", Toast.LENGTH_SHORT).show();
			}
		}
	};

}
