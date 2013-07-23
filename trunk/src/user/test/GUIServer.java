/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user.test;

import android_toolkit.AndroidDevice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tomique
 */
public class GUIServer {

    private static GUIServer instance;
    private JLabel statusLabel;
    private JLabel deviceLabel;
    private JLabel event1Label;
    private JLabel event2Label;
    private JLabel action1Label;
    private JLabel action2Label;
    private Status status;

    public enum Status {

        CONNECTED, DISCONNECTED;
    }

    private GUIServer() {
        init();
    }

    public static GUIServer getInstance() {
        if (instance == null) {
            instance = new GUIServer();
        }
        return instance;
    }

    private void init() {
        JFrame frame = new JFrame("User test");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 2));
        northPanel.setPreferredSize(new Dimension(300, 50));
        northPanel.add(new JLabel("Server status:"));

        statusLabel = new JLabel("DISCONNECTED");
        statusLabel.setForeground(Color.red);
        northPanel.add(statusLabel);

        northPanel.add(new JLabel("Connected device:"));

        deviceLabel = new JLabel("-");
        northPanel.add(deviceLabel);

        mainPanel.add(northPanel);
        /////////////////////////////////////////
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2));

        JPanel button1Panel = new JPanel();
        button1Panel.setBorder(BorderFactory.createTitledBorder("Button 1"));
        button1Panel.setLayout(new BoxLayout(button1Panel, BoxLayout.Y_AXIS));

        event1Label = new JLabel("-");
        button1Panel.add(event1Label);

        action1Label = new JLabel("-");
        button1Panel.add(action1Label);


        southPanel.add(button1Panel);
        /////////////////////////////
        JPanel button2Panel = new JPanel();
        button2Panel.setBorder(BorderFactory.createTitledBorder("Button 2"));
        button2Panel.setLayout(new BoxLayout(button2Panel, BoxLayout.Y_AXIS));

        event2Label = new JLabel("-");
        button2Panel.add(event2Label);

        action2Label = new JLabel("-");
        button2Panel.add(action2Label);


        southPanel.add(button2Panel);


        mainPanel.add(southPanel);

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setStatus(Status status) {
        this.status = status;
        switch (status) {
            case CONNECTED:
                statusLabel.setText("CONNECTED");
                statusLabel.setForeground(Color.green);
                break;
            case DISCONNECTED:
                statusLabel.setText("DISCONNECTED");
                statusLabel.setForeground(Color.red);
                break;
        }
    }

    public void setDevice(AndroidDevice device) {
        if (device == null) {
            deviceLabel.setText("-");
        } else {
            deviceLabel.setText(device.getModel());
        }
    }

    public void setAction1(final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                action1Label.setText(text);
                sleep(1500);
                action1Label.setText("-");
            }
        }).start();

    }

    public void setAction2(final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                action2Label.setText(text);
                sleep(1500);
                action2Label.setText("-");
            }
        }).start();
    }

    public void setEvent1(final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                event1Label.setText(text);
                sleep(1500);
                event1Label.setText("-");
            }
        }).start();
    }

    public void setEvent2(final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                event2Label.setText(text);
                sleep(1500);
                event2Label.setText("-");
            }
        }).start();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(GUIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
