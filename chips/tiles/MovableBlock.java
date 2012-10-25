/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.Movable;
import chips.LevelMap;
import chips.*;
import it.marteEngine.*;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.*;

/**
 *
 * @author Ludvig
 */
public class MovableBlock extends Movable{

    public MovableBlock( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );

        setGraphic( ResourceManager.getSpriteSheet( "tileset").getSprite( 0, 10) );
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        super.update(container, delta);
        Entity collide = collide(TileType.GROUND_TYPE_WATER.toString(), getX(), getY());
    }

    @Override
    public void collisionResponse(Entity other) {
        super.collisionResponse(other);
        if (other.isType(TileType.GROUND_TYPE_WATER.toString())) {
            TileType tile = TileType.GROUND_TYPE_DIRT;
            Entity dirt = new Ground(getX(), getY(), tile, ResourceManager.getSpriteSheet("tileset").getSprite(0, 11), LevelMap.DECORATIONDEPTH, tile.getTileGroups());
            ME.world.add(dirt);
            other.destroy();
            destroy();
        }


    }

    @Override
    public boolean isAllowedToMove(float x, float y, Direction direction) {

        if( ! super.isAllowedToMove( x, y, direction ) ){
            return false;
        }

        /* Dirt */
        Entity dirt = collide( TileGroup.ACTING_DIRT, x, y );
        if( dirt != null ) {
            return false;
        }

        /* Dirt */
        Entity object = collide( TileGroup.OBJECT, x, y );
        if( object != null ) {
            return false;
        }

        /*
         * Check against Acting walls
         */
        TileGroup[] wallTypes = { TileGroup.BLOCK_ACTING_WALL,
                                  TileGroup.ACTING_WALL,
                                  TileGroup.WALL };
        if( collide( wallTypes, x, y ) != null ) {
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
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public Direction getIntendedDirection() {
        throw new UnsupportedOperationException( "Not supported yet." );
    }




}
