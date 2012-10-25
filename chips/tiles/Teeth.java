/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.Direction;
import chips.Globals;
import chips.TileGroup;
import chips.TileType;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Kristoffer
 */
public class Teeth extends Monster {

    public Teeth( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );
        setMovesPerSecond( 2.5f );
    }

    @Override
    public ArrayList<Direction> getPreferedDirections() {
        ArrayList<Direction> preferedDirections = new ArrayList();
        Vector2f playerPos = Globals.player.getPos();
        int xDelta = (int)Math.abs( playerPos.x - getX() );
        int yDelta = (int)Math.abs( playerPos.y - getY() );
        if( xDelta > yDelta ) {
            if( playerPos.x > getX() ) {
                preferedDirections.add( Direction.RIGHT );
            }
            else if( playerPos.x < getX() ) {
                preferedDirections.add( Direction.LEFT );
            }
            if( playerPos.y > getY() ) {
                preferedDirections.add( Direction.DOWN );
            }
            else if( playerPos.y < getY() ) {
                preferedDirections.add( Direction.UP );
            }
        }
        else {
            if( playerPos.y > getY() ) {
                preferedDirections.add( Direction.DOWN );
            }
            else if( playerPos.y < getY() ) {
                preferedDirections.add( Direction.UP );
            }
            if( playerPos.x > getX() ) {
                preferedDirections.add( Direction.RIGHT );
            }
            else if( playerPos.x < getX() ) {
                preferedDirections.add( Direction.LEFT );
            }
        }
        return preferedDirections;
    }

    @Override
    public void addAnimations() {
        addAnimation( Direction.UP.toString(), false, 4, 8 );
        addAnimation( Direction.LEFT.toString(), false, 5, 8 );
        addAnimation( Direction.DOWN.toString(), false, 6, 8 );
        addAnimation( Direction.RIGHT.toString(), false, 7, 8 );
    }
}
