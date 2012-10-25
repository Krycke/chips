/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.TileGroup;
import chips.TileType;
import it.marteEngine.ME;
import it.marteEngine.ResourceManager;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.Image;

/**
 *
 * @author Ludvig
 */
public class BlueWall extends Tile {

    public BlueWall( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void collisionResponse( Entity other ) {
        if( other.isType( PLAYER ) ) {
            ME.world.add( new Wall( getX(), getY()) );
            destroy();
        }
    }

}
