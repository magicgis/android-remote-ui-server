/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.sample;

import cz.ctu.guiproject.server.events.ClickEvent;
import cz.ctu.guiproject.server.events.LongClickEvent;
import cz.ctu.guiproject.server.events.TouchEvent;
import cz.ctu.guiproject.server.gui.entity.Component;
import cz.ctu.guiproject.server.gui.entity.DefaultButton;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioButton;
import cz.ctu.guiproject.server.gui.entity.DefaultRadioGroup;
import cz.ctu.guiproject.server.main.AndroidServer;
import cz.ctu.guiproject.server.main.AndroidServerImpl;
import cz.ctu.guiproject.server.observers.ClickObserver;
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
public class Test implements ClickObserver, TouchObserver, LongClickObserver {

    public Test() {
        init();
    }

    private void init() {
        AndroidServer server = new AndroidServerImpl(6789);
        server.registerClickObserver(this);
        server.registerTouchObserver(this);
        server.registerLongClickObserver(this);
    }

    public static void main(String[] args) {
        new Test();
    }

    @Override
    public void update(ClickEvent event) {
        try {
            Component comp = event.getSource();
            Robot robot = new Robot();

            if (comp instanceof DefaultButton) {
                DefaultButton b = (DefaultButton) comp;

                switch (b.getName()) {
                    case "button_prev":
                        robot.keyPress(KeyEvent.VK_LEFT);
                        System.out.println("prev");
                        break;
                    case "button_next":
                        robot.keyPress(KeyEvent.VK_RIGHT);
                        System.out.println("next");
                        break;
                }
            } else if (comp instanceof DefaultRadioGroup) {
                DefaultRadioGroup g = (DefaultRadioGroup) comp;

                for (DefaultRadioButton r : g.getRadios()) {
                    if (r.isSelected()) {
                        switch(r.getName()) {
                            case "radio_1":
                                robot.keyPress(KeyEvent.VK_1);
                                robot.keyPress(KeyEvent.VK_ENTER);
                                break;
                            case "radio_2": 
                                robot.keyPress(KeyEvent.VK_5);
                                robot.keyPress(KeyEvent.VK_ENTER);
                                break;
                            case "radio_3":
                                robot.keyPress(KeyEvent.VK_1);
                                robot.keyPress(KeyEvent.VK_5);
                                robot.keyPress(KeyEvent.VK_ENTER);
                                break;
                        }
                    }
                }
            }
        } catch (AWTException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(TouchEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LongClickEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
