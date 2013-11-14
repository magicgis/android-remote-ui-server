/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class IdMapper {

    // singleton instance of NetworkSessionIdMapper
    private static IdMapper instance;
    private Map<String, Integer> sessionNetworkMap;
    private static final Logger logger = Logger.getLogger(IdMapper.class.getName());

    /**
     * Private constructor of NetworkSessionIdMapper singleton
     */
    private IdMapper() {
        sessionNetworkMap = new HashMap<>();
    }

    /**
     * Returns the only existing instance of IdMapper
     *
     * @return the only existing instance of IdMapper
     */
    public static IdMapper getInstance() {
        if (instance == null) {
            logger.log(Level.INFO, "private constructor called!");
            instance = new IdMapper();
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
        throw new RuntimeException("Requested sessionId is not contained in the map: " + sessionId + " (" + sessionNetworkMap.size() + ")");
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
