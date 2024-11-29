package atomic.bomber.view.sprites;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class ClusterBonus extends GameObject {
    public ClusterBonus(World world, float x, float y) {
        super(Main.assetManager.get(Assets.CLUSTER_BONUS), world, x, y, false, 1f, 1f, 0f, 0.3f);

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.setUserData(this);

        fixtureDef.filter.categoryBits = CollisionCategory.CLUSTER_BONUS;
        fixtureDef.filter.maskBits = CollisionCategory.PLANE;
        body.createFixture(fixtureDef);
    }

    public void move() {}

    @Override
    public void die() {
        super.die();
        GameMenu.cluster++;
    }
}
