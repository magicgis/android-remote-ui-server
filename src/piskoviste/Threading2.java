/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piskoviste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author tomique
 */
public class Threading2 {

    private final Object lock;

    public Threading2() {

        lock = new Object();

        init();
    }

    private void init() {

        JFrame frame1 = new JFrame("Button frame");
        JButton button = new JButton("Click me!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (lock) {
                    lock.notify();
                }
            }
        });

        frame1.getContentPane().add(button);

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("I am waiting!");
                        lock.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Threading2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                System.out.println("I am finally released!!");
            }
        }).start();
    }

    public static void main(String[] args) {
        new Threading2();
    }
}
