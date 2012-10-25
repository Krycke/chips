/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.*;
import it.marteEngine.World;
import it.marteEngine.entity.TextEntity;
import org.newdawn.slick.*;

/**
 *
 * @author Ludvig
 */
public class Questionmark extends Tile {

    private TextEntity text;

    public Questionmark( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );

        text = new HintBox( 352, 165, null, Globals.hint );
        text.visible = false;

        Globals.world.add( text, World.ABOVE );
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );

        text.visible = (collide( PLAYER, getX(), getY() ) != null ? true : false);
    }
}
