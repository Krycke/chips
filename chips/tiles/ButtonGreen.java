/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.*;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.Image;

/**
 *
 * @author Ludvig
 */
public class ButtonGreen extends Tile implements Button {

    public ButtonGreen(float x, float y, TileType tileType, Image image, int depth, TileGroup[] types) {
        super(x, y, tileType, image, depth, types);
    }

    @Override
    public void push() {
        for (Entity toggleWall : Globals.world.getEntities( TileType.TOGGLE_WALL.toString() ) ) {
            if (toggleWall.isType(TileGroup.ACTING_WALL.toString() ) ) {
                toggleWall.addType(TileGroup.ACTING_FLOOR.toString());
                toggleWall.removeType(TileGroup.ACTING_WALL.toString());
                toggleWall.setAnim( Direction.DOWN.toString() );
            }
            else {
                toggleWall.removeType(TileGroup.ACTING_FLOOR.toString());
                toggleWall.addType(TileGroup.ACTING_WALL.toString());
                toggleWall.setAnim( Direction.UP.toString() );
            }
        }
    }
}
