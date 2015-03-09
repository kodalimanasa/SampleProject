package com.example.samplemanasa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread thread = new Thread(){
			public void run(){
				try {
					sleep(2000);
					Intent i = new Intent(getApplicationContext(),MainActivity.class);
					startActivity(i);
					finish();
				} catch (Exception e) { 
					e.printStackTrace();
				}	
			}
		};thread.start();
	}

}
