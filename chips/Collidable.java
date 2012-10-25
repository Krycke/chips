package chips;

import it.marteEngine.entity.Entity;

public class Collidable extends Entity{
	public static final String NAME = "STATIC_COLLISION";

    public Collidable(float x, float y, int width, int height) {
        super(x, y, width, height);

		// set id
		name = NAME;

		// define collision box and type
		setHitBox(0, 0, width, height);

		addType(NAME, SOLID);

	}

}

