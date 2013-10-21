/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author tomique
 */
public class EventCMessage extends Message {

    private ArrayList<int[]> pointList;
    private int[] point;
    private String eventType;
    private String action;

    public void setPointList(ArrayList<int[]> pointList) {
        this.pointList = pointList;
        altered = true;
    }

    public void setPoint(int[] point) {
        this.point = point;
        altered = true;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
        altered = true;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<int[]> getPointList() {
        return pointList;
    }

    public int[] getPoint() {
        return point;
    }

    public String getEventType() {
        return eventType;
    }

    public String getAction() {
        return action;
    }

    @Override
    public void setXML() {
        throw new UnsupportedOperationException("Never used on server side.");
    }

    @Override
    public void decodeXML(String xml) {
        try {

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            byte[] byteArray = xml.getBytes("UTF-8");
            ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                // start tag
                if (event.isStartElement()) {

                    StartElement startElement = event.asStartElement();

                    switch (startElement.getName().getLocalPart()) {

                        case "event": {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();

                                switch (attribute.getName().toString()) {
                                    case "type":
                                        eventType = attribute.getValue();
                                        break;
                                    case "location":
                                        if (attribute.getValue().contains(",")) {    // pointList case
                                            pointList = new ArrayList<>();
                                            StringTokenizer st = new StringTokenizer(attribute.getValue(), ",");
                                            while (st.hasMoreTokens()) {
                                                StringTokenizer st2 = new StringTokenizer(st.nextToken());
                                                int[] pnt = {Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken())};
                                                pointList.add(pnt);
                                            }

                                        } else {                                    // point case
                                            // TODO je nutne duplikovat info o pointu take do listu???
                                            pointList = new ArrayList<>();
                                            StringTokenizer st = new StringTokenizer(attribute.getValue());
                                            point = new int[2];
                                            point[0] = Integer.parseInt(st.nextToken());
                                            point[1] = Integer.parseInt(st.nextToken());
                                            pointList.add(point);
                                        }
                                        break;
                                    case "action":
                                        action = attribute.getValue();
                                        break;
                                }
                            }
                        }
                    }
                }
                // close tag
                if (event.isEndElement()) {

                    EndElement endElement = event.asEndElement();

                    switch (endElement.getName().getLocalPart()) {
                        case "cln":
                            break;
                        case "event":
                            break;
                    }
                }
            }

        } catch (IOException | XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return EventCMessage.class.getName();
    }
}
