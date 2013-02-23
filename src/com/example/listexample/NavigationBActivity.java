package com.example.listexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NavigationBActivity extends Activity implements OnClickListener {
	private final static String mTag = "Activity_B";
	private Button mButton = null;
	public final static String DATA_USER_NAME = "DATA_USER_NAME";
	public final static String DATA_INPUT_COMPANY_NAME = "DATA_INPUT_COMPANY_NAME";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(mTag, "onCreate()");
		
		String companyName = getIntent().getExtras().getString(DATA_INPUT_COMPANY_NAME);
		Toast.makeText(this, "Llegó: " + companyName, Toast.LENGTH_LONG).show();
		setContentView(R.layout.activity_b);
		mButton = (Button)findViewById(R.id.btnGoToA);
		mButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.btnGoToA) {
			Log.d(mTag, "click on gotoA");
			
			Intent data = new Intent();
			data.putExtra(DATA_USER_NAME, "Manolo");
			setResult(RESULT_OK, data);
			
			finish();
		}
		
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
