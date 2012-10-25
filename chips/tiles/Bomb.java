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
public class Bomb extends Tile {

    public Bomb( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );
        Entity collide = collide( TileGroup.OBJECT.toString(), getX(), getY() );
        if( collide != null ) {
            if( collide.isType( PLAYER ) ) {
                Globals.die( "Ooops! Don't touch the bombs!" );
            }
            else {
                collide.destroy();
                destroy();
            }
        }
    }
}
