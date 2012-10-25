/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.*;
import it.marteEngine.entity.Entity;
import java.util.*;
import java.util.logging.*;
import org.newdawn.slick.Image;

/**
 *
 * @author Ludvig
 */
public class ButtonRed extends Tile implements Button {

    public ButtonRed(float x, float y, TileType tileType, Image image, int depth, TileGroup[] types) {
        super(x, y, tileType, image, depth, types);
    }

    @Override
    public void push() {
        List<Entity> cloneMachines = Globals.world.getEntities( TileType.CLONE_MACHINE.toString() );
        CloneMachine cloneMachine = null;
        Collections.sort( cloneMachines );
        float posX = getX();
        float posY = getY();
        for( Iterator<Entity> it = cloneMachines.iterator(); it.hasNext(); ) {
            cloneMachine = (CloneMachine)it.next();
            float clonePosX = cloneMachine.getX();
            float clonePosY = cloneMachine.getY();
            if( (posY < clonePosY) || (posY == clonePosY && posX < clonePosX ) ) {
                /*
                 * The first time this happens will be the first trap in forwards
                 * reading order starting at out button.
                 */
                break;
            }
        }
        if( cloneMachine != null ) {
            try {
                cloneMachine.cloneEntity();
            }
            catch( CloneNotSupportedException ex ) {
                Logger.getLogger( ButtonRed.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }
}
