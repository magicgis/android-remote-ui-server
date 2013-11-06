/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.networking.handler;

import cz.ctu.guiproject.server.networking.ServerNetworkAgentImpl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class ClientHandlerImpl implements ClientHandler {

    private int networkId;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ServerNetworkAgentImpl server;
    private static final Logger logger = Logger.getLogger(ClientHandlerImpl.class.getName());

    public ClientHandlerImpl(Socket socket, ServerNetworkAgentImpl serverAgent) {
        this.socket = socket;
        this.server = serverAgent;
        networkId = serverAgent.getNetworkId();

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    public int getNetworkId() {
        return networkId;
    }

    @Override
    public void send(String message) {
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error writing to output stream: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            try {

                Object message = input.readObject();
                server.messageReceived("[" + networkId + "]" + message.toString());

            } catch (IOException ex) {
                logger.log(Level.WARNING, "Premature end of ClientHandler (" + networkId + ") " + ex.getMessage());
                running = false;
            } catch (ClassNotFoundException ex) {
                logger.log(Level.SEVERE, ex.getMessage());
                // TODO how to solve this? Terminate?
            }
        }
        // TODO clean my resources
        server.handlerConnectionFailed(this);
    }

    @Override
    public void closeIO() {
        try {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error occured when closing ClientHandler: " + ex.getMessage());
        }
    }

    // TODO adekvatne prepsat jak equals, tak hashcode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.networkId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientHandlerImpl other = (ClientHandlerImpl) obj;
        if (this.networkId != other.networkId) {
            return false;
        }
        return true;
    }
}
