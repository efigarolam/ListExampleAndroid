package com.example.listexample.webapp;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.listexample.R;
import com.example.listexample.utils.HttpUtils;

public class MyWebAppActivity extends Activity {
	
	private WebView mBrowser = null;
	private static final String mTag = "MyWebAppActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webapp);
		
		mBrowser = (WebView)findViewById(R.id.wbBrowser);
		initBrowser();
	}
	
	protected void initBrowser() {
		WebSettings webSettings = mBrowser.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		mBrowser.addJavascriptInterface(new WebViewBridge(this), "AndroidInterface");
		mBrowser.setWebViewClient(new MyWebViewClient());
		//mBrowser.loadData(getHtml(), "text/html", "UTF-8");
		mBrowser.loadUrl("http://agenda-jqm.herokuapp.com/");
	}
	
	protected String getHtml() {
		InputStream is = null;
		String html = null;
		try {
			is = getAssets().open("htdocs/index.html");
			html = HttpUtils.convertStreamToString(is);
		} catch (Exception e) {
			html = "";
			e.printStackTrace();
		} finally {
			if (is != null) {
				try { is.close(); } catch(Exception ex) {}
			}
		}
		
		return html;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
			if(mBrowser.canGoBack()) {
				mBrowser.goBack();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
			return super.shouldOverrideKeyEvent(view, event);
		}
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.d(mTag, "URL: " + url);
			return super.shouldOverrideUrlLoading(view, url);
		}
	}
}
