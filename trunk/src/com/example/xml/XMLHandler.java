package com.example.xml;

import com.example.handler.ClientHandler;
import com.example.messaging.Message;
import com.example.networking.ClientNetworkAgentSender;

public class XMLHandler {

	private static XMLHandler instance;
	private ClientNetworkAgentSender netSender;
	private ClientHandler clientHandler;

	private XMLHandler() {}

	public static XMLHandler getInstance() {
		if (instance == null) {
			instance = new XMLHandler();
			instance.netSender = ClientNetworkAgentSender.getInstance();
			instance.clientHandler = ClientHandler.getInstance();
		}
		return instance;
	}
 
	public void send(Message message) {

		// get XML message
		String xmlMessage = message.getXML();
		netSender.send(xmlMessage);
	}

	public void receive(String xmlMessage) {

		Message message = XMLToolkit.decodeXML(xmlMessage);
		clientHandler.receive(message);
	}
}