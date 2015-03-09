package com.example.samplemanasa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samplemanasa.utils.ConnectionDetector;
import com.example.samplemanasa.utils.GlobalServiceCall;
import com.example.samplemanasa.utils.ServiceMethodListener;

public class LoginActivity extends Activity implements ServiceMethodListener{
	
	private EditText username,password;
	private String mUsername,mPassword;
	private ImageView back;
	private Typeface Machinato_ExtraLight,Machinato;
	private TextView header;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Machinato_ExtraLight = Typeface.createFromAsset(this.getAssets(), "fonts/Jelloween - Machinato ExtraLight.ttf");
		Machinato = Typeface.createFromAsset(this.getAssets(), "fonts/Jelloween - Machinato.ttf");
		header = (TextView) findViewById(R.id.header);
		header.setTypeface(Machinato_ExtraLight);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		username.setTypeface(Machinato);
		password.setTypeface(Machinato);
		back = (ImageView) findViewById(R.id.img_back);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	public void buttonLogin(View v){
		
		mUsername = username.getText().toString();
		mPassword = password.getText().toString();
		
		if (mUsername.length() != 0) {
			if (mPassword.length() != 0) {
				ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
				Boolean isInternetPresent = cd.isConnectingToInternet();
				if (isInternetPresent == true) {
					
					try {
						String classname = "LoginActivity";
						String methodname = "login";
						String url = "http://dev.apppartner.com/AppPartnerProgrammerTest/scripts/login.php";
						List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
						JSONObject job = new JSONObject();
						urlParameters.add(new BasicNameValuePair("username", mUsername));
						urlParameters.add(new BasicNameValuePair("password", mPassword));
						GlobalServiceCall postmethods = new GlobalServiceCall(LoginActivity.this, url, urlParameters, classname, methodname);
						postmethods.execute();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(getApplicationContext(),"Check internet connection", Toast.LENGTH_SHORT).show();
				}
				
			}else {
				Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
			}
		}else {
			Toast.makeText(LoginActivity.this, "Please enter unsername ", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void getResponse(String data, String classname, String methodname) {
		if (classname.equals("LoginActivity") && methodname.equals("login")) {
			try {
				JSONObject object = new JSONObject(data);
				String code = object.getString("code");
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
				alertDialog.setMessage(object.getString("message"));

				alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
				alertDialog.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}