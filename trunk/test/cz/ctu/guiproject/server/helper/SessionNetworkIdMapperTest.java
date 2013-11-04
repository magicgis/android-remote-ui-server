/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.helper;

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
public class SessionNetworkIdMapperTest {

    public SessionNetworkIdMapperTest() {
    }

    /**
     * Test of getInstance method, of class SessionNetworkIdMapper.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        SessionNetworkIdMapper result = SessionNetworkIdMapper.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getNetworkId method, of class SessionNetworkIdMapper.
     */
    @Test
    public void testGetNetworkId() {
        System.out.println("getNetworkId");
        String sessionId1 = "session01";
        String sessionId2 = "session666";

        int networkId1 = 55;
        int networkId2 = 101;
        SessionNetworkIdMapper mapper = SessionNetworkIdMapper.getInstance();
        mapper.assign(sessionId1, networkId1);
        mapper.assign(sessionId2, networkId2);
        int expResult1 = networkId1;
        int expResult2 = networkId2;
        int result1 = mapper.getNetworkId(sessionId1);
        int result2 = mapper.getNetworkId(sessionId2);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    @Test(expected = RuntimeException.class)
    public void testGetNetworkIdException() {
        System.out.println("getNetworkId [exception]");
        SessionNetworkIdMapper mapper = SessionNetworkIdMapper.getInstance();
        String sessionId3 = "sessionNonExisting";
        mapper.getNetworkId(sessionId3);
    }

    /**
     * Test of assign method, of class SessionNetworkIdMapper.
     */
    @Test
    public void testAssign() {
        System.out.println("assign");
        String sessionId1 = "session1";
        String sessionId2 = "session5";
        int networkId1 = 0;
        int networkId2 = 809;
        SessionNetworkIdMapper mapper = SessionNetworkIdMapper.getInstance();
        mapper.assign(sessionId1, networkId1);
        mapper.assign(sessionId2, networkId2);
        int expResult1 = networkId1;
        int expResult2 = networkId2;
        int result1 = mapper.getNetworkId(sessionId1);
        int result2 = mapper.getNetworkId(sessionId2);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of isAssigned method, of class SessionNetworkIdMapper.
     */
    @Test
    public void testIsAssigned() {
        System.out.println("isAssigned");
        String sessionId1 = "session1";
        String sessionId2 = "session1";
        int networkId1 = 0;
        int networkId2 = 1;
        SessionNetworkIdMapper instance = SessionNetworkIdMapper.getInstance();
        instance.assign(sessionId1, networkId1);
        boolean expResult1 = true;
        boolean expResult2 = false;
        boolean result1 = instance.isAssigned(sessionId1, networkId1);
        boolean result2 = instance.isAssigned(sessionId2, networkId2);
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }
}