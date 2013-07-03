package com.example.messaging;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

import com.example.logging.Logger;
import com.example.networking.Session;

/**
 * Received from server side, used to confirm the connection request.
 * 
 * @author tomique
 * 
 */
public class InitSMessage extends Message {

	private Session session;
	private String serverAnswer;

	public InitSMessage() {

	}

	/*
	 * SETTERS
	 */

	/**
	 * Sets the current session
	 * 
	 * @param session
	 *            Session, that need to be set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Sets the code answer of the server (e.g. 200 - OK)
	 * 
	 * @param serverAnswer
	 *            Code answer of the server
	 */
	public void setServerAnswer(String serverAnswer) {
		this.serverAnswer = serverAnswer;
	}

	/*
	 * GETTERS
	 */

	/**
	 * Returns the received session
	 * 
	 * @return The received session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Returns the coded server answer
	 * 
	 * @return The coded server answer
	 */
	public String getServerAnswer() {
		return serverAnswer;
	}

	@Override
	public void setXML() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"setXML not supported in InitSMessage on the client side!");
	}

	@Override
	public void decodeXML(String xml) {

		// parse given xml file and fill response with parsed data
		try {

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();

			xpp.setInput(new StringReader(xml));
			int eventType = xpp.getEventType();
			String currentTag = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					// TODO possible to remove
					break;
				case XmlPullParser.END_DOCUMENT:
					// TODO possible to remove
					break;
				case XmlPullParser.START_TAG:

					currentTag = xpp.getName();

					if (currentTag.equals("response")) {
						if (xpp.getAttributeName(0).equals("state")) {
							setServerAnswer(xpp.getAttributeValue(0));
						}
						break;
					}

					if (currentTag.equals("session")) {
						if (xpp.getAttributeName(0).equals("id")) {
							setSession(new Session(xpp.getAttributeValue(0)));
						}
						break;
					}

					break;

				case XmlPullParser.END_TAG:

					break;
				case XmlPullParser.TEXT:
					break;
				}

				eventType = xpp.next();
			}

		} catch (XmlPullParserException e) {
			Log.e(Logger.ERR, "XmlPullParserException in XMLHandler");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(Logger.ERR, "IOException in XMLHandler");
			e.printStackTrace();
		}
	}
}
