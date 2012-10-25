/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

import chips.tiles.*;
import it.marteEngine.ResourceManager;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Kristoffer
 */
public class Player extends Movable {

    public static final String GROUND = "Ground";
    private final int movesPerSecond = 5;
    private final int movesPerSecondSliding = 7;
    private int acceptManualMoveIn;
    private int acceptAutomaticMoveIn;
    private String onGround;
    private Direction lastDirection;
    private Direction intendedDirection;
    private Action action;
    private boolean allowPlayerToMove;

    public Player( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types, startDirection );

        setGraphic( ResourceManager.getSpriteSheet( "tileset" ) );
        addType( PLAYER );
        name = "PLAYER";

        addAnimation( Action.WALK + GROUND + Direction.UP, false, 12, 9 );
        addAnimation( Action.WALK + GROUND + Direction.LEFT, false, 13, 9 );
        addAnimation( Action.WALK + GROUND + Direction.DOWN, false, 14, 9 );
        addAnimation( Action.WALK + GROUND + Direction.RIGHT, false, 15, 9 );
        addAnimation( Action.WALK + TileType.GROUND_TYPE_WATER.toString() + Direction.UP.toString(), false, 12, 3 );
        addAnimation( Action.WALK + TileType.GROUND_TYPE_WATER.toString() + Direction.LEFT.toString(), false, 13, 3 );
        addAnimation( Action.WALK + TileType.GROUND_TYPE_WATER.toString() + Direction.DOWN.toString(), false, 14, 3 );
        addAnimation( Action.WALK + TileType.GROUND_TYPE_WATER.toString() + Direction.RIGHT.toString(), false, 15, 3 );
        addAnimation( Action.DIE + TileType.GROUND_TYPE_WATER.toString(), false, 3, 3 );
        addAnimation( Action.DIE + TileType.GROUND_TYPE_FIRE.toString(), false, 4, 3 );
        addAnimation( Action.WIN.toString(), false, 9, 3 );

        define( Direction.LEFT.toString(), Input.KEY_LEFT, Input.KEY_A, Input.KEY_NUMPAD4 );
        define( Direction.RIGHT.toString(), Input.KEY_RIGHT, Input.KEY_D, Input.KEY_NUMPAD6 );
        define( Direction.DOWN.toString(), Input.KEY_DOWN, Input.KEY_S, Input.KEY_NUMPAD2, Input.KEY_NUMPAD5 );
        define( Direction.UP.toString(), Input.KEY_UP, Input.KEY_W, Input.KEY_NUMPAD8 );

