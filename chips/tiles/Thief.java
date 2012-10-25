/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.*;
import org.newdawn.slick.*;

/**
 *
 * @author Ludvig
 */
public class Thief extends Tile {

    public Thief( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        super.update(container, delta);
        if( collide(PLAYER, getX(), getY() ) != null ) {
            Globals.inventory.remove( TileType.SHOE_FIRE );
            Globals.inventory.remove( TileType.SHOE_FORCE );
            Globals.inventory.remove( TileType.SHOE_ICE );
            Globals.inventory.remove( TileType.SHOE_WATER );
        }
    }
}
