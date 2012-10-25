/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Kristoffer
 */
public class SlidingObject {
    private Movable movable;
    private Vector2f newPosition;

    public SlidingObject(Movable movable, Vector2f newPosition) {
        this.movable = movable;
        this.newPosition = newPosition;
    }

    public Movable getMovable() {
        return movable;
    }

    public Vector2f getNewPosition() {
        return newPosition;
    }

    public void setMovable(Movable movable) {
        this.movable = movable;
    }

    public void setNewPosition(Vector2f newPosition) {
        this.newPosition = newPosition;
    }
    
}
