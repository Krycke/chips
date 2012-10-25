/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.Direction;
import chips.Globals;
import chips.TileGroup;
import chips.TileType;
import it.marteEngine.entity.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Ludvig
 */
public class ButtonBlue extends Tile implements Button{

    public ButtonBlue( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void push() {
        List<Entity> tanks = Globals.world.getEntities( TileType.TANK.toString() );
        for( Iterator<Entity> it = tanks.iterator(); it.hasNext(); ) {
            Monster entity = (Monster)it.next();
            entity.setLastDirection( entity.getLastDirection().getOppositeDirection() );
        }
    }
}
