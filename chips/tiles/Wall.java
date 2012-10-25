/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.LevelMap;
import chips.TileType;
import it.marteEngine.ResourceManager;

/**
 *
 * @author Ludvig
 */
public class Wall extends Tile {

    public Wall( float x, float y ) {
        super( x, y, TileType.WALL, ResourceManager.getSpriteSheet( "tileset" ).getSprite( 0, 1 ), LevelMap.DECORATIONDEPTH, TileType.WALL.getTileGroups() );
    }
}
