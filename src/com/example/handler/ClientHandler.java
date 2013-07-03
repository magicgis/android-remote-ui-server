package com.example.handler;

import android.content.Context;
import android.util.Log;

import com.example.android_toolkit.AndroidDevice;
import com.example.logging.Logger;
import com.example.messaging.InitCMessage;
import com.example.messaging.InitSMessage;
import com.example.messaging.Message;
import com.example.networking.ClientNetworkAgentReceiver;
import com.example.networking.ClientNetworkAgentSender;
import com.example.networking.Session;
import com.example.xml.XMLHandler;

public class ClientHandler {

	private Context context;
	private XMLHandler xmlHandler;
	private Session session;
	private static ClientHandler instance;

	private ClientNetworkAgentReceiver agentReceiver;
	private ClientNetworkAgentSender agentSender;

	private ClientHandler(Context context) {
		this.context = context;
	}

	public static ClientHandler getInstance(Context context) {
		if (instance == null) {
			instance = new ClientHandler(context);
			instance.xmlHandler = XMLHandler.getInstance();
			instance.agentSender = ClientNetworkAgentSender.getInstance();
			instance.agentReceiver = ClientNetworkAgentReceiver.getInstance();
		}
		return instance;
	}
	
	public static ClientHandler getInstance() {
		if(instance == null) {
			Log.e(Logger.ERR, "Call to second constructor in ClientHandler is needed!");
			throw new RuntimeException("Call to second constructor in ClientHandler is needed!");
		}
		return instance;
	}
	
	public void receive(Message message) {
		
		// receive response from server regarding the connection init
		if(message instanceof InitSMessage) {
			InitSMessage response = (InitSMessage) message;
			  
			// check, if the answer code is 200 (OK)
			if (!response.getServerAnswer().equals("200")) {
				Log.e(Logger.ERR, "Server refused to establish connection!");
				throw new RuntimeException(
						"Server refused to establish connection!");
			}
   
			// setup session with received Session id
			// any future request and response message for server contains received
			// session information
			session = response.getSession();
			Log.i(Logger.DEBUG, "Client is connected!");
			// from now on, the connection is set!
			return;
		}
		
	}

	
	public void init() {

		// init daemons, that take care of receiving and sending messages to
		// server
		initDaemons();
		
		if(agentSender == null) {
			Log.e(Logger.ERR, "agentSender nesmi byt null!");
		}
		
		// establish the connection with server
		AndroidDevice device = new AndroidDevice(context);
		
		InitCMessage initMessage = new InitCMessage();
		initMessage.setDevice(device);
		
		xmlHandler.send(initMessage);
		
	}

	private void initDaemons() {

		Thread t1 = new Thread(agentReceiver);
		t1.setDaemon(true);

		Thread t2 = new Thread(agentSender);
		t2.setDaemon(true);

		// start daemon threads
		t1.start();
		t2.start();
		
		// TODO replace with something, that waits, until both threads t1 and t2 are ready to perform
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
