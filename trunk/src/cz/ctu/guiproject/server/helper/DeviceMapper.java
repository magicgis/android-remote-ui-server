/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.helper;

import cz.ctu.guiproject.server.gui.device.ClientDevice;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClientId - clientDevice mapping
 *
 * @author tomas.buk
 */
public class DeviceMapper {

    private static DeviceMapper instance;
    private Map<String, ClientDevice> map;
    private static final Logger logger = Logger.getLogger(DeviceMapper.class.getName());

    private DeviceMapper() {
        map = new HashMap<>();
    }

    public static DeviceMapper getInstance() {
        if (instance == null) {
            instance = new DeviceMapper();
        }
        return instance;
    }

    /**
     * Adds new device to the existing devices list
     *
     * @param newDevice Device to be added to the mapper
     */
    public void addDevice(ClientDevice newDevice) {
        map.put(newDevice.getId(), newDevice);
        logger.log(Level.INFO, "New device has been added: " + newDevice.getId());
    }

    /**
     * Returns ClientDevice based on given unique sessionId
     *
     * @param sessionId
     * @return
     */
    public ClientDevice getDevice(String sessionId) {
        return map.get(sessionId);
    }

    /**
     * Returns map of all existing devices
     *
     * @return
     */
    public Map<String, ClientDevice> getDevices() {
        return map;
    }
}
