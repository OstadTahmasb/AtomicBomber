package atomic.bomber.view.sprites;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Gdx.graphics;

public class Mig extends Attacker {
    final static float HEIGHT = graphics.getHeight() * GameMenu.camera.zoom / 2 / GameMenu.PPM - 0.5f;
    final static float ATTACK_RADIUS = 0.5f;

    public Mig(World world, float x, int DIFFICULTY) {
        super(Main.assetManager.get(Assets.MIG), world, x, HEIGHT, false, 1f, 1f, 0.5f, 0,
                ATTACK_RADIUS * DIFFICULTY, CollisionCategory.TARGET, CollisionCategory.PLANE,
                CollisionCategory.TARGET, CollisionCategory.PLANE);
        this.KILL = 6;
    }

    @Override
    public void update(float deltaTime) {
        if (body.getPosition().x > graphics.getWidth() * GameMenu.camera.zoom * 3f / GameMenu.PPM / GameMenu.DIFFICULTY) {
            body.setTransform(-graphics.getWidth() * GameMenu.camera.zoom / 2f / GameMenu.PPM, body.getPosition().y, body.getAngle());
        }

        setPosition(body.getPosition().x - (getWidth() * SCALE / 2f), body.getPosition().y - (getHeight() * SCALE / 2f));
        body.setLinearVelocity(VELOCITY);
    }

    public void move() {}

    public void doAction(GameObject obj) {
        GameMenu.addBullet(new BulletToBeAdded(world, getX(), getY(), obj.getX(), obj.getY()));
    }
}
