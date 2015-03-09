package com.example.samplemanasa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Typeface Machinato_Bold;
	private TextView codingTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		codingTask = (TextView) findViewById(R.id.tv1);
		Machinato_Bold = Typeface.createFromAsset(this.getAssets(), "fonts/Jelloween - Machinato Bold.ttf");  
		codingTask.setTypeface(Machinato_Bold);
	}

	public void buttonChat(View v){

		Intent i = new Intent(MainActivity.this, ChatActivity.class);
		startActivity(i);

	}
	public void buttonLogin(View v){
		Intent i = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(i);
	}
	public void buttonAnimation(View v){
		Intent i = new Intent(MainActivity.this, AnimationActivity.class);
		startActivity(i);
	}
}
