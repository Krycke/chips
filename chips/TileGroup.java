/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips;

/**
 *
 * @author Ludvig
 */
public enum TileGroup {

    BOOT, // An item that Chip can use to pass or to preserve normal movement on the corresponding element. The boots are Fire boots, Ice skates, Flippers and Suction boots.
    BUTTON, // Is any of four small, circular tiles which perform four different mechanical functions. The four buttons are the red button, blue button, green button and brown button, with the color code denoting their function.
    DESTRUCTIVE_OBSTACLE, // The destructive obstacles water, fire and bombs, named because they are the only obstacles which can destroy objects.
    ELEMENT, // An element is any of the four tiles that match up with the four boots: fire, water, force floor or ice.
    ITEM, // An item is any of nine tiles which Chip can collect and store in his inventory. The nine items are the four keys, the four boots and computer chips.
    KEY, // A key is an item that Chip can use to open a lock of the same color. Each key can only be used to open one lock, after which it disappears, with the exception of the green key, which can be used an infinite number of times.
    MONSTER, // A monster is a moving object that Chip must avoid at all times. There are nine types, all with different properties.
    OBJECT, // Objects as defined in Chip's Challenge are tiles which can move, which are technically limited to Chip, monsters and blocks.
    RANDOM_ELEMENT, // A random element is a tile in Chip's Challenge in which its behavior is determined randomly (Blobs, Walkers, Random force floor).
    REMOVABLE_OBSTACLE, // A removable obstacle includes any wall or acting wall which can be removed from its current square, temporarily or permanently.
    SLIDING_TILE, // A sliding tile is any of the three types of tiles that normally make an object move at 10 m/s: force floors, ice and teleports.
    TILE, // A tile is a generic square with which Chip, monsters and blocks can interact in Chip's Challenge.
    WALL, // A wall is a tile that is impassable, immovable, and indestructible.
    ACTING_WALL, //A acting wall is a tile other than the nine types of walls, which nevertheless acts as a wall to an object.
    CHIP_ACTING_WALL, //A chip-acting wall is a tile other than the nine types of walls, which nevertheless acts as a wall to Chip.
    BLOCK_ACTING_WALL, //A block-acting wall is a tile other than the nine types of walls, which nevertheless acts as a wall to blocks.
    MONSTER_ACTING_WALL, //A monster-acting wall is a tile other than the nine types of walls, which nevertheless acts as a wall to monsters.
    ACTING_DIRT, // Acting dirt is a tile which is an acting wall to blocks and monsters, but not to Chip, and which disappears when Chip touches it. In other words, it functions exactly like a dirt space.
    ACTING_FLOOR,
    CHIP_ACTING_FLOOR,
    BLOCK_ACTING_FLOOR,
    MONSTER_ACTIONG_FLOOR,
    SIDE_WALL,
}
