package com.example.networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

import com.example.logging.Logger;

public class ClientNetworkAgentSender implements Runnable {

	private final Object lock;
	private String message;
	private static ClientNetworkAgentSender instance;

	private ClientNetworkAgentSender() {
		lock = new Object();
	}

	public static ClientNetworkAgentSender getInstance() {
		if (instance == null) {
			instance = new ClientNetworkAgentSender();
		}
		return instance;
	}

	public void send(String message) {
		// TODO More elegant solution is needed!
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		this.message = message;
		synchronized (lock) {
			lock.notify();
		}
	}

	@Override
	public void run() {

		boolean stop = false;
		// TODO preferably load from config xml
		String ip = "192.168.0.165";
		int port = 6788;

		while (!stop) {
			Socket socket;

			try {
				socket = new Socket(ip, port);

				ObjectOutputStream oos = new ObjectOutputStream(
						socket.getOutputStream());
 
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						Log.e(Logger.ERR,
								"InterruptedException occured in ClientNetworkAgentSender");
					}
				}
				 
				oos.writeObject(message);
				oos.flush();
				oos.close();
				socket.close();

			} catch (UnknownHostException e) {
				Log.e(Logger.ERR,
						"UnknownHostException occured in ClientNetworkAgentSender.");
			} catch (IOException e) {
				Log.e(Logger.ERR,
						"IOException occured in ClientNetworkAgentSender.");
			}
		}
	}
}
