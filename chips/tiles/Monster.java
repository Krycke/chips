/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.Movable;
import chips.*;
import it.marteEngine.ResourceManager;
import it.marteEngine.entity.Entity;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Ludvig
 */
public abstract class Monster extends Movable {
    private Direction intendedDirection;

    public Monster( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );

        setGraphic( ResourceManager.getSpriteSheet( "tileset" ) );
        addAnimations();

        setMovesPerSecond( 5f );
        setAcceptMoveIn( getMoveTime( getMovesPerSecond() ) );
    }



    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );

        if( collide( PLAYER, getX(), getY() ) != null ) {
            Globals.die( "Ooops! Look out for creatures!" );
        }

        if( getAcceptMoveIn() > 0 ) {
            setAcceptMoveIn( getAcceptMoveIn() - 1 );
        }

        if( getAcceptMoveIn() == 0 ) {
            setAcceptMoveIn( getMoveTime( getMovesPerSecond() ) );
            if( ! isSliding() ) {
                intendedDirection = getIntendedDirection();
                if( intendedDirection != null ) {
                    move( getDeltaToMove( intendedDirection ) );
                }
            }
        }

        setAnimation();
    }

    public abstract void addAnimations();

    @Override
    public boolean isAllowedToMove( float x, float y, Direction direction ) {
        if( ! super.isAllowedToMove( x, y, direction ) ){
            return false;
        }

        /*
         * Check against not allowed TileGroups
         */
        TileGroup[] notAllowed = {TileGroup.ACTING_DIRT,
                                  TileGroup.ITEM,
                                  TileGroup.MONSTER_ACTING_WALL,
                                  TileGroup.ACTING_WALL,
                                  TileGroup.WALL,
                                  TileGroup.MONSTER,
        };

        Entity object = collide( notAllowed, x, y );
        if( object != null ) {
            return false;
        }

        /*
         * Check map for regular walls
         */
        if( collide( SOLID, x, y ) != null ) {
            return false;
        }

        return true;
    }

    @Override
    public void setAnimation() {
        setAnim( getLastDirection().toString() );
    }

    @Override
    public void setIntendedDirection( Direction intendedDirection ) {
        this.intendedDirection = intendedDirection;
    }

    @Override
    public Direction getIntendedDirection() {
        for( Direction direction : getPreferedDirections() ) {
            Vector2f delta = getDeltaToMove( direction );
            if( isAllowedToMove( getX() + delta.x, getY() + delta.y, direction ) ) {
                return direction;
            }
        }
        return null;
    }

    public abstract ArrayList<Direction> getPreferedDirections();
}
