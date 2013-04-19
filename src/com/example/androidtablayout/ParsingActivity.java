package com.example.androidtablayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ParsingActivity extends Activity {
	/** Called when the activity is first created. */
	public static List<JSONitem> list1 = new ArrayList<JSONitem>();
	private ListView listView;
	private ParsingActivityAdapter mAdapter;
	private static EditText mSearchArtist;
	private List<JSONitem> mList;
	private String aname, cname, image, cprice;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parsing_layout);
		
		listView = (ListView) findViewById(R.id.lstdata);
		
		String readTwitterFeed = readTwitterFeed();
		try {
			JSONObject mainJson = new JSONObject(readTwitterFeed);
			
			JSONArray jsonArray = mainJson.getJSONArray("results");
			Log.i(ParsingActivity.class.getName(), "results"
					+ jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				
//				JSONObject json= (JSONObject) new JSONTokener(readTwitterFeed).nextValue();
//		        JSONObject json2 = jsonObject2.getJSONObject("results");
//		        aname = (String) json2.get("artistName");
//		        cname = (String) json2.get("collectionName");
//		        image = (String) json2.get("artworkUrl60");
//		        cprice = (String) json2.get("collectionPrice");
				
				aname = jsonObject2.getString("artistName");
				cname = jsonObject2.getString("collectionName");
				image = jsonObject2.optString("artworkUrl60");
				cprice = jsonObject2.getString("collectionPrice");
				

				JSONitem item = new JSONitem();
				item.setId("ARTIST:  "  + aname);
				item.setTitle("COLLECTION:  "  + cname);
				item.setLargeimage(image);
				item.setDesignation("PRICE:  $" + cprice);				
				list1.add(item);

			}
			ParsingActivityAdapter adapter = new ParsingActivityAdapter(this,
					list1);
			listView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(PhotoActivity.this,
//						PhotoGridActivity.class);
//
//				startActivity(intent);
//
//			}
//		});
		
		init();

	}
	
	private void init() {
		mSearchArtist = (EditText) findViewById(R.id.srchartist);
		mAdapter = new ParsingActivityAdapter(this, list1);
		listView.setAdapter(mAdapter);
		
		mSearchArtist.addTextChangedListener(new TextWatcher() {
			
			public void afterTextChanged(Editable s) {
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			
			mAdapter.getFilter().filter(s.toString());		
						
			mAdapter.notifyDataSetChanged();
			
			

			
			}
			});
		
	}

	public String readTwitterFeed() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
//				"http://financemyhome.com/webservice/bio.php");
				"http://itunes.apple.com/search?term=search");
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(ParsingActivity.class.toString(), "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}