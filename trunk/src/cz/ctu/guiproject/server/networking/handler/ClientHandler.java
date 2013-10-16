/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.networking.handler;

/**
 *
 * @author tomas.buk
 */
public interface ClientHandler extends Runnable {

    /**
     * Closes input and output streams as well as the socket.
     */
    void closeIO();

    /**
     * Sends message to the server/
     *
     * @param message Message sent to the server
     */
    void send(String message);
}
