/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

/**
 *
 * @author tomique
 */
public class Event {
    
    protected int[] point;
    
    public Event(int[] point) {
        this.point = point;
    }
    
    public void setPoint(int[] point) {
        this.point = point;
    }
    
    public int[] getPoint() {
        return point;
    }
}
