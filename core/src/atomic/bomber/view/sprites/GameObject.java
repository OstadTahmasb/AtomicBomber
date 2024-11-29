package atomic.bomber.view.sprites;

import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.gdx.Gdx.graphics;

public abstract class GameObject extends Sprite {
    public World world;
    public Body body;
    public BodyDef bodyDef;
    protected TextureRegion graphic;
    protected float SCALE;
    protected float SPEED;
    protected Vector2 VELOCITY;
    protected FixtureDef fixtureDef;
    private final boolean canRotate;
    protected boolean isAlive;
    public int KILL = 0;

    public GameObject(Texture graphicInput, World world, float x, float y, boolean canRotate,
                      float SCALE, float SPEED, float xVelocity, float yVelocity) {
        this.world = world;
        this.SCALE = SCALE;
        this.SPEED = SPEED;
        this.VELOCITY = new Vector2(xVelocity, yVelocity);
        this.graphic = new TextureRegion(graphicInput);
        this.canRotate = canRotate;
        this.isAlive = true;

        this.bodyDef = new BodyDef();
        bodyDef.position.set(x, y);

        setBounds(0, 0, graphic.getRegionWidth() / GameMenu.PPM , graphic.getRegionHeight() / GameMenu.PPM);
        setRegion(graphic);
        setScale(SCALE);

        fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 * SCALE, getHeight() / 2 * SCALE);
        fixtureDef.shape = shape;
    }

    public void update(float deltaTime) {
        if (Math.abs(body.getPosition().x) > graphics.getWidth() * GameMenu.camera.zoom / 2f / GameMenu.PPM) {
            body.setTransform(-body.getPosition().x, body.getPosition().y, body.getAngle());
        }
        if (body.getPosition().y > graphics.getHeight() * GameMenu.camera.zoom / 2f / GameMenu.PPM) {
            this.VELOCITY.set(this.VELOCITY.x, -this.VELOCITY.y);
            body.setTransform(body.getPosition(), -body.getAngle());
            rotate((float) (2 * body.getAngle() / Math.PI * 180));
        }

        if (VELOCITY.x >= 0) {
            graphic.flip(graphic.isFlipX(), graphic.isFlipY());
        } else {
            graphic.flip(!graphic.isFlipX(), graphic.isFlipY());
        }

        setRegion(graphic);
        setScale(SCALE);

        move();

        if (canRotate) {
            double alpha = Math.atan(getHeight() / getWidth());
            double theta = body.getAngle();
            double yOffset = Math.sin(alpha + theta) * getHeight() * SCALE / 2f / Math.sin(alpha);
            double xOffset = Math.cos(alpha + theta) * getWidth() * SCALE / 2f / Math.cos(alpha);
            setPosition(body.getPosition().x - (float) xOffset, body.getPosition().y - (float) yOffset);
        } else {
            setPosition(body.getPosition().x - (getWidth() * SCALE / 2f), body.getPosition().y - (getHeight() * SCALE / 2f));
        }
        body.setLinearVelocity(VELOCITY);
    }

    public abstract void move();

    public void die() {
        this.isAlive = false;
        this.VELOCITY.set(0, 0);
    }

    public boolean isDead() {
        return !this.isAlive;
    }
}
