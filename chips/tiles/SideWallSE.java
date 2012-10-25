/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.*;
import org.newdawn.slick.Image;

/**
 *
 * @author Ludvig
 */
public class SideWallSE extends SideWall {

    public SideWallSE( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );

    }

    @Override
    public void addWallDirections() {
        addWall( Direction.DOWN );
        addWall( Direction.RIGHT );
    }
}
