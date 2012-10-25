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
 * @author Ludvig
 */
public abstract class SideWall extends Tile {
    ArrayList<Direction> wallsDirections;

    public SideWall( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );

        wallsDirections = new ArrayList<>();

        addWallDirections();

    }

    public void addWall( Direction direction ){
        wallsDirections.add( direction );
    }

    public abstract void addWallDirections();

    public boolean canEnterFrom( Direction direction ){
        if( wallsDirections.contains( direction.getOppositeDirection() ) )
            return false;
        return true;
    }

    public boolean canExitTo( Direction direction ){
        if( wallsDirections.contains( direction ) )
            return false;
        return true;
    }
}
