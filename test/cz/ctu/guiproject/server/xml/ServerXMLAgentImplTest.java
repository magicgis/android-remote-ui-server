/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.xml;

import cz.ctu.guiproject.server.helper.IdParser;
import cz.ctu.guiproject.server.messaging.AndroidMessage;
import cz.ctu.guiproject.server.messaging.AndroidMessageFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomas.buk
 */
public class ServerXMLAgentImplTest {

    public ServerXMLAgentImplTest() {
    }

    /**
     * Test of update method, of class ServerXMLAgentImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String message = "[353]<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<clickEvent>\n"
                + "    <sessionId>AndroidDeviceSHA9990001</sessionId>\n"
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
                + "</clickEvent>";
        update(message);
    }

    public void update(String message) {
        // parse network and session ids
        String sessionId = IdParser.getSessionId(message);
        int networkId = IdParser.getNetworkId(message);

        // trim incomming xml message, so that is does not contain networkId information
        message = message.substring(message.indexOf("]") + 1);
        System.out.println("sessionId: " + sessionId + "\nnetworkId: " + networkId + "\nxml:\n" + message);
    }
//    /**
//     * Test of registerObserver method, of class ServerXMLAgentImpl.
//     */
//    @Test
//    public void testRegisterObserver() {
//        System.out.println("registerObserver");
//        ServerXMLObserver o = null;
//        ServerXMLAgentImpl instance = new ServerXMLAgentImpl();
//        instance.registerObserver(o);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeObserver method, of class ServerXMLAgentImpl.
//     */
//    @Test
//    public void testRemoveObserver() {
//        System.out.println("removeObserver");
//        ServerXMLObserver o = null;
//        ServerXMLAgentImpl instance = new ServerXMLAgentImpl();
//        instance.removeObserver(o);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of notifyObservers method, of class ServerXMLAgentImpl.
//     */
//    @Test
//    public void testNotifyObservers() {
//        System.out.println("notifyObservers");
//        ServerXMLAgentImpl instance = new ServerXMLAgentImpl();
//        instance.notifyObservers();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of broadcast method, of class ServerXMLAgentImpl.
//     */
//    @Test
//    public void testBroadcast() {
//        System.out.println("broadcast");
//        AndroidMessage message = null;
//        ServerXMLAgentImpl instance = new ServerXMLAgentImpl();
//        instance.broadcast(message);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of send method, of class ServerXMLAgentImpl.
//     */
//    @Test
//    public void testSend() {
//        System.out.println("send");
//        int networkId = 0;
//        AndroidMessage message = null;
//        ServerXMLAgentImpl instance = new ServerXMLAgentImpl();
//        instance.send(networkId, message);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}