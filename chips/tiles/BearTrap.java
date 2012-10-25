/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.Movable;
import chips.*;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.*;

/**
 *
 * @author Ludvig
 */
public class BearTrap extends Tile {

    boolean active = true;

    public BearTrap( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );
        Movable collide = (Movable)collide( TileGroup.OBJECT.toString(), getX(), getY() );
        if( collide != null ) {
            collide.setAllowedToMove( !active );
        }

        active = true;
    }
}
