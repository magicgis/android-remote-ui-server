/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piskoviste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author tomique
 */
public class Threading {

    public static void main(String[] args) {
        final Object lock = new Object();

        final JFrame frame = new JFrame("Notify Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        JButton button = new JButton("Notify");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                synchronized (lock) {
                    lock.notify();
                }
            }
        });
        frame.add(button);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("1. starting");
                        lock.wait();
                        System.out.println("1. step 1");
                        lock.wait();
                        System.out.println("1. step 2");
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }).start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("2. starting");
                        lock.wait();
                        System.out.println("2. step 1");
                        lock.wait();
                        System.out.println("2. step 2");
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
