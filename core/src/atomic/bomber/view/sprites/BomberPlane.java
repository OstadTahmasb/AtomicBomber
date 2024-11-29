package atomic.bomber.view.sprites;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.gdx.Gdx.*;

public class BomberPlane extends GameObject {
    public BomberPlane(World world, float x, float y) {
        super(Main.assetManager.get(Assets.PLANE), world, x, y, true, 1.5f, 0.5f, 0.02f, 0.02f);

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.setUserData(this);

        fixtureDef.filter.categoryBits = CollisionCategory.PLANE;
        fixtureDef.filter.maskBits = CollisionCategory.TARGET | CollisionCategory.BULLET | CollisionCategory.GROUND |
                                    CollisionCategory.CLUSTER_BONUS | CollisionCategory.NUKE_BONUS;
        body.createFixture(fixtureDef);
    }

    public void move() {
        VELOCITY.set((float) (SPEED * Math.cos(body.getAngle())), (float) (SPEED * Math.sin(body.getAngle())));
    }

    public void handleInput() {
        if (input.isKeyPressed(Input.Keys.W)) {
            body.setTransform(body.getPosition(), body.getAngle() + 0.01f);
            rotate((float) (0.01f / Math.PI * 180));
            flip(true, false);
        }
        if (input.isKeyPressed(Input.Keys.S)) {
            body.setTransform(body.getPosition(), body.getAngle() - 0.01f);
            rotate(-(float) (0.01f / Math.PI * 180));
            flip(true, false);
        }
        if (input.isKeyPressed(Input.Keys.A)) {
            SPEED += 0.04f;
        }
        if (input.isKeyPressed(Input.Keys.D)) {
            SPEED -= 0.04f;
        }
        if (input.isKeyJustPressed(Input.Keys.SPACE)) {
            GameMenu.addObject(new Bomb(world, getX(), getY(), this.VELOCITY.x >= 0));
            GameMenu.bombsUsed++;
        }
        if (input.isKeyJustPressed(Input.Keys.R)) {
            if (GameMenu.nuke > 0) {
                GameMenu.addObject(new Nuke(world, getX(), getY(), this.VELOCITY.x >= 0));
                GameMenu.bombsUsed++;
                GameMenu.nuke--;
            }
        }
    }
}
