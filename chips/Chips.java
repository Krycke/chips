package chips;

import it.marteEngine.*;
import java.io.File;
import java.io.IOException;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;


/**
 *
 * @author Kristoffer
 */
public class Chips extends StateBasedGame {

    public static final int GAMESTATE = 0;

    private AppGameContainer container;
    public static boolean ressourcesInited = false;

    public Chips() {
        super("Chip's Challenge");
   }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        if(container instanceof AppGameContainer) {
            this.container = (AppGameContainer) container;
        }

        GameWorld gameWorld = new GameWorld(GAMESTATE);
        addState(gameWorld);

        ME.world = gameWorld;
        Globals.world = gameWorld;
    }

    public static void initRessources() throws SlickException {
        if(ressourcesInited) {
            return;
        }
        try {
            ResourceManager.loadResources("data/resources.xml");
        }
        catch(IOException e) {
            Log.error("failed to load resource file 'data/resources.xml': "
                      + e.getMessage());
            throw new SlickException("Resource loading failed!");
        }

        ressourcesInited = true;
    }

    /**
     * @see org.newdawn.slick.state.StateBasedGame#keyPressed(int, char)
     */
    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);

        if(key == Input.KEY_F2) {
            if(container != null) {
                try {
                    container.setFullscreen(!container.isFullscreen());
                    if(container.isFullscreen()) {
                        container.setMouseGrabbed(true);
                    }
                }
                catch(SlickException e) {
                    Log.error(e);
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "src\\dll"), LWJGLUtil.getPlatformName()).getAbsolutePath());

        try {
            ME.keyToggleDebug = Input.KEY_1;
            ME.keyRestart = Input.KEY_F10;
            Chips t = new Chips();

            AppGameContainer container = new AppGameContainer(t);
//            container.setIcon( "./data/icon.png");
            container.setDisplayMode(512, 351, false);
            container.setTargetFrameRate(60);
            String[] icons = {"data/icon16.png", "data/icon24.png", "data/icon32.png" };
            container.setIcons( icons );

            container.start();
        }
        catch(SlickException e) {
            e.printStackTrace();
        }
    }
}
