package com.example.samplemanasa.utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
public class GlobalServiceCall extends AsyncTask<Void, Void, String>{
	Context context;
	ServiceMethodListener listener;
	String url;
	List<NameValuePair> urlParameters;
	String classname;
	String methodname;
	ProgressDialog progress;

	public GlobalServiceCall(Context _context, String _url,List<NameValuePair> _urlParameters,String _classname,String _methodname) {
		context = _context;
		url = _url;
		urlParameters = _urlParameters;
		classname = _classname;
		methodname = _methodname;
		progress=new ProgressDialog(_context);
		progress.setCancelable(false);
		progress.setMessage("Loading..");
	}

	@Override                                 
	protected void onPreExecute() {
		super.onPreExecute();
		progress.show();
	
	}           

	@Override
	protected String doInBackground(Void... params) {
		try {
			listener = (ServiceMethodListener) context;
		} catch (Exception e) {
			e.printStackTrace();
		}

		String responseBody = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpParams httpParams = httpclient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
			HttpConnectionParams.setSoTimeout(httpParams, 60000);
			HttpPost httppost = new HttpPost(url);
			Log.i("JJA", "In HttpPostMethodCalls urlParameters is ::"+urlParameters);
			httppost.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = httpclient.execute(httppost);
			int code = response.getStatusLine().getStatusCode();
			if (code == 500) {
				responseBody = "500";
			}else {
				responseBody = EntityUtils.toString(response.getEntity());
			}
			return responseBody;
		} catch(ConnectTimeoutException e){
		progress.dismiss();
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody;

	}  

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.i("FAP", " result responseBody"+result);
		if( result == null ){
			progress.dismiss();
			String message = "Connection time out !!!!!!!";
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		}else{
			progress.dismiss();
			listener.getResponse(result, classname, methodname);
		}
	}
}
