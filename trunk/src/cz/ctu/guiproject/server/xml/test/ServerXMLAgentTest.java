/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.xml.test;

import cz.ctu.guiproject.server.messaging.AndroidMessage;
import cz.ctu.guiproject.server.messaging.AndroidMessageFactory;
import cz.ctu.guiproject.server.xml.ServerXMLAgent;
import cz.ctu.guiproject.server.xml.ServerXMLAgentImpl;
import cz.ctu.guiproject.server.xml.ServerXMLObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class ServerXMLAgentTest implements ServerXMLObserver {

    private static final Logger logger = Logger.getLogger(ServerXMLAgentTest.class.getName());

    public ServerXMLAgentTest() {
        init();
    }

    private void init() {
        final ServerXMLAgent serverXML = new ServerXMLAgentImpl();
        serverXML.registerObserver(this);
        sleep(15000);
        new Thread(new Runnable() {
            @Override
            public void run() {

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    try {
                        int choice = Integer.parseInt(br.readLine());
                        String xml;
                        switch (choice) {
                            case 0:
                                xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                        + "<clickEvent>\n"
                                        + "    <points>\n"
                                        + "        <coord>1</coord>\n"
                                        + "        <coord>2</coord>\n"
                                        + "        <coord>3</coord>\n"
                                        + "        <coord>4</coord>\n"
                                        + "        <coord>57</coord>\n"
                                        + "        <coord>8</coord>\n"
                                        + "        <coord>9</coord>\n"
                                        + "        <coord>0</coord>\n"
                                        + "    </points>\n"
                                        + "</clickEvent>\n"
                                        + "</root>\n";
                                serverXML.send(choice, AndroidMessageFactory.createAndroidMessage(xml));
                            case 1:
                                xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                                        + "<touchEvent>\n"
                                        + "    <points>\n"
                                        + "        <coord>1</coord>\n"
                                        + "        <coord>2</coord>\n"
                                        + "        <coord>3</coord>\n"
                                        + "        <coord>4</coord>\n"
                                        + "        <coord>5</coord>\n"
                                        + "        <coord>1</coord>\n"
                                        + "    </points>\n"
                                        + "    <mask>USER_UP</mask>\n"
                                        + "</touchEvent>";
                                serverXML.broadcast(AndroidMessageFactory.createAndroidMessage(xml));
                                break;
                        }

                    } catch (IOException ex) {
                        break;
                    }
                }
            }
        }).start();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ServerXMLAgentTest();
    }

    @Override
    public void update(AndroidMessage message) {
        logger.log(Level.INFO, "New message received: " + message);
    }
}
