/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author tomique
 */
public class ServerAgentSender implements Runnable {

    private static ServerAgentSender instance;
    private int port;
    private final Object lock;
    private String message;

    private ServerAgentSender() {
        this.port = 6789;
        lock = new Object();
    }

    public static ServerAgentSender getInstance() {
        if (instance == null) {
            instance = new ServerAgentSender();
        }
        return instance;
    }

    public void send(String message) {

        this.message = message;

        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
    public void run() {

        boolean stop = false;

        while (!stop) {
            try {

                ServerSocket serverSocket = new ServerSocket(port);

                while (true) {

                    Socket socket = serverSocket.accept();

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                    oos.writeObject(message);
                }

            } catch (IOException ex) {
                // TODO osetrit lepe
//                ex.printStackTrace();
            }
        }
    }
}