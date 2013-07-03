package com.example.android_toolkit;

import android.content.Context;

import com.example.entity.Resolution;

public class AndroidDevice {

	private Context context;
	private Resolution resolution;
	private String model;
	private String manufacturer;

	// TODO informace o poskytnutych sluzbach (gyro, dotykovy displej, ...)

	public AndroidDevice(Context context) {
		this.context = context;
		initDevice();
	}

	private void initDevice() {
		// init model
		model = android.os.Build.MODEL;
		// init manufacturer
		manufacturer = android.os.Build.MANUFACTURER;
		// init resolution
		resolution = AndroidToolkit.getResolution(context);
	}

	/*
	 * GETTERS
	 */

	/**
	 * Returns the device manufacturer
	 * 
	 * @return the device manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Returns the model name
	 * 
	 * @return The model name
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Returns the resolution of the device
	 * 
	 * @return The resolution of the device
	 */
	public Resolution getResolution() {
		return resolution;
	}
}
