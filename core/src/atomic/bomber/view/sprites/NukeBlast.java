package atomic.bomber.view.sprites;

import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


import static atomic.bomber.Main.assetManager;
import static atomic.bomber.Assets.NUKE_ANIMATION;

public class NukeBlast extends GameObject {
    private Animation<TextureRegion> animation;
    private float x, y, timer;
    private static float DURATION = 2f;

    public NukeBlast(World world, float x, float y) {
        super(assetManager.get(NUKE_ANIMATION), world, x, y, false, 0.2f, 0, 0, 0);
        this.x = x;
        this.y = y;
        this.timer = 0;
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                frames.add(new TextureRegion(assetManager.get(NUKE_ANIMATION), j * 320, i * 232, 320, 232));
            }
        }
        this.animation = new Animation<>(DURATION / 20f, frames);
    }

    @Override
    public void update(float deltaTime) {
        setRegion(animation.getKeyFrame(timer, false));
        setPosition(x, y);

        timer += deltaTime;
        if (timer >= DURATION) {
            GameMenu.removeObject(this);
        }
    }

    public void move() { this.VELOCITY.set(0, 0); }
}
