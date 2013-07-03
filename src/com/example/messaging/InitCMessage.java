package com.example.messaging;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.example.android_toolkit.AndroidDevice;
import com.example.xml.XMLToolkit;

public class InitCMessage extends Message{
	/*
	 * filled by client
	 */
	private AndroidDevice device;

	/*
	 * filled by server
	 */
	private String sessionId;

	/**
	 * Default constructor
	 */
	public InitCMessage() {

	}

	/*
	 * SETTERS
	 */

	/**
	 * 
	 * @param device
	 */
	public void setDevice(AndroidDevice device) {
		this.device = device;
		altered = true;
	}

	/**
	 * 
	 * @param sessionId
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/*
	 * GETTERS
	 */

	/**
	 * 
	 * @return
	 */
	public AndroidDevice getDevice() {
		return device;
	}

	/**
	 * 
	 * @return
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Converts message to XML message with given attribute values and sets its
	 * output as String "xmlRep" from parent class.
	 */
	@Override
	public void setXML() {

		if (!altered) {
			return;
		}

		XmlSerializer xmlSerializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();

		try {
 
			xmlSerializer.setOutput(writer);

			// TODO is encoding mentioned here right??
			// start DOCUMENT, set encoding and standalone flag
			xmlSerializer.startDocument("UTF-8", true);
			// open tag: <cln>
			xmlSerializer.startTag("", "cln");
			xmlSerializer.attribute("", "state", "connection_init");
			// open tag: <device>
			xmlSerializer.startTag("", "device");
			xmlSerializer.attribute("", "name", device.getModel());
 
			// open tag: <resolution>
			xmlSerializer.startTag("", "resolution");

			// tag: <width>
			XMLToolkit.writeXML(xmlSerializer, "width", device.getResolution()
					.getWidth() + "");

			// tag: <height>
			XMLToolkit.writeXML(xmlSerializer, "height", device.getResolution()
					.getHeight() + "");

			// close tag: </resolution>
			xmlSerializer.endTag("", "resolution");

			// close tag: </device>
			xmlSerializer.endTag("", "device");
			// close tag: </cln>
			xmlSerializer.endTag("", "cln");

			// end DOCUMENT
			xmlSerializer.endDocument();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		xmlRep = writer.toString();
		altered = false;
	}

	@Override
	public void decodeXML(String xml) {
		throw new RuntimeException("There is no need to use decodeXML on InitCMessage at client side!");
	}
}
