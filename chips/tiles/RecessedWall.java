/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.TileGroup;
import chips.TileType;
import it.marteEngine.ME;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ludvig
 */
public class RecessedWall extends Tile {

    public RecessedWall( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        super.update(container, delta);

        if( collide(PLAYER, getX(), getY() ) != null ) {
            ME.world.add( new Wall( getX(), getY()) );
            destroy();
        }
    }
}
