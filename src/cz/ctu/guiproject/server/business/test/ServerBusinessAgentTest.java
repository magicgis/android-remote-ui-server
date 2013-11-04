/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.business.test;

import cz.ctu.guiproject.server.business.ServerBusinessAgent;
import cz.ctu.guiproject.server.messaging.AndroidMessage;
import cz.ctu.guiproject.server.messaging.AndroidMessageFactory;
import cz.ctu.guiproject.server.observers.EventObserver;
import cz.ctu.guiproject.server.xml.ServerXMLAgent;
import cz.ctu.guiproject.server.xml.ServerXMLAgentImpl;
import cz.ctu.guiproject.server.xml.ServerXMLObserver;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class ServerBusinessAgentTest implements ServerBusinessAgent, ServerXMLObserver {

    private ServerXMLAgent xmlAgent;
    private static final Logger logger = Logger.getLogger(ServerBusinessAgentTest.class.getName());

    @SuppressWarnings("LeakingThisInConstructor")
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
                String xml1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
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
                        + "</root>";
                String xml2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<dragEvent>\n"
                        + "    <points>\n"
                        + "        <coord>1</coord>\n"
                        + "        <coord>2</coord>\n"
                        + "        <coord>3</coord>\n"
                        + "        <coord>45</coord>\n"
                        + "        <coord>5</coord>\n"
                        + "    </points>\n"
                        + "</dragEvent>";
                String xml3 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
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
                xmlAgent.send(0, AndroidMessageFactory.createAndroidMessage(xml1));
                xmlAgent.send(1, AndroidMessageFactory.createAndroidMessage(xml2));
                xmlAgent.broadcast(AndroidMessageFactory.createAndroidMessage(xml3));
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
