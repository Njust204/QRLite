package com.demo.qrlite;

import com.demo.data.DataHolder;
import com.demo.data.DataManager;
import com.zxing.integration.IntentIntegrator;
import com.zxing.integration.IntentResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button addItemBtn;
	private Button deleteItemBtn;
	private Button searchItemBtn;
	private Button editItemBtn;
	private Button allItemBtn;
	private Button QRScanBtn;
	private Button mainFinishBtn;
	private Button dropTableBtn;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        addItemBtn = (Button)findViewById(R.id.addActivityBtn);
	        deleteItemBtn = (Button)findViewById(R.id.deleteActivityBtn);
	        searchItemBtn = (Button)findViewById(R.id.searchActivityBtn);
	        editItemBtn = (Button)findViewById(R.id.editActivityBtn);
	        allItemBtn = (Button)findViewById(R.id.allActivityBtn);
	        QRScanBtn = (Button)findViewById(R.id.QRActivityBtn);
	        mainFinishBtn = (Button)findViewById(R.id.FinishBtn);
	        
	        addItemBtn.setOnClickListener(mainBtnListener);
	        deleteItemBtn.setOnClickListener(mainBtnListener);
	        searchItemBtn.setOnClickListener(mainBtnListener);
	        editItemBtn.setOnClickListener(mainBtnListener);
	        allItemBtn.setOnClickListener(mainBtnListener);
	        QRScanBtn.setOnClickListener(scanAnything);
	        mainFinishBtn.setOnClickListener(mainBtnListener);
	        
	        DataHolder.dtManager = new DataManager(this);
	        
	    }
	 
	 	@Override  
	    protected void onDestroy() {  
	        DataHolder.dtManager.closeDB();  
	        super.onDestroy(); 
	    }  
	 	
	 	@Override
	 	  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	 	    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	 	    if (result != null) {
	 	      String contents = result.getContents();
	 	      if (contents != null) {
//	 	    	String infoString = DataHolder.dtManager.searchItem(contents); 
	 	    	DataHolder.all2SearchName = contents;
				Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
				MainActivity.this.startActivity(intent1);
//	 	        showDialog(R.string.result_succeeded, result.toString()+infoString+'\n');
	 	      } else {
	 	        showDialog(R.string.result_failed, getString(R.string.result_failed_why));
	 	      }
	 	    }
	 	  }
	 	
	 	private final Button.OnClickListener scanAnything = new Button.OnClickListener() {
	 	    public void onClick(View v) {
	 	      IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
	 	      integrator.initiateScan();
	 	    }
	 	  };
	 	  private void showDialog(int title, CharSequence message) {
	 	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	 	    builder.setTitle(title);
	 	    builder.setMessage(message);
	 	    builder.setPositiveButton("OK", null);
	 	    builder.show();
	 	  }
	 
	 private OnClickListener mainBtnListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.addActivityBtn:
				intent = new Intent(MainActivity.this, AddActivity.class);
				startActivity(intent);
				break;
			case R.id.deleteActivityBtn:
				intent = new Intent(MainActivity.this, DeleteActivity.class);
				startActivity(intent);
				break;
			case R.id.searchActivityBtn:
				intent = new Intent(MainActivity.this, SearchActivity.class);
				startActivity(intent);
				break;
			case R.id.editActivityBtn:
				intent = new Intent(MainActivity.this, EditActivity.class);
				startActivity(intent);
				break;
			case R.id.allActivityBtn:
				intent = new Intent(MainActivity.this, AllActivity.class);
				startActivity(intent);
				break;
			case R.id.FinishBtn:
				MainActivity.this.finish();
				break;
			}
		}
	};


}
