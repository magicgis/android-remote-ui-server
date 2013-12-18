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
import cz.ctu.guiproject.server.gui.device.StateLayoutManager;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultButton;
import cz.ctu.guiproject.server.gui.entity.DefaultComboBox;
import cz.ctu.guiproject.server.gui.entity.DefaultFader;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioGroup;
import cz.ctu.guiproject.server.gui.entity.DefaultToggleButton;
import java.util.Arrays;
import java.util.Iterator;
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
    private StateLayoutManager stateLayoutManager;
    private int[] startPos;

    private EventManager() {
        deviceManager = DeviceManager.getInstance();
        stateLayoutManager = StateLayoutManager.getInstance();
    }

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void eventOccured(AndroidEvent event) {
        int[] userPoint = event.getPoint();
        // get device, that caused an event
        ClientDevice device = deviceManager.getDeviceMapper().getDevice(event.getSessionId());
        // get intersected component on particular device
        Component metricComp = device.getClientLayout().getIComponent(userPoint[0], userPoint[1]);

        if (metricComp == null) {
            logger.log(Level.INFO, "No component intersected.");
            return;
        }
        // status component
        Component statusComp = null;

        // convert metric component to status component
        for (Component refStatusComp : stateLayoutManager.getStateLayout().getComponents()) {
            if (refStatusComp.equals(metricComp)) {
                statusComp = refStatusComp;
                break;
            }
        }

        if (statusComp == null) {
            throw new RuntimeException("Impossible state, always should equal to some component from layout!");
        }

        event.setSource(statusComp);

        logger.log(Level.INFO, statusComp.getName() + " caused an event.");

//        // iterate through all ClientDevices and update appropriately
//        for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
//            ClientDevice clientDevice = entry.getValue();
//            // map devices metric layout to appropriate
////            Component metricComp = clientDevice.getClientLayout().getMetricComponenent(deviceComp);

////////////////////////////////////////////////////////////////////////////////        
        if (event instanceof TouchEvent) {
            TouchEvent touchEvent = (TouchEvent) event;
            if (statusComp instanceof DefaultButton) {
                if (touchEvent.getMask().equals("ACTION_MOVE")) {
                    DefaultButton stateB = (DefaultButton) statusComp;
                    if (!stateB.isPressed()) {
                        stateB.setPressed(true);
                        for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
                            ClientDevice clientDevice = entry.getValue();
                            metricComp = clientDevice.getClientLayout().getMetricComponent(metricComp);
                            clientDevice.updateContext(statusComp, metricComp);
                        }
                    }
                }
            } else if (statusComp instanceof DefaultFader) {
                if (touchEvent.getMask().equals("ACTION_DOWN")) {
                    startPos = touchEvent.getPoint();
                }
            }

        } else if (event instanceof ClickEvent) {
            if (statusComp instanceof DefaultRadioButton) {

                DefaultRadioButton stateR = (DefaultRadioButton) statusComp;
                stateR.setSelected(stateR.isSelected() ? false : true);

                for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
                    ClientDevice clientDevice = entry.getValue();
                    metricComp = clientDevice.getClientLayout().getMetricComponent(metricComp);
                    clientDevice.updateContext(statusComp, metricComp);
                }

            } else if (statusComp instanceof DefaultComboBox) {

                DefaultComboBox stateC = (DefaultComboBox) statusComp;
                DefaultComboBox metricC = (DefaultComboBox) metricComp;

                if (stateC.isSelected()) {
                    stateC.setSelected(false);
                    // form action area of each drop-down list value
                    int minX = metricC.getPosX();
                    int minY = metricC.getPosY();
                    int maxX = minX + metricC.getOuterWidth();
                    int maxY = minY + metricC.getOuterHeight();

                    int[] point = event.getPoint();
                    System.out.println(Arrays.toString(point));
                    for (int i = 0; i < stateC.getValues().length; i++) {
                        // form actionArea
                        minY = maxY;
                        maxY += metricC.getOuterHeight();
                        int[] aabb = {minX, minY, maxX, maxY};
                        System.out.println(Arrays.toString(aabb));
                        if (BitmapMixin.intersects(point[0], point[1], aabb)) {
                            stateC.setSelectedValue(stateC.getValues()[i]);
                            break;
                        }
                    }
                } else {
                    stateC.setSelected(true);
                }

                for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
                    ClientDevice clientDevice = entry.getValue();
                    metricComp = clientDevice.getClientLayout().getMetricComponent(metricComp);
                    clientDevice.updateContext(statusComp, metricComp);
                }

            } else if (statusComp instanceof DefaultToggleButton) {
                DefaultToggleButton stateT = (DefaultToggleButton) statusComp;
                stateT.setPressed(stateT.isPressed() ? false : true);

                for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
                    ClientDevice clientDevice = entry.getValue();
                    metricComp = clientDevice.getClientLayout().getMetricComponent(metricComp);
                    clientDevice.updateContext(statusComp, metricComp);
                }

            } else if (statusComp instanceof DefaultButton) {
                DefaultButton stateB = (DefaultButton) statusComp;
                stateB.setPressed(stateB.isPressed() ? false : true);

                for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
                    ClientDevice clientDevice = entry.getValue();
                    metricComp = clientDevice.getClientLayout().getMetricComponent(metricComp);
                    clientDevice.updateContext(statusComp, metricComp);
                }

            } else if (statusComp instanceof DefaultFader) {
                // TODO convert to particular metric system
                int[] endPos = event.getPoint();
                // difference in x axis
                int path = endPos[0] - startPos[0];
                boolean minus = path < 0 ? true : false;
                // how many percent is added / subtracted
                DefaultFader d = (DefaultFader) statusComp;
                DefaultFader posD = (DefaultFader) metricComp;
                int caretPath = posD.getOuterWidth() - (2 * posD.getStopperWidth()) - posD.getCaretWidth();
                int percent = (int) ((100.0 * Math.abs(path)) / caretPath);
                int res = d.getCaretPosition() + (minus ? -percent : percent);
                res = Math.max(0, res);
                res = Math.min(100, res);
                d.setCaretPosition(res);
//                clientDevice.updateContext(statusComp, metricComp);
                for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
                    ClientDevice clientDevice = entry.getValue();
                    metricComp = clientDevice.getClientLayout().getMetricComponent(metricComp);
                    clientDevice.updateContext(statusComp, metricComp);
                }
            } else if (statusComp instanceof DefaultRadioGroup) {
                DefaultRadioGroup statusG = (DefaultRadioGroup) statusComp;
                DefaultRadioGroup metricG = (DefaultRadioGroup) metricComp;

                Iterator<DefaultRadioButton> statusIt = statusG.getRadios().iterator();
                Iterator<DefaultRadioButton> metricIt = metricG.getRadios().iterator();
                // identify clicked radio button
                while (statusIt.hasNext()) {
                    DefaultRadioButton statusR = statusIt.next();
                    DefaultRadioButton metricR = metricIt.next();
                    int[] actionArea = metricR.getActionArea();
                    if (BitmapMixin.intersects(userPoint[0], userPoint[1], actionArea)) {
                        statusR.setSelected(true);
                        statusG.setSelectedRadio(statusR);
                    } else {
                        statusR.setSelected(false);
                    }
                }

                for (Map.Entry<String, ClientDevice> entry : deviceManager.getDeviceMapper().getDevices().entrySet()) {
                    ClientDevice clientDevice = entry.getValue();
                    metricComp = clientDevice.getClientLayout().getMetricComponent(metricComp);
                    clientDevice.updateContext(statusComp, metricComp);
                }
            }
        } else if (event instanceof LongClickEvent) {
        }
//        }
    }
}