/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.sample;

import cz.ctu.guiproject.server.events.ClickEvent;
import cz.ctu.guiproject.server.events.DragEvent;
import cz.ctu.guiproject.server.events.LongClickEvent;
import cz.ctu.guiproject.server.events.TouchEvent;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultButton;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioGroup;
import cz.ctu.guiproject.server.main.AndroidServer;
import cz.ctu.guiproject.server.main.AndroidServerImpl;
import cz.ctu.guiproject.server.observers.ClickObserver;
import cz.ctu.guiproject.server.observers.DragObserver;
import cz.ctu.guiproject.server.observers.LongClickObserver;
import cz.ctu.guiproject.server.observers.TouchObserver;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class Main implements TouchObserver, ClickObserver, DragObserver, LongClickObserver {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Main() {
        init();
    }

    private void init() {
        AndroidServer server = new AndroidServerImpl(6789);
        server.registerClickObserver(this);
        server.registerTouchObserver(this);
        server.registerDragObserver(this);
        server.registerLongClickObserver(this);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void update(ClickEvent event) {
        Component source = event.getSource();
        if (source != null) {
            if (source instanceof DefaultRadioButton) {
                DefaultRadioButton radio = (DefaultRadioButton) source;
                logger.log(Level.INFO, "Source component: " + source.getClass().getName() + ": " + radio.isSelected());
            } else if (source instanceof DefaultButton) {
                try {
                    DefaultButton button = (DefaultButton) source;

                    Robot robot = new Robot();
                    if (button.getName().equals("button_next")) {
                        robot.keyPress(KeyEvent.VK_RIGHT);
                    } else if (button.getName().equals("button_prev")) {
                        robot.keyPress(KeyEvent.VK_LEFT);
                    }

                    logger.log(Level.INFO, "Source component: " + source.getClass().getName() + ": " + button.getLabel());
                } catch (AWTException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (source instanceof DefaultRadioGroup) {
                try {
                    DefaultRadioGroup group = (DefaultRadioGroup) source;
                    
                    Robot robot = new Robot();
                
                    

                    for (DefaultRadioButton radio : group.getRadios()) {
                        if (radio.isSelected()) {
                            switch (radio.getName()) {
                                case "g_radio_1":
                                    robot.keyPress(KeyEvent.VK_NUMPAD1);
                                    robot.keyPress(KeyEvent.VK_ENTER);
                                    break;
                                case "g_radio_5":
                                    robot.keyPress(KeyEvent.VK_NUMPAD5);
                                    robot.keyPress(KeyEvent.VK_ENTER);
                                    break;
                                case "g_radio_13":
                                    robot.keyPress(KeyEvent.VK_NUMPAD1);
                                    robot.keyPress(KeyEvent.VK_NUMPAD3);
                                    robot.keyPress(KeyEvent.VK_ENTER);
                                    break;
                            }
                        }
                    }
                } catch (AWTException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        logger.log(Level.INFO, "ClickEvent occured: " + event.getSessionId());
    }

    @Override
    public void update(TouchEvent event) {
        logger.log(Level.INFO, "TouchEvent occured: " + event.getSessionId());
    }

    @Override
    public void update(DragEvent event) {
        logger.log(Level.INFO, "DragEvent occured: " + event.getSessionId());
    }

    @Override
    public void update(LongClickEvent event) {
        logger.log(Level.INFO, "LongClickEvent occured: " + event.getSessionId());
    }
}
