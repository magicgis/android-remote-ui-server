package com.example.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

import com.example.logging.Logger;
import com.example.xml.XMLHandler;

public class ClientNetworkAgentReceiver implements Runnable {
	
	private static ClientNetworkAgentReceiver instance;
	private XMLHandler xmlHandler;
	
	private ClientNetworkAgentReceiver() {
	}
	
	public static ClientNetworkAgentReceiver getInstance() {
		if(instance == null) {
			instance = new ClientNetworkAgentReceiver();
			instance.xmlHandler = XMLHandler.getInstance();
		}
		return instance;
	}

	@Override
	public void run() {

		boolean stop = false;
		// TODO nacitat z konfiguracniho XML
		String ip = "192.168.0.165";
		int port = 6789;

		while (!stop) {

			Socket clientSocket;
			String message;

			try {

				clientSocket = new Socket(ip, port);

				ObjectInputStream ois = new ObjectInputStream(
						clientSocket.getInputStream());

				// receive the message from server
				message = (String) ois.readObject();
				xmlHandler.receive(message);
				
				ois.close();
				clientSocket.close();
 
			} catch (UnknownHostException e) {
				Log.e(Logger.ERR, "UnknownHostException in ClientNetworkAgentReceiver occured!");
			} catch (ClassNotFoundException e) {
				Log.e(Logger.ERR, "ClassNotFoundException in ClientNetworkAgentReceiver occured!");
			} catch (IOException e) {
				Log.e(Logger.ERR, "IOException in ClientNetworkAgentReceiver occured!");
			}
		}
	}
}
