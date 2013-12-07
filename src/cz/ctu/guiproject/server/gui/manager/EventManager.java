/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.manager;

import cz.ctu.guiproject.server.events.AndroidEvent;
import cz.ctu.guiproject.server.events.ClickEvent;
import cz.ctu.guiproject.server.events.LongClickEvent;
import cz.ctu.guiproject.server.events.TouchEvent;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultComboBox;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultToggleButton;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class EventManager {

    private DeviceManager deviceManager;
    private static EventManager instance;
    private static final Logger logger = Logger.getLogger(EventManager.class.getName());

    private EventManager() {
        deviceManager = DeviceManager.getInstance();
    }

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void eventOccured(AndroidEvent event) {
        int[] userPoint = event.getPoint();
        ClientDevice device = deviceManager.getDeviceMapper().getDevice(event.getSessionId());
        Component comp = device.getClientLayout().getIComponent(userPoint[0], userPoint[1]);

        if (comp == null) {
            return;
        }
        logger.log(Level.INFO, comp.getName() + " caused an event.");

        // iterate through all ClientDevices
        for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
            ClientDevice clientDevice = entry.getValue();
            if (event instanceof TouchEvent) {
            } else if (event instanceof ClickEvent) {
                if (comp instanceof DefaultRadioButton) {

                    DefaultRadioButton defaultRadio = (DefaultRadioButton) comp;
                    defaultRadio.setSelected(defaultRadio.isSelected() ? false : true);
                    clientDevice.getClientLayout().updateLayout(defaultRadio);
                    clientDevice.updateContext();

                } else if (comp instanceof DefaultComboBox) {
                } else if (comp instanceof DefaultToggleButton) {
                }
            } else if (event instanceof LongClickEvent) {
            }
        }
    }
}
