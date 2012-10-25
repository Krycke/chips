/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

import chips.tiles.*;
import it.marteEngine.*;
import it.marteEngine.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kristoffer
 */
public class LevelMap extends Map {

    /**
     * *******
     * Values used in the Map Editor *******
     */

    /*
     * Special Layers
     */
    public final static String ENTITIES = "Entities";
    public final static String ENTITIES2 = "Entities2";
    /*
     * Special Depths
     */
    public final static int FRINGEDEPTH = 50;
    public final static int DECORATIONDEPTH = 40;

    /*
     * Map Properties
     */
    public final static String CHIPCOUNT = "chipCount";
    public final static String TIMELIMIT = "timeLimit";
    public final static String HINT = "hint";
    public final static String NAME = "name";
    public final static String PASSWORD = "password";
    /**
     * Movable collisionEntity
     */
    public static final int TILEWIDTH = 32;
    public static final int TILEHEIGHT = 32;
    private final static Collidable collisionEntity = new Collidable( 0, 0, TILEWIDTH, TILEHEIGHT );

    public LevelMap( String ref ) throws SlickException {
        super( ref );
        Globals.chipsLeft = Integer.parseInt( getMapProperty( CHIPCOUNT, "-1" ) );
        Globals.timeLimit = Integer.parseInt( getMapProperty( TIMELIMIT, "-1" ) );
        Globals.hint = "Hint: " + getMapProperty( HINT, "" );
        ME.world.add( collisionEntity );
    }

    public int getTileId( int x, int y ) {
        return getTileId( x, y, getLayerIndex( ENTITIES ) );
    }

    public String getMapName() {
        return getMapProperty( NAME, "No name..." );
    }

    public String getMapPassword() {
        return getMapProperty( PASSWORD, "...." );
    }

    public void fillMapWithEntities() throws SlickException {
        int layerCount = getLayerCount();
        List<Integer> entitiesIndex = new ArrayList<>();
        entitiesIndex.add( getLayerIndex( ENTITIES ) );
        entitiesIndex.add( getLayerIndex( ENTITIES2 ) );
        for( Integer index : entitiesIndex ) {
            _fillMapWithEntities( layerCount, index );
        }
    }

