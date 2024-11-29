package atomic.bomber.view.sprites;

import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.image.BufferedImageFilter;

import static atomic.bomber.Main.assetManager;
import static atomic.bomber.Assets.*;

public class BigBlast extends GameObject {
    private Animation<Texture> animation;
    private float x, y, timer;
    final static float DURATION = 0.5f;

    public BigBlast(World world, float x, float y) {
        super(assetManager.get(BIG_BLAST.get(0)), world, x, y, false, 0.5f, 0, 0, 0);

        this.animation = new Animation<>(DURATION / 4f, new Texture[] {
                assetManager.get(BIG_BLAST.get(0)),
                assetManager.get(BIG_BLAST.get(1)),
                assetManager.get(BIG_BLAST.get(2)),
                assetManager.get(BIG_BLAST.get(3))
        });
        this.x = x;
        this.y = y;
        this.timer = 0;
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
