/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.networking;

import cz.ctu.guiproject.server.networking.handler.ClientHandlerImpl;
import cz.ctu.guiproject.server.networking.handler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class ServerNetworkAgentImpl implements ServerNetworkAgent {

    private static int networkIdCounter = 0;
    private State state;
    // port number, that server uses
    private int port;
    // collection to store ClientListener threads
    private ArrayList<ClientHandlerImpl> clientHandlerList;
    // observer pattern related stuff
    private ArrayList<ServerNetworkObserver> observers;
    private String currentMessage;
    private static final Logger logger = Logger.getLogger(ServerNetworkAgentImpl.class.getName());

    public ServerNetworkAgentImpl(int port) {
        this.port = port;
        this.state = State.STOPPED;
        clientHandlerList = new ArrayList<>();
        observers = new ArrayList<>();
        start();
    }

    /**
     * Returns the networkId of the current ClientHandler instance
     *
     * @return networkId of the current ClientHandler instance
     */
    public int getNetworkId() {
        return networkIdCounter++;
    }

    /**
     * Connection of particular clientHandler failed. Runs ClientHandler
     * cleanup.
     *
     * @param clientHandler Failed ClientHandler instance
     */
    public void handlerConnectionFailed(ClientHandlerImpl clientHandler) {
        // ClientHandler cleanup
        if (clientHandler != null) {
            cleanupClientHandler(clientHandler);
        }
    }

    /**
     * Sets the current state of the server.
     *
     * @param state
     */
    private void setState(State state) {
        this.state = state;
        logger.log(Level.INFO, "Current state set to: " + state);
    }

    /**
     * Starts a new thread responsible for accepting new client connections and
     * starting appropriate ClientHandlers
     */
    private void start() {
        if (state == State.STOPPED) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    startServer();
                }
            }).start();
        } else {
            throw new RuntimeException("Server is already running!");
        }
    }

    private void startServer() {
        setState(State.RUNNING);
        try {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (state == State.RUNNING) {

                    logger.log(Level.INFO, "waiting for clients to connect");
                    // wait for another connection
                    Socket socket = serverSocket.accept();
                    logger.log(Level.INFO, "connection accepted");

                    // force server to stop
                    if (state == State.STOPPED) {
                        break;
                    }
                    // register new ClientListener
                    // TODO dependent variable
                    ClientHandlerImpl clientHandler = new ClientHandlerImpl(socket, this);
                    clientHandlerList.add(clientHandler);
                    new Thread(clientHandler).start();
                }
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Fatal error, server restart needed! " + ex.getMessage());
        }
    }

    /**
     * Stops the server instance and provides necessary cleanup. Called from
     * outside this class.
     */
    void stop() {
        setState(State.STOPPED);
        try {
            // connect to itself to finish the STOP operation
            new Socket("localhost", port);
        } catch (UnknownHostException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    @Override
    public void broadcast(String message) {
        for (ClientHandler c : clientHandlerList) {
            c.send(message);
        }
    }

    @Override
    public void send(int networkId, String message) {
        for (ClientHandlerImpl c : clientHandlerList) {
            if (c.getNetworkId() == networkId) {
                c.send(message);
                return;
            }
        }
        logger.log(Level.SEVERE, "Unable to send message, networkId probably does not exist.");
    }

    /**
     * Observer related method, when new message event occurs, this method is
     * called.
     *
     * @param currentMessage
     */
    public void messageReceived(String currentMessage) {
        this.currentMessage = currentMessage;
        notifyObservers();
    }

    /**
     * Makes the ClientHandler instance to cleanup its resources and removes it
     * from the clientHandlerList
     *
     * @param clientHandler Removed ClientHandler instance
     */
    private void cleanupClientHandler(ClientHandlerImpl clientHandler) {
        // free clientListener resources
        clientHandler.closeIO();
        // remove clientListener from the list
        if (!clientHandlerList.remove(clientHandler)) {
            throw new RuntimeException("Unable to remove clientHandler from the list!");
        }
        logger.log(Level.INFO, "ClientHandler closed, current clientHandlerList size = " + clientHandlerList.size());
    }

    @Override
    public void registerObserver(ServerNetworkObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(ServerNetworkObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (ServerNetworkObserver observer : observers) {
            observer.update(currentMessage);
        }
    }
}