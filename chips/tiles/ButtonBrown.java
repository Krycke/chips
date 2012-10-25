/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.*;
import it.marteEngine.entity.Entity;
import java.util.*;
import org.newdawn.slick.*;

/**
 *
 * @author Ludvig
 */
public class ButtonBrown extends Tile implements Button {

    public ButtonBrown( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );

        if( collide( TileGroup.OBJECT, getX(), getY() ) != null ){
            push();
        }
    }


    @Override
    public void push() {
        /*
         * The beartraps in the Array is put in a forwards reading order
         */
        List<Entity> bearTraps = Globals.world.getEntities( TileType.BEAR_TRAP.toString() );
        BearTrap bearTrap = null;
        for( Iterator<Entity> it = bearTraps.iterator(); it.hasNext(); ) {
            bearTrap = (BearTrap)it.next();
            float posX = getX();
            float posY = getY();
            float trapPosX = bearTrap.getX();
            float trapPosY = bearTrap.getY();
            if( (posY < trapPosY) || (posY == trapPosY && posX < trapPosX ) ) {
                /*
                 * The first time this happens will be the first trap in forwards
                 * reading order starting at out button.
                 */
                break;
            }
        }
        if( bearTrap != null ) {
            bearTrap.setActive( false );
        }


    }
}
