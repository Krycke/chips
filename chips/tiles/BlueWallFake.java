/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.TileGroup;
import chips.TileType;
import it.marteEngine.ResourceManager;
import org.newdawn.slick.Image;

/**
 *
 * @author Ludvig
 */
public class BlueWallFake extends Tile {

    public BlueWallFake( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );

        setGraphic( ResourceManager.getSpriteSheet( "tileset").getSprite( 1, 14) );
    }
}
