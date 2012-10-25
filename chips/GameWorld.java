/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

import it.marteEngine.*;
import it.marteEngine.entity.InputManager;
import java.util.*;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Kristoffer
 */
public class GameWorld extends World {

    public static final String GOTONEXTLEVEL = "Go to next level";
    public static final String RESTARTCURRENTLEVEL = "Restart current level";
    public static final String MUTE = "Mute";
    public static final String JUMP_LEVEL = "Jump level";
    public LevelMap map;
    public int justClicked;
    private Music[] music = new Music[2];
    private boolean containerHaveLostFocus = false;
    java.util.List<SlidingObject> slideList;
    private float movesPerSecond = 10;
    private int makeSlideMoveIn;

    public GameWorld( int id ) {
        super( id );
    }

    @Override
    public void init( GameContainer container, StateBasedGame game ) throws SlickException {
        super.init( container, game );

        camera = new Camera( Globals.TILESX * Globals.TILEWIDTH - 1, Globals.TILESY * Globals.TILEHEIGHT - 1, new Vector2f( 32, 32 ) );
        slideList = new ArrayList<>();

        Chips.initRessources();
        restartGame();

        define( GOTONEXTLEVEL, Input.KEY_3 );
        define( RESTARTCURRENTLEVEL, Input.KEY_R );
        define( MUTE, Input.KEY_2 );
        define( JUMP_LEVEL, Input.KEY_F5 );
        define( TileType.COMPUTER_CHIP.toString(), Input.KEY_MINUS );

        //Lägg till skor och nyklar i inventory för att enklare feltesta banor.
        define( TileType.KEY_RED.toString(), Input.KEY_Y );
        define( TileType.KEY_BLUE.toString(), Input.KEY_U );
        define( TileType.KEY_YELLOW.toString(), Input.KEY_I );
        define( TileType.KEY_GREEN.toString(), Input.KEY_O );
        define( TileType.SHOE_ICE.toString(), Input.KEY_H );
        define( TileType.SHOE_FORCE.toString(), Input.KEY_J );
        define( TileType.SHOE_FIRE.toString(), Input.KEY_K );
        define( TileType.SHOE_WATER.toString(), Input.KEY_L );

        music[0] = ResourceManager.getMusic( "chip01" );
        music[1] = ResourceManager.getMusic( "chip02" );
    }

    public void restartGame() throws SlickException {
        Globals.lvl = 0;
        nextLvl();
    }

    public void nextLvl() throws SlickException {
        int lvl = ++Globals.lvl;


        loadMap( lvl );
    }

    public void loadMap( int lvl ) throws SlickException {

        Globals.reset();

        /*
         * @TODO: Release Keys
         */

        /**
         * Read in new map
         */
        try {
            map = new LevelMap( "data/levels/level" + lvl + ".tmx" );
            Globals.map = map;

            slideList.clear();
            makeSlideMoveIn = 0;

            /**
             * Set Bounds
             */
            camera.setSceneBounds( 0, 0, map.getWidth() * map.getTileWidth(), map.getHeight() * map.getTileHeight() );

            /**
             * Remove Old Entities
             */
            clear();
            /**
             * And Add New Ones
             */
            map.fillMapWithEntities();

            String mapName = map.getMapName();
            if( ME.world.container instanceof AppGameContainer ) {
                ( (AppGameContainer)ME.world.container ).setTitle( "Chip's Challenge: " + mapName.toUpperCase() );
            }

            ArrayList<String> lvlRows = new ArrayList<>();
            lvlRows.add( mapName.toUpperCase() );
            lvlRows.add( "Password: " + map.getMapPassword() );

            ME.world.add( new NewLevelBox( 62, 244, null, lvlRows ), World.ABOVE );
        }
        catch( RuntimeException e )
        {
            Globals.win = true;
        }

    }

    public void restartLvl() throws SlickException {
        loadMap( Globals.lvl );
    }

