/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.helper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tomas.buk
 */
public class SessionNetworkIdMapper {

    // singleton instance of NetworkSessionIdMapper
    private static SessionNetworkIdMapper instance;
    private Map<String, Integer> sessionNetworkMap;

    /**
     * Private constructor of NetworkSessionIdMapper singleton
     */
    private SessionNetworkIdMapper() {
        sessionNetworkMap = new HashMap<>();
    }

    /**
     * Returns the only existing instance of NetworkSessionIdMapper
     *
     * @return the only existing instance of NetworkSessionIdMapper
     */
    public static SessionNetworkIdMapper getInstance() {
        if (instance == null) {
            instance = new SessionNetworkIdMapper();
        }
        return instance;
    }

    /**
     * Returns appropriate networkId connected with particular sessionId
     *
     * @param sessionId
     * @return
     */
    public int getNetworkId(String sessionId) {
        if (sessionNetworkMap.containsKey(sessionId)) {
            return sessionNetworkMap.get(sessionId);
        }
        throw new RuntimeException("Requested sessionId is not contained in the map!");
    }

    /**
     * Assigns particular sessionId with appropriate networkId
     *
     * @param sessionId
     * @param networkId
     */
    public void assign(String sessionId, int networkId) {
        sessionNetworkMap.put(sessionId, networkId);
    }

    /**
     * Returns true, if given sessionId corresponds with particular networkId
     *
     * @param sessionId
     * @param networkId
     * @return
     */
    public boolean isAssigned(String sessionId, int networkId) {
        if (!sessionNetworkMap.containsKey(sessionId)) {
            return false;
        }
        int networkId_ = sessionNetworkMap.get(sessionId);
        return (networkId_ == networkId) ? true : false;
    }
}