    private void _fillMapWithEntities( int layerCount, int entitiesIndex ) throws SlickException {
        if( layerCount > 0 && entitiesIndex >= 0 ) {
            for( int i = 0; i < getWidth(); i++ ) {
                for( int j = 0; j < getHeight(); j++ ) {
                    int tileID = getTileId( i, j, entitiesIndex );
                    if( tileID > 0 ) {
                        Image tileImage = getTileImage( i, j, entitiesIndex );
                        int posX = i * getTileWidth();
                        int posY = ( j + 1 ) * getTileHeight() - tileImage.getHeight();
                        TileType tileType = TileType.getTypeById( tileID );

                        Entity tile;
                        if( tileType != null ) {
                            switch( tileType ) {
                                case PLAYER:
                                    tile = new Player( posX,
                                                       posY,
                                                       tileType,
                                                       tileImage,
                                                       FRINGEDEPTH,
                                                       tileType.getTileGroups(),
                                                       Direction.DOWN );
                                    ME.world.camera.follow( tile, Camera.FollowStyle.LOCKON );
                                    Globals.player = (Player)tile;
                                    break;
                                case MOVABLEBLOCK:
                                    tile = new MovableBlock( posX,
                                                             posY,
                                                             tileType,
                                                             tileImage,
                                                             FRINGEDEPTH + entitiesIndex,
                                                             tileType.getTileGroups(),
                                                             tileType.getDirection( tileID ) );

                                    break;
                                case BOMB:
                                    tile = new Bomb( posX,
                                                     posY,
                                                     tileType,
                                                     tileImage,
                                                     DECORATIONDEPTH + entitiesIndex,
                                                     tileType.getTileGroups() );
                                    break;
                                case TELEPORT:
                                    tile = new Teleport( posX,
                                                         posY,
                                                         tileType,
                                                         tileImage,
                                                         DECORATIONDEPTH + entitiesIndex,
                                                         tileType.getTileGroups() );
                                    Teleport.addTeleportToList( (Teleport)tile );

                                    break;
                                case SPY:
                                    tile = new Thief( posX,
                                                      posY,
                                                      tileType,
                                                      tileImage,
                                                      DECORATIONDEPTH + entitiesIndex,
                                                      tileType.getTileGroups() );
                                    break;
                                case BUTTON_GREEN:
                                    tile = new ButtonGreen( posX,
                                                            posY,
                                                            tileType,
                                                            tileImage,
                                                            DECORATIONDEPTH + entitiesIndex,
                                                            tileType.getTileGroups() );
                                    break;
                                case BUTTON_BLUE:
                                    tile = new ButtonBlue( posX,
                                                           posY,
                                                           tileType,
                                                           tileImage,
                                                           DECORATIONDEPTH + entitiesIndex,
                                                           tileType.getTileGroups() );
                                    break;
                                case BUTTON_RED:
                                    tile = new ButtonRed( posX,
                                                          posY,
                                                          tileType,
                                                          tileImage,
                                                          DECORATIONDEPTH + entitiesIndex,
                                                          tileType.getTileGroups() );
                                    break;
                                case BUTTON_BROWN:
                                    tile = new ButtonBrown( posX,
                                                            posY,
                                                            tileType,
                                                            tileImage,
                                                            DECORATIONDEPTH + entitiesIndex,
                                                            tileType.getTileGroups() );
                                    break;
                                case BEAR_TRAP:
                                    tile = new BearTrap( posX,
                                                         posY,
                                                         tileType,
                                                         tileImage,
                                                         DECORATIONDEPTH + entitiesIndex,
                                                         tileType.getTileGroups() );
                                    break;
                                case DOOR_BLUE:
                                case DOOR_YELLOW:
                                case DOOR_GREEN:
                                case DOOR_RED:
                                    tile = new Door( posX,
                                                     posY,
                                                     tileType,
                                                     tileImage,
                                                     DECORATIONDEPTH + entitiesIndex,
                                                     tileType.getTileGroups() );
                                    break;
                                case PORTAL:
                                    tile = new Portal( posX,
                                                       posY,
                                                       tileType,
                                                       tileImage,
                                                       DECORATIONDEPTH + entitiesIndex,
                                                       tileType.getTileGroups() );
                                    break;
                                case SOCKET:
                                    tile = new Socket( posX,
                                                       posY,
                                                       tileType,
                                                       tileImage,
                                                       DECORATIONDEPTH + entitiesIndex,
                                                       tileType.getTileGroups() );
                                    break;
                                case GROUND_TYPE_FIRE:
                                case GROUND_TYPE_WATER:
                                case GROUND_TYPE_DIRT:
                                    tile = new Ground( posX,
                                                       posY,
                                                       tileType,
                                                       tileImage,
                                                       DECORATIONDEPTH + entitiesIndex,
                                                       tileType.getTileGroups() );
                                    break;
                                case QUESTIONMARK:
                                    tile = new Questionmark( posX,
                                                             posY,
                                                             tileType,
                                                             tileImage,
                                                             DECORATIONDEPTH + entitiesIndex,
                                                             tileType.getTileGroups() );
                                    break;
                                case BUG:
                                    tile = new Bug( posX,
                                                    posY,
                                                    tileType,
                                                    tileImage,
                                                    FRINGEDEPTH + entitiesIndex,
                                                    tileType.getTileGroups(),
                                                    tileType.getDirection( tileID ) );
                                    break;
                                case FIREBALL:
                                    tile = new Fireball( posX,
                                                         posY,
                                                         tileType,
                                                         tileImage,
                                                         FRINGEDEPTH + entitiesIndex,
                                                         tileType.getTileGroups(),
                                                         tileType.getDirection( tileID ) );
                                    break;
                                case TANK:
                                    tile = new Tank( posX,
                                                     posY,
                                                     tileType,
                                                     tileImage,
                                                     FRINGEDEPTH + entitiesIndex,
                                                     tileType.getTileGroups(),
                                                     tileType.getDirection( tileID ) );
                                    break;
                                case TEETH:
                                    tile = new Teeth( posX,
                                                      posY,
                                                      tileType,
                                                      tileImage,
                                                      FRINGEDEPTH + entitiesIndex,
                                                      tileType.getTileGroups(),
                                                      tileType.getDirection( tileID ) );
                                    break;
                                case GLIDER:
                                    tile = new Glider( posX,
                                                       posY,
                                                       tileType,
                                                       tileImage,
                                                       FRINGEDEPTH + entitiesIndex,
                                                       tileType.getTileGroups(),
                                                       tileType.getDirection( tileID ) );
                                    break;
                                case PAEAMECIUM:
                                    tile = new Paramecium( posX,
                                                           posY,
                                                           tileType,
                                                           tileImage,
                                                           FRINGEDEPTH + entitiesIndex,
                                                           tileType.getTileGroups(),
                                                           tileType.getDirection( tileID ) );
                                    break;
                                case BLOB:
                                    tile = new Blob( posX,
                                                     posY,
                                                     tileType,
                                                     tileImage,
                                                     FRINGEDEPTH + entitiesIndex,
                                                     tileType.getTileGroups(),
                                                     tileType.getDirection( tileID ) );
                                    break;
                                case WALKER:
                                    tile = new Walker( posX,
                                                       posY,
                                                       tileType,
                                                       tileImage,
                                                       FRINGEDEPTH + entitiesIndex,
                                                       tileType.getTileGroups(),
                                                       tileType.getDirection( tileID ) );
                                    break;
                                case PINK_BALL:
                                    tile = new PinkBall( posX,
                                                         posY,
                                                         tileType,
                                                         tileImage,
                                                         FRINGEDEPTH + entitiesIndex,
                                                         tileType.getTileGroups(),
                                                         tileType.getDirection( tileID ) );
                                    break;
                                case RECESSED_WALL:
                                    tile = new RecessedWall( posX,
                                                             posY,
                                                             tileType,
                                                             tileImage,
                                                             DECORATIONDEPTH + entitiesIndex,
                                                             tileType.getTileGroups() );
                                    break;
                                case BLUE_WALL:
                                    tile = new BlueWall( posX,
                                                         posY,
                                                         tileType,
                                                         tileImage,
                                                         DECORATIONDEPTH + entitiesIndex,
                                                         tileType.getTileGroups() );
                                    break;
                                case HIDDEN_WALL:
                                    tile = new HiddenWall( posX,
                                                           posY,
                                                           tileType,
                                                           tileImage,
                                                           DECORATIONDEPTH + entitiesIndex,
                                                           tileType.getTileGroups() );
                                    break;
                                case BLUE_WALL_FAKE:
                                    tile = new BlueWallFake( posX,
                                                             posY,
                                                             tileType,
                                                             tileImage,
                                                             DECORATIONDEPTH + entitiesIndex,
                                                             tileType.getTileGroups() );
                                    break;
                                case TOGGLE_WALL:
                                    tile = new ToggleWall( posX,
                                                           posY,
                                                           tileType,
                                                           tileImage,
                                                           DECORATIONDEPTH + entitiesIndex,
                                                           tileType.getTileGroups(),
                                                           tileType.getDirection( tileID ) );
                                    break;
                                case SIDE_WALL_UP:
                                    tile = new SideWallUp( posX,
                                                           posY,
                                                           tileType,
                                                           tileImage,
                                                           DECORATIONDEPTH + entitiesIndex,
                                                           tileType.getTileGroups() );
                                    break;
                                case SIDE_WALL_LEFT:
                                    tile = new SideWallLeft( posX,
                                                             posY,
                                                             tileType,
                                                             tileImage,
                                                             DECORATIONDEPTH + entitiesIndex,
                                                             tileType.getTileGroups() );
                                    break;
                                case SIDE_WALL_DOWN:
                                    tile = new SideWallDown( posX,
                                                             posY,
                                                             tileType,
                                                             tileImage,
                                                             DECORATIONDEPTH + entitiesIndex,
                                                             tileType.getTileGroups() );
                                    break;
                                case SIDE_WALL_RIGHT:
                                    tile = new SideWallRight( posX,
                                                              posY,
                                                              tileType,
                                                              tileImage,
                                                              DECORATIONDEPTH + entitiesIndex,
                                                              tileType.getTileGroups() );
                                    break;
                                case SIDE_WALL_SE:
                                    tile = new SideWallSE( posX,
                                                              posY,
                                                              tileType,
                                                              tileImage,
                                                              DECORATIONDEPTH + entitiesIndex,
                                                              tileType.getTileGroups() );
                                    break;
                                case CLONE_MACHINE:
                                    tile = new CloneMachine( posX,
                                                             posY,
                                                             tileType,
                                                             tileImage,
                                                             DECORATIONDEPTH + entitiesIndex,
                                                             tileType.getTileGroups() );
                                    break;
                                default:
                                    tile = new Tile( posX,
                                                     posY,
                                                     tileType,
                                                     tileImage,
                                                     DECORATIONDEPTH + entitiesIndex,
                                                     tileType.getTileGroups() );
                                    break;
                            }
                            if( tile != null ) {
                                ME.world.add( tile );
                            }
                        }
                        else {
                            tile = new Tile( posX,
                                             posY,
                                             TileType.UNKNOWN,
                                             tileImage,
                                             DECORATIONDEPTH + entitiesIndex, null );
                            if( tile != null ) {
                                ME.world.add( tile );
                            }
                        }
                    }
                }
            }
        }
    }

    public TileType getGroundTypeByPosition( int x, int y ) {
        int id = getTileId( x, y );
        TileType type = TileType.getTypeById( id );
        return type;
    }
}
