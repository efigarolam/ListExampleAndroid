package com.example.listexample.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
	
	public static ArrayList<Tweet> parseTwitterTimeline(String jsonStr){
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		
		try{
			Tweet tweet = null;
			JSONArray jsonArray = new JSONArray(jsonStr);
			JSONObject jsonObj = null;
			JSONObject jsonUserObj = null;
			
			for (int a = 0; a < jsonArray.length(); a++){
				tweet = new Tweet();
				jsonObj = jsonArray.getJSONObject(a);
				jsonUserObj = jsonObj.getJSONObject("user");
				
				tweet.setUser_image(jsonUserObj.optString("profile_image_url"));
				tweet.setUser_name(jsonUserObj.optString("name"));
				tweet.setTweet(jsonObj.optString("text"));
				tweet.setUrl(jsonUserObj.optString("url"));
				tweet.setId(jsonObj.optString("id_str"));
				
				tweets.add(tweet);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return tweets;
	}
}