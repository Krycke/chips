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
public class Tank extends Monster{

    public Tank( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );
    }

    @Override
    public ArrayList<Direction> getPreferedDirections() {
        ArrayList<Direction> preferedDirections = new ArrayList();
        Direction lastDirection = getLastDirection();
        preferedDirections.add( lastDirection );
        return preferedDirections;
    }

    @Override
    public void addAnimations() {
        addAnimation( Direction.UP.toString(),    false, 12, 7 );
        addAnimation( Direction.LEFT.toString(),  false, 13, 7 );
        addAnimation( Direction.DOWN.toString(),  false, 14, 7 );
        addAnimation( Direction.RIGHT.toString(), false, 15, 7 );
    }


}
