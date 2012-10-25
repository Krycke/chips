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
public class Blob extends Monster {

    private final Random random = new Random();

    public Blob( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );
        setMovesPerSecond( 2.5f );
    }

    @Override
    public ArrayList<Direction> getPreferedDirections() {
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add( Direction.UP );
        directions.add( Direction.DOWN );
        directions.add( Direction.LEFT );
        directions.add( Direction.RIGHT );
        ArrayList<Direction> preferedDirections = new ArrayList();
        while( directions.size() > 0 ) {
            int randomInt = random.nextInt( directions.size() );
            preferedDirections.add( directions.get(randomInt) );
            directions.remove( randomInt );
        }
        return preferedDirections;
    }

    @Override
    public void addAnimations() {
        addAnimation( Direction.UP.toString(),    false, 12, 8 );
        addAnimation( Direction.LEFT.toString(),  false, 13, 8 );
        addAnimation( Direction.DOWN.toString(),  false, 14, 8 );
        addAnimation( Direction.RIGHT.toString(), false, 15, 8 );
    }
}
