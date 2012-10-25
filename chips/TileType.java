/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

/**
 *
 * @author Ludvig
 */
public enum TileType {

    PLAYER( "player", 192, TileGroup.OBJECT ),
    COMPUTER_CHIP( "chip", 27, TileGroup.ACTING_DIRT, TileGroup.ITEM ),
    MOVABLEBLOCK( "movable_block", 0, 14, 0, 15, 1, 0, 1, 1, TileGroup.OBJECT, TileGroup.MONSTER_ACTING_WALL ),
    //Keys
    KEY_BLUE( "key_blue", 59, TileGroup.KEY, TileGroup.ITEM ),
    KEY_RED( "key_red", 72, TileGroup.KEY, TileGroup.ITEM ),
    KEY_GREEN( "key_green", 85, TileGroup.KEY, TileGroup.ITEM ),
    KEY_YELLOW( "key_yellow", 98, TileGroup.KEY, TileGroup.ITEM ),
    //Acting Walls
    DOOR_BLUE( "door_blue", 80, TileGroup.ACTING_WALL ),
    DOOR_RED( "door_red", 93, TileGroup.ACTING_WALL ),
    DOOR_GREEN( "door_green", 106, TileGroup.ACTING_WALL ),
    DOOR_YELLOW( "door_yellow", 119, TileGroup.ACTING_WALL ),
    PORTAL( "portal", 67, TileGroup.MONSTER_ACTING_WALL, TileGroup.BLOCK_ACTING_WALL ),
    SOCKET( "socket", 29, TileGroup.ACTING_WALL ),
    QUESTIONMARK( "?", 198, TileGroup.ACTING_FLOOR ),
    RECESSED_WALL( "recessed wall", 2, 14, TileGroup.MONSTER_ACTING_WALL, TileGroup.BLOCK_ACTING_WALL ),
    WALL( "wall", 0, 1, TileGroup.ACTING_WALL ),
    BLUE_WALL( "blue_wall", 1, 14, TileGroup.ACTING_WALL ),
    HIDDEN_WALL( "hidden_wall", 2, 12, TileGroup.ACTING_WALL ),
    BLUE_WALL_FAKE( "blue_wall_fake", 1, 15, TileGroup.ACTING_DIRT ),
    //Groundtypes
    GROUND_TYPE_WATER( "ground_type_water", 40, TileGroup.ELEMENT, TileGroup.DESTRUCTIVE_OBSTACLE ),
    GROUND_TYPE_FIRE( "ground_type_fire", 53, TileGroup.ELEMENT, TileGroup.DESTRUCTIVE_OBSTACLE ),
    GROUND_TYPE_ICE_CORNER_NW( "ground_type_ice_nw", 132, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_ICE_CORNER_NE( "ground_type_ice_ne", 145, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_ICE_CORNER_SE( "ground_type_ice_se", 158, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_ICE_CORNER_SW( "ground_type_ice_sw", 171, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_ICE( "ground_type_ice", 157, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_FORCE_UP( "ground_type_direction_up", 28, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_FORCE_RIGHT( "ground_type_direction_right", 41, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_FORCE_LEFT( "ground_type_direction_left", 54, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_FORCE_DOWN( "ground_type_direction_down", 170, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_FORCE_RANDOM( "ground_type_direction_random", 30, TileGroup.ELEMENT, TileGroup.SLIDING_TILE ),
    GROUND_TYPE_DIRT( "ground_type_dirt", 0, 11, TileGroup.CHIP_ACTING_FLOOR, TileGroup.ACTING_DIRT ),
    GROUND_TYPE_GRAVEL( "ground_type_gravel", 2, 13, TileGroup.CHIP_ACTING_FLOOR, TileGroup.MONSTER_ACTING_WALL ),
    //Shoes
    SHOE_WATER( "shoe_water", 111, TileGroup.BOOT, TileGroup.ITEM ),
    SHOE_FIRE( "shoe_fire", 124, TileGroup.BOOT, TileGroup.ITEM ),
    SHOE_ICE( "shoe_ice", 137, TileGroup.BOOT, TileGroup.ITEM ),
    SHOE_FORCE( "shoe_force", 150, TileGroup.BOOT, TileGroup.ITEM ),
    //Temporary walls
    TOGGLE_WALL( "toggle_wall", 2, 5, 99, 99, 2, 6, 99, 99, TileGroup.ACTING_WALL ),
    BUTTON_GREEN( "button green", 42, TileGroup.BUTTON ),
    BUTTON_BLUE( "button blue", 2, 8, TileGroup.BUTTON ),
    BUTTON_BROWN( "button brown", 2, 7, TileGroup.BUTTON ),
    BUTTON_RED( "button red", 2, 4, TileGroup.BUTTON ),
    BEAR_TRAP("bear trap", 2, 11, TileGroup.ACTING_FLOOR),
    //MISQ
    BOMB( "bomb", 133, TileGroup.DESTRUCTIVE_OBSTACLE ),
    TELEPORT( "teleport", 120, TileGroup.SLIDING_TILE ),
    SPY( "spy", 16, TileGroup.MONSTER_ACTING_WALL, TileGroup.BLOCK_ACTING_WALL ),
    CLONE_MACHINE( "clone machine", 3, 1, TileGroup.ACTING_WALL ),
    //Monster
    BUG( "bug",               4,  0, 4,  1, 4,  2, 4,  3, TileGroup.MONSTER, TileGroup.OBJECT ),
    GLIDER( "glider",         5,  0, 5,  1, 5,  2, 5,  3, TileGroup.MONSTER, TileGroup.OBJECT ),
    PAEAMECIUM( "paramecium", 6,  0, 6,  1, 6,  2, 6,  3, TileGroup.MONSTER, TileGroup.OBJECT ),
    FIREBALL( "fireball",     4,  4, 4,  5, 4,  6, 4,  7, TileGroup.MONSTER, TileGroup.OBJECT ),
    TEETH( "teeth",           5,  4, 5,  5, 5,  6, 5,  7, TileGroup.MONSTER, TileGroup.OBJECT ),
    PINK_BALL( "pink ball",   4,  8, 4,  9, 4, 10, 4, 11, TileGroup.MONSTER, TileGroup.OBJECT ),
    WALKER( "walker",         5,  8, 5,  9, 5, 10, 5, 11, TileGroup.MONSTER, TileGroup.OBJECT ),
    TANK( "tank",             4, 12, 4, 13, 4, 14, 4, 15, TileGroup.MONSTER, TileGroup.OBJECT ),
    BLOB( "blob",             5, 12, 5, 13, 5, 14, 5, 15, TileGroup.MONSTER, TileGroup.OBJECT ),

    //Side Walls
    SIDE_WALL_UP(    "side wall up",    0, 6, TileGroup.SIDE_WALL ),
    SIDE_WALL_LEFT(  "side wall left",  0, 7, TileGroup.SIDE_WALL ),
    SIDE_WALL_DOWN(  "side wall down",  0, 8, TileGroup.SIDE_WALL ),
    SIDE_WALL_RIGHT( "side wall right", 0, 9, TileGroup.SIDE_WALL ),
    SIDE_WALL_SE(    "side wall se", 3, 0, TileGroup.SIDE_WALL ),

    //UNKNOWN
    UNKNOWN( "unknown", 13, TileGroup.ACTING_FLOOR );
    private int id;
    private int idLeft;
    private int idDown;
    private int idRight;
    private String name;
    private TileGroup[] types;

    private TileType( String name, int id ) {
        this.id = id;
        this.name = name;
    }

    private TileType( String name, int id, TileGroup... types ) {
        this.id = id;
        this.name = name;
        this.types = types;
    }

    private TileType( String name, int x, int y, TileGroup... types ) {
        this.id = y * 13 + x + 1;
        this.name = name;
        this.types = types;
    }

    private TileType( String name, int xUp, int yUp, int xLeft, int yLeft, int xDown, int yDown, int xRight, int yRight, TileGroup... types ) {
        this.id = yUp * 13 + xUp + 1;
        this.idLeft = yLeft * 13 + xLeft + 1;
        this.idDown = yDown * 13 + xDown + 1;
        this.idRight = yRight * 13 + xRight + 1;
        this.name = name;
        this.types = types;
    }

    public Direction getDirection( int id ) {
        if( this.id == id ) {
            return Direction.UP;
        }
        else if( idDown == id ) {
            return Direction.DOWN;
        }
        else if( idLeft == id ) {
            return Direction.LEFT;
        }
        else {
            return Direction.RIGHT;
        }
    }

    public static TileType getTypeById( int id ) {
        for( TileType type : TileType.values() ) {
            if( type.id == id
                || type.idLeft == id
                || type.idDown == id
                || type.idRight == id ) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public TileGroup[] getTileGroups() {
        return types;
    }

    public static TileType getTypeByName( String name ) {
        for( TileType type : TileType.values() ) {
            if( type.name.equals( name ) ) {
                return type;
            }
        }
        return null;
    }

    public static TileType getCorrespondingKeyFromDoor( TileType door ) {
        switch( door ) {
            case DOOR_RED:
                return KEY_RED;
            case DOOR_BLUE:
                return KEY_BLUE;
            case DOOR_GREEN:
                return KEY_GREEN;
            case DOOR_YELLOW:
                return KEY_YELLOW;
            default:
                return null;
        }

    }

    public static TileType getCorrespondingShoeFromGroundType( TileType groundType ) {
        switch( groundType ) {
            case GROUND_TYPE_FORCE_LEFT:
            case GROUND_TYPE_FORCE_RIGHT:
            case GROUND_TYPE_FORCE_UP:
            case GROUND_TYPE_FORCE_DOWN:
            case GROUND_TYPE_FORCE_RANDOM:
                return SHOE_FORCE;
            case GROUND_TYPE_WATER:
                return SHOE_WATER;
            case GROUND_TYPE_FIRE:
                return SHOE_FIRE;
            case GROUND_TYPE_ICE:
            case GROUND_TYPE_ICE_CORNER_NE:
            case GROUND_TYPE_ICE_CORNER_NW:
            case GROUND_TYPE_ICE_CORNER_SE:
            case GROUND_TYPE_ICE_CORNER_SW:
                return SHOE_ICE;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static String[] toStringArray() {
        TileType[] types = values();
        String[] stringTypes = new String[types.length];
        for( int i = 0; i < types.length; i++ ) {
            stringTypes[i] = types[i].toString();
        }
        return stringTypes;
    }
}
