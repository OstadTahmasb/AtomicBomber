package atomic.bomber.view.sprites;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Nuke extends Attacker {
    private ArrayList<GameObject> objectsHit;

    public Nuke(World world, float x, float y, boolean direction) {
        super(Main.assetManager.get(Assets.NUKE_BOMB), world, x, y, false, 1f, 1f, 1f, 0,
                1f, CollisionCategory.BOMB, CollisionCategory.GROUND, CollisionCategory.BOMB,
                (short) (CollisionCategory.TARGET | CollisionCategory.GROUND));
        if (!direction) this.VELOCITY.set(-this.VELOCITY.x, this.VELOCITY.y);
        this.objectsHit = new ArrayList<>();
    }

    public void move() {
        this.VELOCITY.add(0, -0.02f);
    }

    @Override
    public void doAction(GameObject obj) {
        if ((obj.body.getFixtureList().get(0).getFilterData().categoryBits & CollisionCategory.TARGET) != 0) objectsHit.add(obj);
    }

    public void die() {
        super.die();
        for (GameObject obj : this.objectsHit) {
            GameMenu.setFireAnimation(obj);
        }
    }
}
