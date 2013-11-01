/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.xml.test;

import cz.ctu.guiproject.server.messaging.AndroidMessage;
import cz.ctu.guiproject.server.xml.ServerXMLAgent;
import cz.ctu.guiproject.server.xml.ServerXMLAgentImpl;
import cz.ctu.guiproject.server.xml.ServerXMLObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import messaging.InitCMessage;
import messaging.InitSMessage;

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
                        switch (choice) {
                            case 0:
                                serverXML.send(choice, new InitCMessage());
                            case 1:
                                serverXML.broadcast(new InitSMessage());
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
