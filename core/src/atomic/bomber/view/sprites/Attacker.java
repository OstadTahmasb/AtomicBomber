package atomic.bomber.view.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Attacker extends GameObject {
    public Attacker(Texture graphic, World world, float x, float y, boolean canRotate, float SCALE, float SPEED,
                    float xVelocity, float yVelocity, float attackRadius, short category1, short mask1, short category2, short mask2) {
        super(graphic, world, x, y, canRotate, SCALE, SPEED, xVelocity, yVelocity);

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.setUserData(this);

        fixtureDef.filter.categoryBits = category1;
        fixtureDef.filter.maskBits = mask1;
        body.createFixture(fixtureDef);

        FixtureDef targetCircle = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(attackRadius);
        circleShape.setPosition(new Vector2(0, 0));
        targetCircle.shape = circleShape;
        targetCircle.isSensor = true;
        targetCircle.filter.categoryBits = category2;
        targetCircle.filter.maskBits = mask2;
        body.createFixture(targetCircle).setUserData("Range");
    }

    public abstract void move();

    public abstract void doAction(GameObject obj);
}
