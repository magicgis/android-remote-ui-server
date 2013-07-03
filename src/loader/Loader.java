/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loader;

import networking.ServerAgentReceiver;
import networking.ServerAgentSender;

/**
 *
 * @author tomique
 */
public class Loader {
    

    public Loader() {
        init();
    }
    
    private void init() {
        
        ServerAgentReceiver sRead = ServerAgentReceiver.getInstance();
        Thread tRead = new Thread(sRead);
        ServerAgentSender sWrite = ServerAgentSender.getInstance();
        Thread tWrite = new Thread(sWrite);

        tRead.start();
        tWrite.start();

        System.out.println("server side up and running");
        
    }
    
    public static void main(String[] args) {
        new Loader();
    }
}
