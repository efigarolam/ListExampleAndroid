package com.example.listexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NavigationAActivity extends Activity implements OnClickListener {
	private final static String mTag = "Activity_A";
	private Button mButton = null;
	private final static int REQUEST_ID_ACTIVITYB = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(mTag, "onCreate()");
		
		setContentView(R.layout.activity_a);
		mButton = (Button)findViewById(R.id.btnGoToB);
		mButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.btnGoToB) {
			Log.d(mTag, "click on gotoB");
			Intent mIntent = new Intent(this, NavigationBActivity.class);
			mIntent.putExtra(NavigationBActivity.DATA_INPUT_COMPANY_NAME, "CI");
			startActivityForResult(mIntent, REQUEST_ID_ACTIVITYB);
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_ID_ACTIVITYB) {
			if (resultCode == RESULT_OK) {
				String userName = data.getExtras().getString(NavigationBActivity.DATA_USER_NAME);
				Toast.makeText(this, "Usuario seleccionado: " + userName, Toast.LENGTH_LONG).show();
			}
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
