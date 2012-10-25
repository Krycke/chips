/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

import chips.tiles.*;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ludvig
 */
public class Ground extends Tile {

    public Ground( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );
        Entity collide = collide( PLAYER, getX(), getY() );
        if( collide != null ) {
            Player player = (Player)collide;
            switch( getTileType() ) {
                case GROUND_TYPE_FIRE:
                    fire( player );
                    break;
                case GROUND_TYPE_WATER:
                    water( player );
                    break;
            }
        }
        Monster monster = (Monster)collide( TileGroup.MONSTER, getX(), getY() );
        if( monster != null ) {
            switch( getTileType() ) {
                case GROUND_TYPE_FIRE:
                    if( monster.getTileType() == TileType.FIREBALL ) {
                        return;
                    }
                case GROUND_TYPE_WATER:
                    if( monster.getTileType() == TileType.GLIDER ) {
                        return;
                    }
                    monster.destroy();
            }
        }
    }

    private void fire( Player player ) {
        if( Globals.inventory.contains( TileType.SHOE_FIRE ) ) {
            player.setOnGround( Player.GROUND );
        }
        else {
            Globals.die( "Ooops! Don't step in fire without fire boots!" );
        }
    }

    private void water( Player player ) {
        player.setOnGround( name );
        if( Globals.inventory.contains( TileType.SHOE_WATER ) ) {
        }
        else {
            Globals.die( "Ooops! Chip can't swim without flippers!" );
        }
    }
}
