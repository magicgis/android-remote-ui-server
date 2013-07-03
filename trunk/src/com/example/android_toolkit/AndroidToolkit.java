package com.example.android_toolkit;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.example.entity.Resolution;

public class AndroidToolkit {

	/**
	 * Returns the resolution of the android device
	 * 
	 * @param context
	 *            Main context
	 * @return Resolution fo the android device
	 */
	public static Resolution getResolution(Context context) {

		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		Resolution res = new Resolution(size.x, size.y);
		return res;
	}
}
