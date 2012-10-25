/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chips.tiles;

import chips.*;
import java.util.*;
import org.newdawn.slick.Image;

/**
 *
 * @author Kristoffer
 */
public class Walker extends Monster{
    private final Random random = new Random();

    public Walker( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );
    }

    @Override
    public ArrayList<Direction> getPreferedDirections() {
        Direction lastDirection = getLastDirection();
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add( lastDirection.getCCW() );
        directions.add( lastDirection.getCW() );
        directions.add( lastDirection.getOppositeDirection() );
        ArrayList<Direction> preferedDirections = new ArrayList();
        preferedDirections.add(lastDirection);
        while( directions.size() > 0 ) {
            int randomInt = random.nextInt( directions.size() );
            preferedDirections.add( directions.get(randomInt) );
            directions.remove( randomInt );
        }
        return preferedDirections;
    }

    @Override
    public void addAnimations() {
        addAnimation( Direction.UP.toString(),    false, 8, 8 );
        addAnimation( Direction.LEFT.toString(),  false, 9, 8 );
        addAnimation( Direction.DOWN.toString(),  false, 10, 8 );
        addAnimation( Direction.RIGHT.toString(), false, 11, 8 );
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
