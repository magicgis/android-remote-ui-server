/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.guiproject.server.networking.test;

import cz.ctu.guiproject.server.networking.ServerNetworkAgent;
import cz.ctu.guiproject.server.networking.ServerNetworkAgentImpl;
import cz.ctu.guiproject.server.networking.ServerNetworkObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.buk
 */
public class ServerNetworkAgentTest implements ServerNetworkObserver{

    private static final Logger logger = Logger.getLogger(ServerNetworkAgentTest.class.getName());
    
    public ServerNetworkAgentTest() {
        init();
    }

    private void init() {
        final ServerNetworkAgent server = new ServerNetworkAgentImpl(6789);
        server.registerObserver(this);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                boolean stop = false;
                while(!stop) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        int networkId = Integer.parseInt(br.readLine());
                        String message = br.readLine();
                        server.send(networkId, message);
                        
                    } catch (IOException ex) {
                        logger.log(Level.SEVERE, ex.getMessage());
                        stop = true;
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new ServerNetworkAgentTest();
    }

    @Override
    public void update(String message) {
        System.out.println("Observed message from client: " + message);
        System.out.println("Active server threads: " + Thread.activeCount());
    }
}
