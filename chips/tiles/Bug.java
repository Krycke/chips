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
public class Bug extends Monster{

    public Bug( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );
    }

    @Override
    public ArrayList<Direction> getPreferedDirections() {
        ArrayList<Direction> preferedDirections = new ArrayList();
        Direction lastDirection = getLastDirection();
        preferedDirections.add( lastDirection.getCCW() );
        preferedDirections.add( lastDirection );
        preferedDirections.add( lastDirection.getCW() );
        preferedDirections.add( lastDirection.getOppositeDirection() );
        return preferedDirections;
    }

    @Override
    public void addAnimations() {
        addAnimation( Direction.UP.toString(),    false, 0, 7 );
        addAnimation( Direction.LEFT.toString(),  false, 1, 7 );
        addAnimation( Direction.DOWN.toString(),  false, 2, 7 );
        addAnimation( Direction.RIGHT.toString(), false, 3, 7 );
    }

    @Override
    public boolean isAllowedToMove( float x, float y, Direction direction ) {
        boolean isAllowedToMove = super.isAllowedToMove( x, y, direction );

        if( isAllowedToMove ){
            if( collide( TileType.GROUND_TYPE_FIRE, x, y) != null )
                isAllowedToMove = false;
        }
        return isAllowedToMove;
    }




}
