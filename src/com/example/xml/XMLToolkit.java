package com.example.xml;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.util.Log;

import com.example.logging.Logger;
import com.example.messaging.InitSMessage;
import com.example.messaging.Message;

public class XMLToolkit {

	/**
	 * Writes pair tag into XmlSerializer in form <tagName>text</tagName>
	 * 
	 * @param xml
	 *            XmlSerializer
	 * @param tagName
	 *            Name of the tag
	 * @param text
	 *            Text value
	 */
	public static void writeXML(XmlSerializer xml, String tagName, String text) {

		try {

			xml.startTag("", tagName);
			xml.text(text);
			// close tag: </height>
			xml.endTag("", tagName);

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
	}
	
	public static Message decodeXML(String xml) {

		Message message = null;

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

					if (currentTag.equals("svr")) {
						if (xpp.getAttributeName(0).equals("state")
								&& xpp.getAttributeValue(0).equals(
										"connection_init")) {
							message = new InitSMessage();
							message.decodeXML(xml);
						}
						break;
					}
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
		return message;
	}
}
