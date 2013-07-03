/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import android_toolkit.AndroidDevice;
import java.util.ArrayList;

/**
 *
 * @author tomique
 */
public class SessionManager {

    private static int sessionCounter = 0;
    private ArrayList<Session> storage;
    private static SessionManager instance;
    private final int MAX_SESSION_COUNT = 10;

    private SessionManager() {
        storage = new ArrayList<>();
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Session getNewSession(AndroidDevice device) {
        
        if(storage.size() == MAX_SESSION_COUNT) {
            return null;
        }
        
        String sid = device.getModel();
        // replace all space characters with underscore
        sid = sid.replace(' ', '_');
        sid += String.format("%04d", sessionCounter++);
        
        Session session = new Session(sid);
        storage.add(session);

        return session;
    }
}
