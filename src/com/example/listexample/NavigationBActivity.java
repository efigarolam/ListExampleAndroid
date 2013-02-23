package com.example.listexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NavigationBActivity extends Activity {
	private final static String mTag = "Activity_B";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(mTag, "onCreate()");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(mTag, "onRestart()");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(mTag, "onStart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(mTag, "onResume()");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(mTag, "onPause()");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(mTag, "onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(mTag, "onDestroy()");
	}
}
