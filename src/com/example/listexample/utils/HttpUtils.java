package com.example.listexample.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class HttpUtils {
	
	private static List<NameValuePair> 
			hashTableToListNameValuePair(Hashtable<String, String> postParameters){
		
		ArrayList<NameValuePair> postList = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : postParameters.entrySet()){
			postList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		return postList;
	}
	
	public static String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	public static String connect(String url, Hashtable<String, String> postParameters){
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		HttpPost httpPost = null;
		HttpResponse httpResponse = null;
		String responseStr = null;
		
		try{
			//Creamos cliente
			httpClient = new DefaultHttpClient();
			
			if (postParameters != null){
				//Conexion POST
				httpPost = new HttpPost(url);
				List<NameValuePair> postList = hashTableToListNameValuePair(postParameters);
				httpPost.setEntity(new UrlEncodedFormEntity(postList, "UTF-8"));
				
				httpResponse = httpClient.execute(httpPost);
			}else{
				//Conexion GET
				httpGet = new HttpGet(url);
				httpResponse = httpClient.execute(httpGet);
			}
			
			responseStr = convertStreamToString(httpResponse.getEntity().getContent());
		}catch (Exception e) {
			e.printStackTrace();
			responseStr = "";
		}
		
		return responseStr;
	}
}