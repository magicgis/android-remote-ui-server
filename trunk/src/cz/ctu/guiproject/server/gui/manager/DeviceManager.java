/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.manager;

import cz.ctu.guiproject.server.helper.DeviceMapper;
import cz.ctu.guiproject.server.helper.IdMapper;

/**
 *
 * @author tomas.buk
 */
public class DeviceManager {

    private static DeviceManager instance;
    private DeviceMapper deviceMapper;
    // mapping from networkId to sessionId
    private IdMapper idMapper;

    /**
     * Private costructor required by the singleton design pattern
     */
    private DeviceManager() {
        idMapper = IdMapper.getInstance();
        deviceMapper = DeviceMapper.getInstance();
    }

    public static DeviceManager getInstance() {
        if (instance == null) {
            instance = new DeviceManager();
        }
        return instance;
    }

    /**
     * Used to access the sessionId - networkId mapping
     *
     * @return sessionId - networkId mapper
     */
    public IdMapper getIdMapper() {
        return idMapper;
    }
    
    public DeviceMapper getDeviceMapper() {
        return deviceMapper;
    }
}
