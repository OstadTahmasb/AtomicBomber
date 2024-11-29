package atomic.bomber.view.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.physics.box2d.World;

import static atomic.bomber.Assets.*;
import static atomic.bomber.Main.*;
public class Fire extends GameObject {
    private Animation<Texture> animation;
    private float timer;
    private GameObject objectOnFire;
    private float x, y;

    public Fire(World world, float x, float y, GameObject objectOnFire) {
        super(assetManager.get(FIRE.get(0)), world, x, y, false, 1f, 0, 0, 0);

        this.animation = new Animation<>(GameMenu.FRAME_RATE, new Texture[] {
                assetManager.get(FIRE.get(0)),
                assetManager.get(FIRE.get(1)),
                assetManager.get(FIRE.get(2))
        });
        this.x = x;
        this.y = y;
        this.timer = 0;
        this.objectOnFire = objectOnFire;
    }

    @Override
    public void update(float deltaTime) {
        setRegion(animation.getKeyFrame(timer + deltaTime, true));
        setPosition(x, y);

        timer += deltaTime;

        if (timer >= GameMenu.FIRE_TIMER) {
            GameMenu.removeObject(this);
            GameMenu.removeBody(objectOnFire);
            GameMenu.removeObject(objectOnFire);
        }
    }

    public void move() {
        this.VELOCITY.set(0, 0);
    }
}
