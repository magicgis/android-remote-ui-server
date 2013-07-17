/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.ArrayList;

/**
 *
 * @author tomique
 */
public class OnTouchEvent extends Event {

    private ArrayList<int[]> pointList;
    private String action;

    public OnTouchEvent(int[] point) {
        super(point);
    }
    
    public void setPointList(ArrayList<int[]> pointList) {
        this.pointList = pointList;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public ArrayList<int[]> getPointList() {
        return pointList;
    }
    
    public String getAction() {
        return action;
    }
}
