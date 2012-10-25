/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chips.tiles;

import chips.Globals;
import chips.TileGroup;
import chips.TileType;
import org.newdawn.slick.Image;

/**
 *
 * @author Ludvig
 */
public class Teleport extends Tile {

    public Teleport( float x, float y, TileType tileType, Image image, int depth, TileGroup[] types ) {
        super( x, y, tileType, image, depth, types );
    }

    public Teleport getNextTeleport() {
        int currentIndex = Globals.teleports.indexOf(this);
        if (currentIndex == Globals.teleports.size() - 1) {
            return Globals.teleports.getFirst();
        }
        return Globals.teleports.get(currentIndex + 1);

    }

    public static void addTeleportToList(Teleport teleport) {
        /*
         * Start looking at lower right corner in a backwards reading order for
         * all teleports. The teleport in the lowest right corner will be the
         * first in the final list, and the teleport in the top left corner will
         * be the last in the lsit.
         */
        if (Globals.teleports.isEmpty()) {
            Globals.teleports.add(teleport);
        } else {
            float teleportPosY = teleport.getY();

            float teleportTempPosY;
            int indexToAddTeleport = 0;

            for (int i = 0; i < Globals.teleports.size(); i++) {
                Teleport teleportTemp = Globals.teleports.get(i);

                teleportTempPosY = teleportTemp.getY();

                if (teleportPosY < teleportTempPosY) {
                    indexToAddTeleport = i + 1;
                }
                if (teleportPosY == teleportTempPosY) {
                    indexToAddTeleport = i;
                    break;

                }
            }
            Globals.teleports.add(indexToAddTeleport, teleport);

        }
    }
}
