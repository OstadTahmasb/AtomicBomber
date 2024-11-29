package atomic.bomber.view.sprites;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Bunker extends GameObject {
    public Bunker(World world, float x, float y) {
        super(Main.assetManager.get(Assets.BUNKER), world, x, y, false, 1f, 0, 0, 0);

        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);
        body.setTransform(body.getPosition().x, GameMenu.GROUND_LEVEL + getHeight() / 2, body.getAngle());
        body.setUserData(this);

        fixtureDef.filter.categoryBits = CollisionCategory.TARGET;
        fixtureDef.filter.maskBits = CollisionCategory.BOMB | CollisionCategory.PLANE;
        body.createFixture(fixtureDef);

        this.KILL = 2;
    }

    public void move() {}
}
