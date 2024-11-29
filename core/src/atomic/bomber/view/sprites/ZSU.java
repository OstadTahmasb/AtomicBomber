package atomic.bomber.view.sprites;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.physics.box2d.World;

public class ZSU extends Attacker {
    final static float ATTACK_RADIUS = 0.5f;

    public ZSU(World world, float x, int DIFFICULTY) {
        super(Main.assetManager.get(Assets.ZSU), world, x, (float) 0, false, 1f, 1f * DIFFICULTY, 0.5f, (float) 0,
                ATTACK_RADIUS * DIFFICULTY, CollisionCategory.TARGET, (short) (CollisionCategory.PLANE | CollisionCategory.BOMB),
                CollisionCategory.TARGET, CollisionCategory.PLANE);
        body.setTransform(body.getPosition().x, GameMenu.GROUND_LEVEL + getHeight() / 2, body.getAngle());

        this.KILL = 5;
    }

    public void move() {}

    public void doAction(GameObject obj) {
        GameMenu.addBullet(new BulletToBeAdded(world, getX(), getY(), obj.getX(), obj.getY()));
    }
}
