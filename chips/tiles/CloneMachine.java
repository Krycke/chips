/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.*;
import it.marteEngine.ME;
import it.marteEngine.entity.Entity;
import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Ludvig
 */
public class CloneMachine extends Tile {

    public CloneMachine( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );
    }

    public void cloneEntity() throws CloneNotSupportedException {
        List<Entity> entities = ME.world.getEntities( new Vector2f( getX(), getY() ) );
        for( Entity entity : entities ) {
            if( entity.isType( TileGroup.OBJECT.toString() ) ) {
                Movable clonedEntity = (Movable)entity.clone();
                clonedEntity.setAllowedToMove( true );
                Vector2f deltaXY = clonedEntity.getDeltaToMove( clonedEntity.getLastDirection() );
                if( clonedEntity.isAllowedToMove( clonedEntity.getX() + deltaXY.x, clonedEntity.getY() + deltaXY.y, clonedEntity.getLastDirection() ) ) {
                    clonedEntity.move( deltaXY );
                    if( clonedEntity instanceof Monster ) {
                        clonedEntity.setAcceptMoveIn( clonedEntity.getMoveTime( clonedEntity.getMovesPerSecond() ) );
                    }
                    ME.world.add( clonedEntity );
                }
            }
        }
    }
}
