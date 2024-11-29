package atomic.bomber.view.sprites;

import com.badlogic.gdx.physics.box2d.World;

public class BulletToBeAdded {
    public World world;
    public float x, y, targetX, targetY;

    public BulletToBeAdded(World world, float x, float y, float targetX, float targetY) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
    }
}
