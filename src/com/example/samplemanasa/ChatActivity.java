package com.example.samplemanasa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.samplemanasa.Model.PersonBean;
import com.example.samplemanasa.adapter.ChatListAdapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChatActivity extends Activity{

	private ImageView back;
	private ListView lv;
	private ArrayList<PersonBean> chat_list = new ArrayList<PersonBean>();
	private Typeface Machinato_ExtraLight;
	private TextView header;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Machinato_ExtraLight = Typeface.createFromAsset(this.getAssets(), "fonts/Jelloween - Machinato ExtraLight.ttf");
		header = (TextView) findViewById(R.id.header);
		header.setTypeface(Machinato_ExtraLight);
		back = (ImageView) findViewById(R.id.img_back);
		lv = (ListView) findViewById(R.id.lv);
		
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		try {
			InputStream is = getResources().openRawResource(R.raw.chat_data);
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
			    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			    int n;
			    while ((n = reader.read(buffer)) != -1) {
			        writer.write(buffer, 0, n);
			    }
			} finally {
			    is.close();
			}
			chat_list.clear();
			String jsonString = writer.toString();
			JSONObject object = new JSONObject(jsonString);
			JSONArray data_array = object.getJSONArray("data");
			for (int i = 0; i < data_array.length(); i++) {
				JSONObject Person = data_array.getJSONObject(i);
				
				PersonBean bean = new PersonBean();
				bean.setId(Person.getString("user_id"));
				bean.setImage(Person.getString("avatar_url"));
				bean.setMessage(Person.getString("message"));
				bean.setName(Person.getString("username"));
				
				chat_list.add(bean);
			}
			
			ChatListAdapter adapter = new ChatListAdapter(ChatActivity.this, chat_list);
			lv.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}