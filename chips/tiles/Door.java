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
public class Door extends Tile {

    public Door( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );
        if( Globals.inventory.contains( TileType.getCorrespondingKeyFromDoor( getTileType() ) ) ) {
            addType( TileGroup.ACTING_DIRT.toString() );
            removeType( TileGroup.ACTING_WALL.toString() );
        }
        else {
            addType( TileGroup.ACTING_WALL.toString() );
            removeType( TileGroup.ACTING_DIRT.toString() );
        }
    }

    @Override
    public void collisionResponse( Entity other ) {
        super.collisionResponse( other );
        if( other.isType( PLAYER ) ) {
            TileType key = TileType.getCorrespondingKeyFromDoor( getTileType() );
            if( !key.equals( TileType.KEY_GREEN ) ) {
                Globals.inventory.remove( key );
            }
        }
    }
}
