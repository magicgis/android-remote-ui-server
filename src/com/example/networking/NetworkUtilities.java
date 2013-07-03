package com.example.networking;

import com.example.app1.MainActivity;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtilities {
	
	private Context context;
	
	public NetworkUtilities(Context context) {
		this.context = context;
	}
	
	/**
	 * Checks, whether the device is connected to network either by wifi or 3G
	 * 
	 * @return true, if the device is connected to network
	 */
	public boolean isConnected() {
		
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(MainActivity.CONNECTIVITY_SERVICE);

		// 3G flag
		Boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

		// wifi flag
		Boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		
		
		if (is3g) {
			return true;
		} else if (isWifi) {
			return true;
		}
		
		return false;
	}
}
