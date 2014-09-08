package moofwd.towns.square.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import moofwd.towns.square.model.EventVO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

public class RefreshNewsEventsTask extends AsyncTask<String, Void, Void> {

	private ProgressDialog dialog;
	private String result;
	private static final String LOADING = "Loading....";
	private static final String GET_EVENT_URL = "http://192.168.1.36:3000/getEventsPost.json";

	public RefreshNewsEventsTask(Fragment fragment) {
		dialog = new ProgressDialog(fragment.getActivity());
	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(LOADING);
		this.dialog.show();
	}

	@Override
	protected Void doInBackground(String... params) {
		InputStream inputStream = null;
		ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
		try {
			// Set up HTTP post

			// HttpClient is more then less deprecated. Need to change to
			// URLConnection
			HttpClient httpClient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost(GET_EVENT_URL);
			httpPost.setEntity(new UrlEncodedFormEntity(param));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();

			// Read content & Log
			inputStream = httpEntity.getContent();
		} catch (UnsupportedEncodingException e1) {
			Log.e("UnsupportedEncodingException", e1.toString());
			e1.printStackTrace();
		} catch (ClientProtocolException e2) {
			Log.e("ClientProtocolException", e2.toString());
			e2.printStackTrace();
		} catch (IllegalStateException e3) {
			Log.e("IllegalStateException", e3.toString());
			e3.printStackTrace();
		} catch (IOException e4) {
			Log.e("IOException", e4.toString());
			e4.printStackTrace();
		}
		// Convert response to string using String Builder
		try {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
			StringBuilder sBuilder = new StringBuilder();

			String line = null;
			while ((line = bReader.readLine()) != null) {
				sBuilder.append(line + "\n");
			}

			inputStream.close();
			result = sBuilder.toString();

		} catch (Exception e) {
			Log.e("StringBuilding & BufferedReader", "Error converting result " + e.toString());
		}
		return null;
	}

//	@SuppressLint("NewApi")
	@Override
	protected void onPostExecute(Void result) {

		// parse JSON data
		try {
			JSONArray jArray = new JSONArray(this.result);
			for (int i = 0; i < jArray.length(); i++) {

				JSONObject jObject = jArray.getJSONObject(i);
				String title = jObject.getString("title");
				String desc = jObject.getString("text");
				EventVO eventVO=new EventVO(title, desc, null);

			} 
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		} catch (JSONException e) {
			Log.e("JSONException", "Error: " + e.toString());
		} // catch (JSONException e)

	}
}
