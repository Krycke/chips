/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chips.tiles;

import chips.*;
import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author Kristoffer
 */
public class PinkBall extends Monster{

    public PinkBall( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );
    }

    @Override
    public ArrayList<Direction> getPreferedDirections() {
        ArrayList<Direction> preferedDirections = new ArrayList();
        Direction lastDirection = getLastDirection();
        preferedDirections.add( lastDirection );
        preferedDirections.add( lastDirection.getOppositeDirection() );
        return preferedDirections;
    }

    @Override
    public void addAnimations() {
        addAnimation( Direction.UP.toString(), false, 8, 7 );
        addAnimation( Direction.LEFT.toString(), false, 9, 7 );
        addAnimation( Direction.DOWN.toString(), false, 10, 7 );
        addAnimation( Direction.RIGHT.toString(), false, 11, 7 );
    }


}
