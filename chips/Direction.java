/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

import java.util.Random;

/**
 *
 * @author Kristoffer
 */
public enum Direction {

    LEFT,
    RIGHT,
    UP,
    DOWN,
    NW,
    NE,
    SW,
    SE;

    public Direction getOppositeDirection() {
        switch( this ) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case NE:
                return SW;
            case NW:
                return SE;
            case SE:
                return NW;
            case SW:
                return SE;
        }
        return null;
    }

    public Direction getCCW() {
        switch( this ) {
            case LEFT:
                return DOWN;
            case DOWN:
                return RIGHT;
            case RIGHT:
                return UP;
            case UP:
                return LEFT;
        }
        return null;
    }

    public Direction getCW() {
        switch( this ) {
            case LEFT:
                return UP;
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
        }
        return null;
    }

    static public Direction random() {
        Random random = new Random();
        switch( random.nextInt( 4 ) ) {
            case 1:
                return UP;
            case 2:
                return DOWN;
            case 3:
                return LEFT;
            case 4:
                return RIGHT;
            default:
                return null;
        }
    }
}
