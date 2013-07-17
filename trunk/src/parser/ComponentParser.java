/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import main.ActionArea;
import main.Background;
import main.Component;
import main.Font;
import main.Text;

/**
 *
 * @author tomique
 */
public class ComponentParser {
    
    private static ComponentParser instance;
    private Component component;

    private ComponentParser() {
        init();
    }
    
    public static ComponentParser getInstance() {
        if(instance == null) {
            instance = new ComponentParser();
        }
        return instance;
    }

    private void init() {
        // TODO better to load component destination from config xml file
        String path = "src/xml/component.xml";
        setupComponent(path);
    }
    
    
    
    public Component getComponent() {
        return component;
    }

    private void setupComponent(String configFile) {

        try {
            // create XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // setup new eventReader
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            ActionArea actionArea = null;
            Background background = null;
            Text text = null;
            Font font = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                // start tag
                if (event.isStartElement()) {

                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().getLocalPart().equals("component")) {
                        component = new Component();

                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();

                            switch (attribute.getName().toString()) {
                                case "id":
                                    component.setId(attribute.getValue());
                                    break;
                                case "width":
                                    component.setWidth(Integer.parseInt(attribute.getValue()));
                                    break;
                                case "height":
                                    component.setHeight(Integer.parseInt(attribute.getValue()));
                                    break;
                            }

                        }
                        continue;
                    }

                    if (startElement.getName().getLocalPart().equals("action-area")) {
                        actionArea = new ActionArea();

                        // read attributes from this tag
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();

                            switch (attribute.getName().toString()) {
                                case "id":
                                    actionArea.setId(attribute.getValue());
                                    break;
                                case "width":
                                    actionArea.setWidth(Integer.parseInt(attribute.getValue()));
                                    break;
                                case "height":
                                    actionArea.setHeight(Integer.parseInt(attribute.getValue()));
                                    break;
                                case "posx":
                                    actionArea.setPosx(Integer.parseInt(attribute.getValue()));
                                    break;
                                case "posy":
                                    actionArea.setPosy(Integer.parseInt(attribute.getValue()));
                                    break;
                                case "color":
                                    actionArea.setColor(attribute.getValue());
                                    break;
                            }
                        }
                        
                        actionArea.setLimits();
                        
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals("background")) {

                            background = new Background();

                            Iterator<Attribute> attributes = event.asStartElement().getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                switch (attribute.getName().toString()) {
                                    case "style":
                                        background.setStyle(attribute.getValue());
                                        break;
                                    case "color":
                                        background.setColor(attribute.getValue());
                                        break;
                                }
                            }
                            continue;
                        }

                        if (event.isStartElement()) {
                            if (event.asStartElement().getName().getLocalPart().equals("user-src")) {
                                Iterator<Attribute> attributes = event.asStartElement().getAttributes();
                                while (attributes.hasNext()) {
                                    Attribute attribute = attributes.next();
                                    if (attribute.getName().toString().equals("src")) {
                                        background.setUserSrc(attribute.getValue());
                                    }
                                }
                                continue;
                            }
                        }
                    }

                    if (event.isStartElement()) {
//                        if (event.asStartElement().getName().getLocalPart().equals("content")) {
//                        }
                        if (event.asStartElement().getName().getLocalPart().equals("text")) {

                            text = new Text();

                            Iterator<Attribute> attributes = event.asStartElement().getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();

                                switch (attribute.getName().toString()) {
                                    case "value":
                                        text.setValue(attribute.getValue());
                                        break;
                                    case "posx":
                                        text.setPosx(Integer.parseInt(attribute.getValue()));
                                        break;
                                    case "posy":
                                        text.setPosy(Integer.parseInt(attribute.getValue()));
                                        break;
                                    case "color":
                                        text.setColor(attribute.getValue());
                                        break;
                                }
                            }
                            continue;
                        }

                        if (event.asStartElement().getName().getLocalPart().equals("font")) {

                            font = new Font();

                            Iterator<Attribute> attributes = event.asStartElement().getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();

                                switch (attribute.getName().toString()) {
                                    case "name":
                                        font.setName(attribute.getValue());
                                        break;
                                    case "style":
                                        font.setStyle(attribute.getValue());
                                        break;
                                    case "size":
                                        font.setSize(Integer.parseInt(attribute.getValue()));
                                        break;
                                }
                                font.setFont();
                            }
                        }
                    }
                }

                // end tag
                if (event.isEndElement()) {

                    switch (event.asEndElement().getName().getLocalPart()) {
                        case "background":
                            actionArea.setBackground(background);
                            break;
                        case "action-area":
                            component.addActionArea(actionArea);
                            break;
                        case "text":
                            actionArea.addText(text);
                            break;
                        case "font":
                            text.setFont(font);
                            break;
                    }
                }
            }

//            System.out.println(component);
            
//            BufferedImage img = SimpleRenderer.getRenderedComponent(component);
//            String source = Codec.encodeToString(img, "png");
//            img = Codec.decodeToImage(source);
//            SimpleViewer viewer = new SimpleViewer(img);

        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
    }
}
