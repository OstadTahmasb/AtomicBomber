package atomic.bomber.view.sprites;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

import static com.badlogic.gdx.Gdx.graphics;

public class Tank extends GameObject {
    public Tank(World world, float x, float y) {
        super(Main.assetManager.get(Assets.TANK), world, x, y, false, 1f, 1f, 0.5f, 0);
        Random random = new Random();
        if (random.nextBoolean()) this.VELOCITY.set(-this.VELOCITY.x, this.VELOCITY.y);

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.setTransform(body.getPosition().x, GameMenu.GROUND_LEVEL + getHeight() / 2, body.getAngle());
        body.setUserData(this);

        fixtureDef.filter.categoryBits = CollisionCategory.TARGET;
        fixtureDef.filter.maskBits = CollisionCategory.BOMB | CollisionCategory.PLANE;
        body.createFixture(fixtureDef);

        this.KILL = 4;
    }

    public void move() {
        if (!isAlive) this.VELOCITY.set(0, 0);
    }
}
