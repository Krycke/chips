/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.Globals;
import chips.TileGroup;
import chips.TileType;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ludvig
 */
public class Socket extends Tile {

    public Socket( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {

        if( Globals.chipsLeft == 0)
        {
            removeType( TileGroup.ACTING_WALL.toString() );
            addType( TileGroup.ACTING_DIRT.toString() );
        }

        super.update( container, delta );
    }
}