    @Override
    public void update( GameContainer container, StateBasedGame game, int delta ) throws SlickException {
        if( Globals.win ) {
            return;
        }
        
        super.update( container, game, delta );
        if( justClicked > 0 ) {
            justClicked--;
        }

        if( !Display.isActive() ) {
            InputManager.keyboardReset();
            return;
        }
        InputManager.resetKeyboardDestroy();

        checkCheats();

        if( Globals.goToNextLvl == true ) {
            Globals.goToNextLvl = false;
            nextLvl();
        }

        if( Globals.dead ) {
            Sys.alert( "Chip's Challenge", Globals.deathMessage );
            loadMap( Globals.lvl );
            return;
        }
        if( makeSlideMoveIn > 0 ) {
            makeSlideMoveIn--;
        }

        if( makeSlideMoveIn == 0 ) {
            makeSlideMoveIn = getMoveTime( movesPerSecond );
            if(  !slideList.isEmpty() ) {
                int i = 0;
                while( i < slideList.size() ) {
                    SlidingObject slidingObject = slideList.get(i);
                    Vector2f newPosition = slidingObject.getNewPosition();
                    Movable movable = slidingObject.getMovable();
                    if( movable.isAllowedToMove( newPosition.x, newPosition.y, movable.getLastDirection() ) ) {
                        movable.setPosition( newPosition );
                        movable.postMove();
                    }
                    i++;
                }
            }
        }

        playMusic();
    }

    public int getMoveTime( float mps ) {
        return (int)( 60 / mps );
    }

    public void addToSlideList( Movable entity, Vector2f position ) {
        for(SlidingObject slidingObject : slideList) {
            if( slidingObject.getMovable().equals(entity)) {
                slidingObject.setNewPosition(position);
                return;
            }
        }
        slideList.add( new SlidingObject( entity, position ) );
    }

    public void removeFromSlideList( Movable entity ) {
        for (Iterator<SlidingObject> it = slideList.iterator(); it.hasNext();) {
            SlidingObject slidingObject = it.next();
            if( slidingObject.getMovable().equals(entity)) {
                it.remove();
            }
        }
    }

    public void checkCheats() throws SlickException {
        if( justClicked > 0 ) {
            return;
        }
        TileType[] cheats = {TileType.SHOE_FORCE,
                             TileType.SHOE_FIRE,
                             TileType.SHOE_ICE,
                             TileType.SHOE_WATER,
                             TileType.KEY_BLUE,
                             TileType.KEY_GREEN,
                             TileType.KEY_RED,
                             TileType.KEY_YELLOW};
        for( TileType cheat : cheats ) {
            if( check( cheat.toString() ) ) {
                if( Globals.inventory.contains( cheat ) ) {
                    Globals.inventory.remove( cheat );
                }
                else {
                    Globals.inventory.add( cheat );
                }
                justClicked = 15;
            }
        }

        if( check( GOTONEXTLEVEL ) ) {
            justClicked = 15;
            Globals.goToNextLvl = true;
        }
        if( check( TileType.COMPUTER_CHIP.toString() ) ) {
            justClicked = 15;
            Globals.chipsLeft = Math.min(-1, Globals.chipsLeft-1);
        }

        if( check( RESTARTCURRENTLEVEL ) ) {
            justClicked = 15;
            restartLvl();
        }

//        if( check( JUMP_LEVEL ) ) {
//            justClicked = 15;
//            String newLvl = JOptionPane.showInputDialog( null, "Jump to level:", "Chip's Challenge", JOptionPane.PLAIN_MESSAGE );
//            if( newLvl != null ) {
//                try {
//                    int lvl = Integer.parseInt( newLvl );
//                    if( lvl > 0 ) {
//                        Globals.lvl = lvl;
//                        restartLvl();
//                    }
//                }
//                catch( Exception e ) {
//                }
//            }
//        }
    }

    public void playMusic() {
        if( check( MUTE ) && justClicked == 0 ) {
            justClicked = 15;
            Globals.playSounds = !Globals.playSounds;
        }

        Music lvlMusic = music[Globals.lvl % 2];
        if( Globals.playSounds ) {
            if( !lvlMusic.playing() ) {
                lvlMusic.resume();
                if( !lvlMusic.playing() ) {
                    lvlMusic.loop( 1.0f, 0.2f );
                }
            }
        }
        else {
            lvlMusic.pause();
        }
    }

    @Override
    public void render( GameContainer container, StateBasedGame game, Graphics g ) throws SlickException {

        if( Globals.win ) {
            g.drawImage( ResourceManager.getImage( "finish" ), 32, 32 );
        }
        else {
            /*
             * Render Map
             */
            renderMap();
        }

        //Draw Background
        g.drawImage( ResourceManager.getImage( "background" ), 0, 0 );

        /*
         * Render things on the left
         */
        renderLvl( g );
        renderTimeLeft( g );
        renderChipsLeft( g );
        renderInventory( g );

        //DrawEntites
        if( ! Globals.win ) {
            super.render( container, game, g );
        }
    }

