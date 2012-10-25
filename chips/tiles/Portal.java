/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.Globals;
import chips.TileGroup;
import chips.TileType;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ludvig
 */
public class Portal extends Tile {

    public Portal( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );

//        setGraphic( ResourceManager.getSpriteSheet( "tileset" ) );
//        Animation anim = new Animation( true );
//        anim.setLooping( true );
//        anim.addFrame( sheet.getSprite( 1, 5 ), 1000 );
//        anim.addFrame( sheet.getSprite( 3, 11 ), 1000 );
//        anim.addFrame( sheet.getSprite( 3, 10 ), 1000 );
//        addAnimation( "portal", anim);

    }
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        super.update(container, delta);
        Entity collide = collide(PLAYER, getX(), getY());
    }
    @Override
    public void collisionResponse(Entity other) {
        super.collisionResponse(other);
        if (other.isType(PLAYER)) {
            Globals.goToNextLvl = true;
        }

    }


}
