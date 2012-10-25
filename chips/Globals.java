/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chips;

import chips.tiles.*;
import java.util.*;

/**
 *
 * @author Kristoffer
 */
public class Globals {
    public static GameWorld world;
    public static LevelMap map;
    public static Player player;

    public static int lvl;
    public static int chipsLeft;
    public static int timeLimit;
    public static String hint;
    public static long lvlStartTime;

    public static boolean playSounds = true;
    public static boolean pause = true;
    public static boolean win   = false;

    /* Dead variable */
    public static boolean dead = false;
    public static String deathMessage = "";

    public static ArrayList<TileType> inventory = new ArrayList<>();

    //Keeps track of all teleports in the game
    public static LinkedList<Teleport> teleports = new LinkedList<>();
    //Keeps track of all "toggelable walls" in the game
    public static ArrayList<ToggleWall> toggleWalls = new ArrayList<>();

    public static boolean goToNextLvl = false;

    public final static int TILEWIDTH = 32;
    public final static int TILEHEIGHT = 32;

    public final static int TILESX = 9;
    public final static int TILESY = 9;

    public static void die(String message) {
        dead = true;
        deathMessage = message;
    }

    public static void reset(){
        dead = false;
        inventory.clear();
        lvlStartTime = 0;
        teleports.clear();
        toggleWalls.clear();
    }

}
