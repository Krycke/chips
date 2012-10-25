/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

import chips.tiles.*;
import it.marteEngine.entity.Entity;
import java.util.List;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

/**
 *
 * @author Kristoffer
 */
public abstract class Movable extends Tile {

    private boolean allowedToMove = true;
    private Direction lastDirection;
    private Direction intendedDirection;

    private float movesPerSecond = 0;
    private int acceptMoveIn = -1;
    
    private boolean sliding = false;

    public Movable( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types, Direction startDirection ) {
        super( x, y, tileType, image, depth, types );
        lastDirection = startDirection;
        intendedDirection = lastDirection;
    }

    @Override
    public Entity collide( String type, float x, float y ) {
        Rectangle hitbox = new Rectangle( x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight );
        Entity collide = super.collide( type, x, y );
        if( collide != null ) {
            return collide;
        }

        if( type.equals( SOLID ) ) {
            return Globals.world.map.getCollision( hitbox );
        }

        return null;
    }
    @Override
    public List<Entity> collideInto( String type, float x, float y ) {
        Rectangle hitbox = new Rectangle( x + hitboxOffsetX, y + hitboxOffsetY, hitboxWidth, hitboxHeight );
        List entities = super.collideInto( type, x, y );

        Entity e = Globals.world.map.getCollision( hitbox );
        if( e != null ) {
            entities.add( e );
        }

        return entities;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection( Direction lastDirection ) {
        this.lastDirection = lastDirection;
    }

    public void setIntendedDirection( Direction intendedDirection ) {
        this.intendedDirection = intendedDirection;
    }

    public void setMovesPerSecond( float movesPerSecond ) {
        this.movesPerSecond = movesPerSecond;
    }

    public float getMovesPerSecond() {
        return movesPerSecond;
    }

    public void setAcceptMoveIn( int acceptMoveIn ) {
        this.acceptMoveIn = acceptMoveIn;
    }

    public int getAcceptMoveIn() {
        return acceptMoveIn;
    }
    
    public boolean isSliding() {
        return sliding;
    }

    @Override
    public void update( GameContainer container, int delta ) throws SlickException {
        super.update( container, delta );

        if( collide( TileType.CLONE_MACHINE, getX(), getY() ) != null ) {
            setAllowedToMove( false );
        }
    }

    public void setAllowedToMove( boolean allowedToMove ) {
        this.allowedToMove = allowedToMove;
    }

    public Vector2f getDeltaToMove( Direction dir ) {
        if( dir == null ) {
            return new Vector2f();
        }
        float dx = 0;
        float dy = 0;
        switch( dir ) {
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
        return new Vector2f( dx, dy );
    }

    public Direction getDirectionFromFloat( Float dx, Float dy ) {
        Direction direction = null;
        if( dx > 0 )
        {
            if( dy > 0 ) {
                direction = Direction.SE;
            }
            else if( dy < 0 ) {
                direction =  Direction.NE;
            }
            else {
                direction =  Direction.RIGHT;
            }
        }
        else if( dx < 0 )
        {
            if( dy > 0 ) {
                direction =  Direction.SW;
            }
            else if( dy < 0 ) {
                direction =  Direction.NW;
            }
            else {
                direction =  Direction.LEFT;
            }
        }
        else {
            if( dy > 0 ) {
                direction =  Direction.DOWN;
            }
            else if( dy < 0 ) {
                direction =  Direction.UP;
            }
        }
        return direction;
    }

    public int getMoveTime( float mps ) {
        return (int)( 60 / mps );
    }

    @Override
    public void move( float dx, float dy ) {
        super.move( dx, dy );
        setLastDirection(getDirectionFromFloat(dx, dy));
        postMove();
    }

    public void postMove() {
        //Push a button
        Button button = (Button)collide( TileGroup.BUTTON, getX(), getY() );
        if( button != null ) {
            button.push();
        }

        if( !isType( PLAYER ) ) {
            Tile slide = (Tile)collide( TileGroup.SLIDING_TILE, getX(), getY() );
            if( slide != null ) {
                //Check collision with special ground type
                TileType ground = slide.getTileType();
                switch( ground ) {
                    case GROUND_TYPE_ICE_CORNER_SW:
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
                        intendedDirection = lastDirection;
                        break;
                    case GROUND_TYPE_FORCE_DOWN:
                        intendedDirection = Direction.DOWN;
                        break;
                    case GROUND_TYPE_FORCE_LEFT:
                        intendedDirection = Direction.LEFT;
                        break;
                    case GROUND_TYPE_FORCE_RIGHT:
                        intendedDirection = Direction.RIGHT;
                        break;
                    case GROUND_TYPE_FORCE_UP:
                        intendedDirection = Direction.UP;
                        break;
                    case GROUND_TYPE_FORCE_RANDOM:
                        intendedDirection = Direction.random();
                        break;
                    case TELEPORT:
                        intendedDirection = lastDirection;
                        Teleport teleport = (Teleport)slide;
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
                         * If we cant pass to this teleport, we check the next
                         * in our list until all are checked.
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
                if( dxdy.x != 0 || dxdy.y != 0 ) {
                    sliding = true;
                    lastDirection = intendedDirection;
                    Globals.world.addToSlideList( this, new Vector2f( getX() + dxdy.x, getY() + dxdy.y ) );
                }

            }
            else {
                Globals.world.removeFromSlideList( this );
                sliding = false;
            }
        }
    }

    public abstract void setAnimation();

    public abstract Direction getIntendedDirection();

    public boolean isAllowedToMove( float x, float y, Direction direction ) {
        if( !allowedToMove ) {
            return false;
        }

        SideWall sideWall = (SideWall)collide( TileGroup.SIDE_WALL, getX(), getY() );
        if( sideWall != null && !sideWall.canExitTo( direction ) ) {
            return false;
        }
        sideWall = (SideWall)collide( TileGroup.SIDE_WALL, x, y );
        if( sideWall != null && !sideWall.canEnterFrom( direction ) ) {
            return false;
        }

        return true;
    }
}
