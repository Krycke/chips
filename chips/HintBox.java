/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chips;

import it.marteEngine.ResourceManager;
import it.marteEngine.entity.TextEntity;
import org.newdawn.slick.*;

/**
 *
 * @author Kristoffer
 */
public class HintBox extends TextEntity{

    public HintBox( float x, float y, Font font, String text ) {
        super( x, y, font, text );
        setGraphic( ResourceManager.getImage( "hintBox") );

        setJustify( Justify.CENTER );

        setHitBox( 7, 2, 114, 126 );
        fixTextBox();
    }

    @Override
    public void render( GameContainer container, Graphics g ) throws SlickException {
        g.setColor( new Color( 0, 255, 255) );
        super.render( container, g );
    }




}
