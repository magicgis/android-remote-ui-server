package com.example.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.handler.ClientHandler;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ClientHandler client = ClientHandler.getInstance(this);
 
		client.init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
