package atomic.bomber.view.sprites;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Bullet extends GameObject {
    private float x, y, targetX, targetY;

    public Bullet(BulletToBeAdded blt) {
        super(Main.assetManager.get(Assets.BULLET), blt.world, blt.x, blt.y, true, 1f,
                (float) (1 / Math.sqrt(Math.pow(blt.targetX - blt.x, 2) + Math.pow(blt.targetY - blt.y, 2))),
                blt.targetX - blt.x, blt.targetY - blt.y);
        this.x = blt.x;
        this.y = blt.y;
        this.targetX = blt.targetX;
        this.targetY = blt.targetY;

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.setUserData(this);

        fixtureDef.filter.categoryBits = CollisionCategory.BULLET;
        fixtureDef.filter.maskBits = CollisionCategory.PLANE;
        body.createFixture(fixtureDef);

        float theta = (float) Math.atan((targetX - x) / (targetY - y));
        body.setTransform(body.getPosition(), body.getAngle() + theta);
        rotate((float) (theta / Math.PI * 180));
    }
    
    public void move() {
        if (getY() >= (Gdx.graphics.getHeight() * GameMenu.camera.zoom / 2 / GameMenu.PPM) - 0.1f) {
            die();
            GameMenu.removeObject(this);
            GameMenu.removeBody(this);
        }
    }
}