        lastDirection = Direction.DOWN;
        intendedDirection = null;
        action = Action.WALK;
        allowPlayerToMove = true;
        onGround = GROUND;
        acceptManualMoveIn = 0;
        acceptAutomaticMoveIn = 0;
    }

    public void reset() {
        lastDirection = Direction.DOWN;
        intendedDirection = null;
        action = Action.WALK;
        allowPlayerToMove = true;
        onGround = GROUND;
        acceptManualMoveIn = 0;
        acceptAutomaticMoveIn = 0;
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {

        if( Globals.dead ) {
            return;
        }

        if( acceptAutomaticMoveIn > 0 ) {
            acceptAutomaticMoveIn--;
        }
        if( acceptManualMoveIn > 0 ) {
            acceptManualMoveIn--;
        }
        if( acceptAutomaticMoveIn == 0 ) {
            makeAutomaticMove();
        }

        if( !( Globals.lvlStartTime > 0 ) ) {
            if( check( Direction.RIGHT.toString() )
                || check( Direction.LEFT.toString() )
                || check( Direction.DOWN.toString() )
                || check( Direction.UP.toString() ) ) {
                Globals.lvlStartTime = System.currentTimeMillis();
            }
        }

        if( acceptManualMoveIn < 3 ) {
            getIntendedDirection();
        }

        if( intendedDirection != null
            && allowPlayerToMove
            && ( acceptManualMoveIn == 0 ) ) {

            makeManualMove();

        }


        setAnimation();

        super.update( container, delta );


    }

    public void makeManualMove() {

        float dx = 0;
        float dy = 0;
        switch( intendedDirection ) {
            case RIGHT:
                dx = Globals.TILEWIDTH;
                break;
            case LEFT:
                dx = -Globals.TILEWIDTH;
                break;
            case DOWN:
                dy = Globals.TILEHEIGHT;
                break;
            case UP:
                dy = -Globals.TILEHEIGHT;
                break;

        }
        if( ( dx != 0 || dy != 0 ) && isAllowedToMove( getX() + dx, getY() + dy, intendedDirection ) ) {
            action = Action.WALK;
            move( dx, dy );
            acceptManualMoveIn = getMoveTime( movesPerSecond );
        }
        lastDirection = intendedDirection;
        intendedDirection = null;

    }

    @Override
    public void postMove() {
        super.postMove();

        //Pickup Items
        Entity item = (Tile)collide( TileGroup.ITEM, getX(), getY() );
        if( item != null ) {
            if( item.isType( TileType.COMPUTER_CHIP.toString() ) ) {
                Globals.chipsLeft = Math.max( 0, Globals.chipsLeft - 1 );
            }
            else {
                Globals.inventory.add( TileType.getTypeByName( item.name ) );

            }
            item.destroy();
        }

        //Are we dying from Water or Fire
        Tile collide = (Tile)collide( TileGroup.ELEMENT, getX(), getY() );
        if( collide != null ) {
            switch( collide.getTileType() ) {
                case GROUND_TYPE_WATER:
                    setOnGround( collide.name );
                case GROUND_TYPE_FIRE:
                    if( !Globals.inventory.contains( TileType.getCorrespondingShoeFromGroundType( collide.getTileType() ) ) ) {
                        action = Action.DIE;
                        setOnGround( collide.name );
                    }

                    break;
                default:
                    setOnGround( GROUND );
                    action = Action.WALK;
            }
        }
        else if( collide( TileType.PORTAL, getX(), getY() ) != null ) {
            setOnGround( GROUND );
            action = Action.WIN;
        }
        else {
            setOnGround( GROUND );
            action = Action.WALK;
        }

    }

    @Override
    public Direction getIntendedDirection() {
        /*
         * Get intended direction
         */
        if( check( Direction.RIGHT.toString() ) ) {
            intendedDirection = Direction.RIGHT;
            return Direction.RIGHT;
        }
        else if( check( Direction.LEFT.toString() ) ) {
            intendedDirection = Direction.LEFT;
            return Direction.LEFT;
        }
        else if( check( Direction.DOWN.toString() ) ) {
            intendedDirection = Direction.DOWN;
            return Direction.DOWN;
        }
        else if( check( Direction.UP.toString() ) ) {
            intendedDirection = Direction.UP;
            return Direction.UP;
        }
        return null;
    }

    public void makeAutomaticMove() {

        //Check collision with special ground type
        Tile collidedWith = (Tile)collide( TileGroup.SLIDING_TILE, getX(), getY() );
        if( collidedWith != null ) {
            TileType ground = collidedWith.getTileType();
            TileType shoe = TileType.getCorrespondingShoeFromGroundType( ground );
            switch( ground ) {
                case GROUND_TYPE_ICE_CORNER_SW:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = false;
                    switch( lastDirection ) {
                        case LEFT:
                            intendedDirection = Direction.UP;
                            break;
                        case DOWN:
                            intendedDirection = Direction.RIGHT;
                            break;
                        default:
                            intendedDirection = lastDirection.getOppositeDirection();
                    }
                    break;
                case GROUND_TYPE_ICE_CORNER_NE:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = false;
                    switch( lastDirection ) {
                        case RIGHT:
                            intendedDirection = Direction.DOWN;
                            break;
                        case UP:
                            intendedDirection = Direction.LEFT;
                            break;
                        default:
                            intendedDirection = lastDirection.getOppositeDirection();
                    }
                    break;
                case GROUND_TYPE_ICE_CORNER_NW:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = false;
                    switch( lastDirection ) {
                        case LEFT:
                            intendedDirection = Direction.DOWN;
                            break;
                        case UP:
                            intendedDirection = Direction.RIGHT;
                            break;
                        default:
                            intendedDirection = lastDirection.getOppositeDirection();
                    }
                    break;
                case GROUND_TYPE_ICE_CORNER_SE:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = false;
                    switch( lastDirection ) {
                        case RIGHT:
                            intendedDirection = Direction.UP;
                            break;
                        case DOWN:
                            intendedDirection = Direction.LEFT;
                            break;
                        default:
                            intendedDirection = lastDirection.getOppositeDirection();
                    }
                    break;
                case GROUND_TYPE_ICE:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = false;
                    intendedDirection = lastDirection;
                    break;
                case GROUND_TYPE_FORCE_DOWN:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = true;
                    intendedDirection = Direction.DOWN;
                    break;
                case GROUND_TYPE_FORCE_LEFT:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = true;
                    intendedDirection = Direction.LEFT;
                    break;
                case GROUND_TYPE_FORCE_RIGHT:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = true;
                    intendedDirection = Direction.RIGHT;
                    break;
                case GROUND_TYPE_FORCE_UP:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = true;
                    intendedDirection = Direction.UP;
                    break;
                case GROUND_TYPE_FORCE_RANDOM:
                    if( Globals.inventory.contains( shoe ) ) {
                        return;
                    }
                    allowPlayerToMove = true;
                    intendedDirection = Direction.random();
                    break;
                case TELEPORT:
                    allowPlayerToMove = false;
                    intendedDirection = lastDirection;
                    Teleport teleport = (Teleport)collidedWith;
                    //Gets the nex teleport in the list
                    Teleport nextTeleport = teleport.getNextTeleport();
                    //Get the position of the nex teleport
                    Vector2f nextTeleportPos = nextTeleport.getPos();
                    //Get how we want to move
                    Vector2f delta = getDeltaToMove( intendedDirection );
                    //Check if we can pass to the next teleport
                    boolean isAllowedToMove = isAllowedToMove( nextTeleportPos.x + delta.x,
                                                               nextTeleportPos.y + delta.y, intendedDirection );

                    /*
                     * If we cant pass to this teleport, we check the next in
                     * our list until all are checked.
                     */
                    int counter = Globals.teleports.size();
                    while( !isAllowedToMove && counter > 0 ) {
                        nextTeleport = nextTeleport.getNextTeleport();
                        nextTeleportPos = nextTeleport.getPos();
                        isAllowedToMove = isAllowedToMove( nextTeleportPos.x + delta.x,
                                                           nextTeleportPos.y + delta.y, intendedDirection );
                        counter--;
                    }
                    setPosition( nextTeleport.getPos() );


                    break;
            }
            Vector2f dxdy = getDeltaToMove( intendedDirection );
            if( !isAllowedToMove( getX() + dxdy.getX(), getY() + dxdy.getY(), intendedDirection ) ) {
                intendedDirection = lastDirection.getOppositeDirection();
                dxdy = getDeltaToMove( intendedDirection );
            }
            if( dxdy.getX() != 0 || dxdy.getY() != 0 ) {
                lastDirection = intendedDirection;
                intendedDirection = null;
                move( dxdy );
                acceptAutomaticMoveIn = getMoveTime( movesPerSecondSliding );
            }
        }
        else {
            allowPlayerToMove = true;
        }
    }

    @Override
    public boolean isAllowedToMove( float x, float y, Direction direction ) {

        if( !super.isAllowedToMove( x, y, direction ) ) {
            return false;
        }

        /**
         * Tries to move a block
         */
        Movable block = (Movable)collide( TileType.MOVABLEBLOCK, x, y );
        if( block != null ) {
            Vector2f dxdy = getDeltaToMove( intendedDirection );

            /*
             * Check that we have a free space behind the block so we can push
             * it.
             */
            if( block.isAllowedToMove( x + dxdy.getX(), y + dxdy.getY(), intendedDirection ) ) {
                block.setLastDirection( intendedDirection );
                block.move( dxdy );
                return true;
            }
            else {
                return false;
            }
        }

        /*
         * Dirt
         */
        Entity dirt = collide( TileGroup.ACTING_DIRT, x, y );
        if( dirt != null ) {
            dirt.destroy();
            return true;
        }

        /*
         * Check against Acting walls
         */
        TileGroup[] wallTypes = {TileGroup.CHIP_ACTING_WALL,
                                 TileGroup.ACTING_WALL,
                                 TileGroup.WALL};
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
        try {
            setAnim( action + ( action != Action.WIN ? onGround + ( action != Action.DIE ? lastDirection : "" ) : "" ) );
        }
        catch( Exception e ) {
            System.out.println( e );
        }
    }

    @Override
    public void move( float dx, float dy ) {
        super.move( dx, dy );
    }

    @Override
    public void render( GameContainer container, Graphics g ) throws SlickException {
        super.render( container, g );
    }

    public void setOnGround( String onGround ) {
        this.onGround = onGround;
    }

    public void setAction( Action action ) {
        this.action = action;
    }
}
