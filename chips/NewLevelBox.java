/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chips;

import it.marteEngine.ResourceManager;
import it.marteEngine.entity.TextEntity;
import java.util.ArrayList;
import org.newdawn.slick.*;

/**
 *
 * @author Kristoffer
 */
public class NewLevelBox extends TextEntity{

    public NewLevelBox( float x, float y, Font font, ArrayList<String> rows ) {
        super( x, y, font, rows );
        setGraphic( ResourceManager.getImage( "newLvlBox") );
        setJustify( Justify.CENTER );
        setStyle( java.awt.Font.BOLD );
        setSize( 20 );

        setHitBox( 12, 2, 203, 52 );
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );

        if( Globals.lvlStartTime > 0 ) {
            destroy();
        }
    }



    @Override
    public void render( GameContainer container, Graphics g ) throws SlickException {
        g.setColor( new Color( 255, 255, 0) );
        super.render( container, g );
    }




}
