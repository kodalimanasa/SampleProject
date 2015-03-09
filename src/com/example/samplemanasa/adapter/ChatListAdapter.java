package com.example.samplemanasa.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.samplemanasa.R;
import com.example.samplemanasa.Model.PersonBean;
import com.example.samplemanasa.imageloader.ImageLoader;

public class ChatListAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<PersonBean> list;
	private Holder holder;
	private Typeface Machinato_Light,Machinato;
	
	public ChatListAdapter(Context _context,ArrayList<PersonBean> _list) {
		context = _context;
		list = _list;
		Machinato_Light = Typeface.createFromAsset(_context.getAssets(), "fonts/Jelloween - Machinato Light.ttf");
		Machinato = Typeface.createFromAsset(_context.getAssets(), "fonts/Jelloween - Machinato.ttf");
	}

	@Override
	public int getCount() {
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(final int position, View v, ViewGroup parent) {
		final Holder holder;
		if (v == null){
			LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.adapter_chat, null);
			holder = new Holder();
			holder.img = (ImageView) v.findViewById(R.id.img);
			holder.tv1 = (TextView) v.findViewById(R.id.tv1);
			holder.tv2 = (TextView) v.findViewById(R.id.tv2);
			v.setTag(holder);
		} 
		else{
			holder = (Holder) v.getTag();
		}
		
		holder.tv1.setTypeface(Machinato);
		holder.tv2.setTypeface(Machinato_Light);
		ImageLoader imageLoader = new ImageLoader(context);
		imageLoader.DisplayImage(list.get(position).getImage(), holder.img);
		holder.tv1.setText(list.get(position).getName());
		holder.tv2.setText(list.get(position).getMessage());
		//holder.img.setImageBitmap(getCroppedBitmap(imageLoader.getBitmap(list.get(position).getImage())));

		return v;
	}
	class Holder{
		public  ImageView img;
		public  TextView tv1;
		public  TextView tv2;

	}
	
}