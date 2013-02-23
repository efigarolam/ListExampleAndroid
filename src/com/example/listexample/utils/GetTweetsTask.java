package com.example.listexample.utils;

import java.util.ArrayList;

import android.os.AsyncTask;

import com.example.listexample.MainActivity.MyAdapter;

public class GetTweetsTask extends AsyncTask<String, Integer, ArrayList<Tweet>> {
	private MyAdapter mAdapter = null;
	
	public void setAdapter(MyAdapter adapter) {
		mAdapter = adapter;
	}
	
	@Override
	protected ArrayList<Tweet> doInBackground(String... params) {
		ArrayList<Tweet> tweets = null;
		
		if (params != null && params.length > 0) {
			try {
				String url = params[0];
				String jsonStr = HttpUtils.connect(url, null);
				tweets = JsonParser.parseTwitterTimeline(jsonStr);
			} catch (Exception e) {
				e.printStackTrace();
				tweets = new ArrayList<Tweet>();
			}
		} else {
			tweets = new ArrayList<Tweet>();
		}
		
		return tweets;
	}
	
	@Override
	protected void onPostExecute(ArrayList<Tweet> result) {
		mAdapter.addElements(result);
	}

}
