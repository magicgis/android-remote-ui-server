/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.gui.manager;

import cz.ctu.guiproject.server.events.AndroidEvent;
import cz.ctu.guiproject.server.events.ClickEvent;
import cz.ctu.guiproject.server.events.LongClickEvent;
import cz.ctu.guiproject.server.events.TouchEvent;
import cz.ctu.guiproject.server.gui.bitmap.BitmapMixin;
import cz.ctu.guiproject.server.gui.device.ClientDevice;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultButton;
import cz.ctu.guiproject.server.gui.entity.DefaultComboBox;
import cz.ctu.guiproject.server.gui.entity.DefaultFader;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioGroup;
import cz.ctu.guiproject.server.gui.entity.DefaultToggleButton;
import java.util.Arrays;
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
    private int[] startPos;

    public void eventOccured(AndroidEvent event) {
        int[] userPoint = event.getPoint();
        // get device, that caused an event
        ClientDevice device = deviceManager.getDeviceMapper().getDevice(event.getSessionId());
        // get intersected component on particular device
        Component comp = device.getClientLayout().getIComponent(userPoint[0], userPoint[1]);

        if (comp == null) {
            logger.log(Level.INFO, "No component intersected.");
            return;
        }

        event.setSource(comp);

        logger.log(Level.INFO, comp.getName() + " caused an event.");

        // iterate through all ClientDevices
        for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
            ClientDevice clientDevice = entry.getValue();
            if (event instanceof TouchEvent) {
                TouchEvent touchEvent = (TouchEvent) event;
                if (comp instanceof DefaultButton) {
                    if (touchEvent.getMask().equals("ACTION_MOVE")) {
                        DefaultButton button = (DefaultButton) comp;
                        if (!button.isPressed()) {
                            button.setPressed(true);
                            clientDevice.updateContext(button);
                        }
                    }
                } else if (comp instanceof DefaultFader) {
                    if (touchEvent.getMask().equals("ACTION_DOWN")) {
                        startPos = touchEvent.getPoint();
                    }
                }

            } else if (event instanceof ClickEvent) {
                if (comp instanceof DefaultRadioButton) {

                    DefaultRadioButton defaultRadio = (DefaultRadioButton) comp;
                    defaultRadio.setSelected(defaultRadio.isSelected() ? false : true);
//                    clientDevice.getClientLayout().updateLayout(defaultRadio);
                    clientDevice.updateContext(defaultRadio);

                } else if (comp instanceof DefaultComboBox) {

                    DefaultComboBox defaultCombo = (DefaultComboBox) comp;
                    if (defaultCombo.isSelected()) {
                        defaultCombo.setSelected(false);
                        // form action area of each drop-down list value
                        int minX = defaultCombo.getPosX();
                        int minY = defaultCombo.getPosY();
                        int maxX = minX + defaultCombo.getOuterWidth();
                        int maxY = minY + defaultCombo.getOuterHeight();

                        int[] point = event.getPoint();
                        for (int i = 0; i < defaultCombo.getValues().length; i++) {
                            // form actionArea
                            minY = maxY;
                            maxY += defaultCombo.getOuterHeight();
                            int[] aabb = {minX, minY, maxX, maxY};
                            if (BitmapMixin.intersects(point[0], point[1], aabb)) {
                                defaultCombo.setSelectedValue(defaultCombo.getValues()[i]);
                                break;
                            }
                        }
                    } else {
                        defaultCombo.setSelected(true);
                    }

//                    clientDevice.getClientLayout().updateLayout(defaultCombo);
                    clientDevice.updateContext(defaultCombo);

                } else if (comp instanceof DefaultToggleButton) {

                    DefaultToggleButton defaultToggle = (DefaultToggleButton) comp;
                    defaultToggle.setPressed(defaultToggle.isPressed() ? false : true);
//                    clientDevice.getClientLayout().updateLayout(defaultToggle);
                    clientDevice.updateContext(defaultToggle);

                } else if (comp instanceof DefaultButton) {
                    DefaultButton button = (DefaultButton) comp;
                    button.setPressed(button.isPressed() ? false : true);
                    clientDevice.updateContext(button);
                } else if (comp instanceof DefaultFader) {
                    int[] endPos = event.getPoint();
                    // difference in x axis
                    int path = endPos[0] - startPos[0];
                    boolean minus = path < 0 ? true : false;
                    // how many percent is added / subtracted
                    DefaultFader d = (DefaultFader) comp;
                    int caretPath = d.getOuterWidth() - (2 * d.getStopperWidth()) - d.getCaretWidth();
                    int percent = (int) ((100.0 * Math.abs(path)) / caretPath);
                    int res = d.getCaretPosition() + (minus ? -percent : percent);
                    res = Math.max(0, res);
                    res = Math.min(100, res);
                    d.setCaretPosition(res);
                    clientDevice.updateContext(d);
                } else if (comp instanceof DefaultRadioGroup) {
                    DefaultRadioGroup g = (DefaultRadioGroup) comp;
                    // identify clicked radio button
                    for (DefaultRadioButton r : g.getRadios()) {
                        int[] actionArea = r.getActionArea();
                        if (BitmapMixin.intersects(userPoint[0], userPoint[1], actionArea)) {
                            // decide, which component was intersected and adequately update component
                            r.setSelected(true);
                            g.setSelectedRadio(r);
                        } else {
                            r.setSelected(false);
                        }
                    }

                    clientDevice.updateContext(g);
                }
            } else if (event instanceof LongClickEvent) {
            }
        }
    }
}
