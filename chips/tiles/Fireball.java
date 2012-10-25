/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chips.tiles;

import chips.Direction;
import chips.TileGroup;
import chips.TileType;
import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author Kristoffer
 */
public class Fireball extends Monster{

    public Fireball( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );
    }

    @Override
    public ArrayList<Direction> getPreferedDirections() {
        ArrayList<Direction> preferedDirections = new ArrayList();
        Direction lastDirection = getLastDirection();
        preferedDirections.add( lastDirection );
        preferedDirections.add( lastDirection.getCW() );
        preferedDirections.add( lastDirection.getCCW() );
        preferedDirections.add( lastDirection.getOppositeDirection() );
        return preferedDirections;
    }

    @Override
    public void addAnimations() {
        addAnimation( Direction.DOWN.toString(), false, 4, 7 );
        addAnimation( Direction.LEFT.toString(), false, 5, 7 );
        addAnimation( Direction.UP.toString(), false, 6, 7 );
        addAnimation( Direction.RIGHT.toString(), false, 7, 7 );
    }


}
