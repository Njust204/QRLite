package com.demo.qrlite;

import com.demo.data.DataHolder;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class LoginActivity extends Activity {

	private Button loginBtn;
	private EditText passwordET;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        passwordET = (EditText)findViewById(R.id.passwordET);
        loginBtn.setOnClickListener(loginListener);
    }
    
    @Override  
    protected void onPause() {  
        passwordET.setText(""); 
        super.onPause(); 
    }  
    
    private OnClickListener loginListener = new OnClickListener() {
		
		public void onClick(View v) {

			String passwordStr = passwordET.getText().toString();
			if(passwordStr.equals(DataHolder.password)){
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				LoginActivity.this.startActivity(intent);
			}else {
				Toast.makeText(LoginActivity.this, "您输入的密码不正确", Toast.LENGTH_SHORT).show();
			}
			
		}
	};

}
