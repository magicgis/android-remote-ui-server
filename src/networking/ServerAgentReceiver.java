/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import user.test.GUIServer;
import xml.XMLHandler;

/**
 *
 * @author tomique
 */
public class ServerAgentReceiver implements Runnable {

    private static ServerAgentReceiver instance;
    private int port;
    private XMLHandler xmlHandler;
    private GUIServer guiServer;

    private ServerAgentReceiver() {
        port = 6788;
    }

    public static ServerAgentReceiver getInstance() {
        if (instance == null) {
            instance = new ServerAgentReceiver();
            instance.xmlHandler = XMLHandler.getInstance();
            instance.guiServer = GUIServer.getInstance();
        }
        return instance;
    }

    @Override
    public void run() {

        boolean stop = false;

        while (!stop) {

            try {

                ServerSocket serverSocket = new ServerSocket(port);

                while (true) {

                    Socket socket = serverSocket.accept();

                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    String message = (String) ois.readObject();

                    xmlHandler.receive(message);
                }

            } catch (IOException | ClassNotFoundException ex) {
//                ex.printStackTrace();
                // TODO jeste lepe osetrit!
                guiServer.setStatus(GUIServer.Status.DISCONNECTED);
                guiServer.setDevice(null);
            }
        }
    }
}
