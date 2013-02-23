package com.example.listexample;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GPSActivity extends Activity implements OnClickListener, LocationListener {
	
	private final static String mTag = "GpsActivity";
	private Button mBtnEncender = null;
	private Button mBtnApagar = null;
	private Boolean wasTurnedOn = false;
	private LocationManager mLocationManager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		
		mLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		
		mBtnEncender = (Button)findViewById(R.id.btnEncender);
		mBtnApagar = (Button)findViewById(R.id.btnApagar);
		mBtnEncender.setOnClickListener(this);
		mBtnApagar.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnApagar) {
			Log.v(mTag, "onClick Apagar");
			turnOff();
		} else if(v.getId() == R.id.btnEncender) {
			Log.v(mTag, "onClick Encender");
			turnOn();
		}		
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		if(wasTurnedOn) {
			turnOn();
		}
	}
	
	@Override
	protected void onStop() {
		turnOff();
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		mLocationManager = null;
		super.onDestroy();
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.v(mTag, "onLocationChanged");
		Toast.makeText(this, "Nueva ubicación: [" 
				+ location.getLatitude() + ", " 
				+ location.getAltitude() + "]", 
				Toast.LENGTH_LONG).show();
	}
	
	protected void turnOn() {
		if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			mLocationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 5000, 1, this);
		} else if(mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			mLocationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 5000, 1, this);
		} else {
			Location lastLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
			if(lastLocation == null) {
				lastLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			}
			
			if(lastLocation != null) {
				Toast.makeText(this, "Nueva ubicación: [" 
						+ lastLocation.getLatitude() + ", " 
						+ lastLocation.getAltitude() + "]", 
						Toast.LENGTH_LONG).show();
			}			
		}
		
		wasTurnedOn = true;
	}
	
	protected void turnOff() {
		mLocationManager.removeUpdates(this);
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.v(mTag, "onProviderDisabled");
		turnOff();
		turnOn();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.v(mTag, "onProviderEnabled");
		turnOff();
		turnOn();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.v(mTag, "onStatusChanged");
	}
}
