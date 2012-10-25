/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.TileGroup;
import chips.TileType;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.Image;

/**
 *
 * @author Ludvig
 */
public class Tile extends Entity {

    private TileType tileType;

    public Tile( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, image );
        setHitBox( 0, 0, getWidth(), getHeight() );
        this.depth = depth;
        this.name = tileType.toString();
        this.tileType = tileType;
        addType( name );
        if( types != null ) {
            for( TileGroup tileGroups : types ) {
                addType( tileGroups.toString() );
            }
        }
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    
    public Entity collide( TileGroup tile, float x, float y ) {
        return super.collide( tile.toString(), x, y );
    }

    public Entity collide( TileType tile, float x, float y ) {
        return super.collide( tile.toString(), x, y );
    }

    public Entity collide( TileGroup[] tiles, float x, float y ) {
        String[] stringTiles = new String[tiles.length];
        for( int i = 0; i < tiles.length; i++ ) {
            stringTiles[i] = tiles[i].toString();
        }
        return super.collide( stringTiles, x, y );
    }

    public Entity collide( TileType[] tiles, float x, float y ) {
        String[] stringTiles = new String[tiles.length];
        for( int i = 0; i < tiles.length; i++ ) {
            stringTiles[i] = tiles[i].toString();
        }
        return super.collide( stringTiles, x, y );
    }
}
