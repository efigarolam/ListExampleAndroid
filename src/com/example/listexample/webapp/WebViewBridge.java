package com.example.listexample.webapp;

import android.content.Context;
import android.widget.Toast;

public class WebViewBridge {
	Context mContext = null;
	
	public WebViewBridge(Context context) {
		mContext = context;
	}
	
	public void showMessage(String message) {
		Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
	}
}
