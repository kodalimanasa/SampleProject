package com.example.samplemanasa;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationActivity extends Activity{

	private ImageView back,img;
	private Typeface Machinato_ExtraLight,Machinato_SemiBold_Italic;
	private TextView header;
	private AnimatorSet mAnimationSet;
	private TextView tv1,tv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		Machinato_ExtraLight = Typeface.createFromAsset(this.getAssets(), "fonts/Jelloween - Machinato ExtraLight.ttf");
		Machinato_SemiBold_Italic = Typeface.createFromAsset(this.getAssets(), "fonts/Jelloween - Machinato SemiBold Italic.ttf");
		header = (TextView) findViewById(R.id.header);
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		back = (ImageView) findViewById(R.id.img_back);
		img = (ImageView) findViewById(R.id.img);
		
		
		header.setTypeface(Machinato_ExtraLight);
		tv1.setTypeface(Machinato_ExtraLight);
		tv2.setTypeface(Machinato_SemiBold_Italic);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
			}
		});

		
	}
	public void buttonFade(View v){
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(img, "alpha",  1f, 0f);
		fadeOut.setDuration(2000);
		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
		fadeIn.setDuration(2000);

		mAnimationSet = new AnimatorSet();
		mAnimationSet.play(fadeIn).after(fadeOut);
		mAnimationSet.start();
	}

}