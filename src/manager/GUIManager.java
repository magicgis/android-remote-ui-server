/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

/**
 *
 * @author tomique
 */
public class GUIManager {

    private static GUIManager instance;

    private GUIManager() {
        // setup available GUI components
    }

    public static GUIManager getInstance() {
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }
}
