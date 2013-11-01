/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business.test;

import cz.ctu.guiproject.server.business.ServerBusinessAgent;
import cz.ctu.guiproject.server.messaging.AndroidMessage;
import cz.ctu.guiproject.server.observers.EventObserver;
import cz.ctu.guiproject.server.xml.ServerXMLAgent;
import cz.ctu.guiproject.server.xml.ServerXMLAgentImpl;
import cz.ctu.guiproject.server.xml.ServerXMLObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import messaging.GUIInitSMessage;
import messaging.InitCMessage;
import messaging.InitSMessage;

/**
 *
 * @author tomas.buk
 */
public class ServerBusinessAgentTest implements ServerBusinessAgent, ServerXMLObserver {
    
    private ServerXMLAgent xmlAgent;
    private static final Logger logger = Logger.getLogger(ServerBusinessAgentTest.class.getName());
    
    public ServerBusinessAgentTest() {
        xmlAgent = new ServerXMLAgentImpl();
        xmlAgent.registerObserver(this);
        init();
    }
    
    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(5000);
                xmlAgent.send(0, new InitSMessage());
                xmlAgent.send(1, new GUIInitSMessage());
                xmlAgent.broadcast(new InitCMessage());
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
    
    @Override
    public void update(AndroidMessage message) {
        System.out.println("Observed message: " + message);
    }
    
    public static void main(String[] args) {
        new ServerBusinessAgentTest();
    }

    @Override
    public void registerObserver(EventObserver o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeObserver(EventObserver o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyEventObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