    private void renderMap() {
        //Render Map but don't render layers that shouldn't be rendered (Entities and Collision)
        HashSet<Integer> dontRender = new HashSet<>();
        int mapCollisionIndex = map.getLayerIndex( it.marteEngine.Map.COLLISION );
        dontRender.add( mapCollisionIndex );
        dontRender.add( map.getLayerIndex( LevelMap.ENTITIES ) );
        dontRender.add( map.getLayerIndex( LevelMap.ENTITIES2 ) );

        int layers = map.getLayerCount();
        for( int layerIndex = 0; layerIndex < layers; layerIndex++ ) {
            if( ( ME.debugEnabled && layerIndex == mapCollisionIndex ) || !dontRender.contains( layerIndex ) ) {
                map.render( (int)camera.getCameraOffset().getX(),
                            (int)camera.getCameraOffset().getY(),
                            (int)( camera.getX() ) / Globals.TILEWIDTH,
                            (int)( camera.getY() ) / Globals.TILEHEIGHT,
                            9, 9, layerIndex, false );
            }
        }
    }

    private void renderInventory( Graphics g ) {
        SpriteSheet sprite = ResourceManager.getSpriteSheet( "tileset" );
        for( TileType item : Globals.inventory ) {
            int x = 0;
            int y = 0;
            int sx = 0;
            int sy = 0;
            switch( item ) {
                case KEY_RED:
                    x = 0;
                    y = 0;
                    break;
                case KEY_BLUE:
                    x = 1;
                    y = 0;
                    break;
                case KEY_YELLOW:
                    x = 2;
                    y = 0;
                    break;
                case KEY_GREEN:
                    x = 3;
                    y = 0;
                    break;
                case SHOE_ICE:
                    x = 0;
                    y = 1;
                    break;
                case SHOE_FORCE:
                    x = 1;
                    y = 1;
                    break;
                case SHOE_FIRE:
                    x = 2;
                    y = 1;
                    break;
                case SHOE_WATER:
                    x = 3;
                    y = 1;
                    break;
            }
            g.drawImage( sprite.getSprite( item.getId() % 13 - 1, item.getId() / 13 ), x * 32 + 352, y * 32 + 247 );
        }
    }

    private void renderLvl( Graphics g ) {
        int startY = 64;
        int lvl = Globals.lvl;
        renderNumber( g, startY, lvl );

    }

    private void renderTimeLeft( Graphics g ) {
        int startY = 126;
        int timeLeft = Globals.timeLimit;
        if( Globals.lvlStartTime > 0 && Globals.timeLimit >= 0 ) {
            timeLeft = Math.max( 0, (int)( ( Globals.timeLimit - ( System.currentTimeMillis() - Globals.lvlStartTime ) / 1000 ) ) );
        }
        if( timeLeft == 0 ) {
            Globals.die( "Ooops! You are out of time!" );
        }
        renderNumber( g, startY, timeLeft );
    }

    private void renderChipsLeft( Graphics g ) {
        int startY = 216;
        int chipsLeft = Globals.chipsLeft;
        renderNumber( g, startY, chipsLeft );
    }

    private void renderNumber( Graphics g, int startY, int number ) {
        int startX = 388;
        SpriteSheet numbers = ResourceManager.getSpriteSheet( "numbers" );
        int hundreds = 1;
        int tens = 1;
        int onces = 1;
        int row = 0;
        if( number != -1 ) {
            hundreds = (int)Math.floor( number / 100 );
            tens = (int)Math.floor( ( number - hundreds * 100 ) / 10 );
            onces = (int)Math.floor( ( number - hundreds * 100 - tens * 10 ) );
            if( hundreds > 0 ) {
                hundreds += 2;
            }
            if( !( tens == 0 && hundreds == 0 ) ) {
                tens += 2;
            }
            onces += 2;
        }
        if( number < 1 ) {
            row = 1;
        }
        g.drawImage( numbers.getSprite( hundreds, row ), startX, startY );
        g.drawImage( numbers.getSprite( tens, row ), startX + 17, startY );
        g.drawImage( numbers.getSprite( onces, row ), startX + 17 * 2, startY );

    }
}
