/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.*;
import it.marteEngine.ResourceManager;
import org.newdawn.slick.Image;

/**
 *
 * @author Ludvig
 */
public class ToggleWall extends Tile {

    public ToggleWall( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction wallStart ) {
        super( x, y, tileType, image, depth, types );

        setGraphic( ResourceManager.getSpriteSheet( "tileset" ) );
        addAnimation( Direction.UP.toString(), false, 5, 2 );
        addAnimation( Direction.DOWN.toString(), false, 6, 2 );

        setAnim( wallStart.toString() );
        if( wallStart.equals( Direction.DOWN ) ) {
            removeType( TileGroup.ACTING_WALL.toString() );
            addType( TileGroup.ACTING_FLOOR.toString() );
        }

    }
}
