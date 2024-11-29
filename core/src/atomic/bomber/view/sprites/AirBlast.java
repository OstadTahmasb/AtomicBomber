package atomic.bomber.view.sprites;

import atomic.bomber.view.menus.GameMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.World;

import static atomic.bomber.Main.assetManager;
import static atomic.bomber.Assets.*;

public class AirBlast extends GameObject {
    private Animation<Texture> animation;
    private float x, y, timer;

    public AirBlast(World world, float x, float y) {
        super(assetManager.get(AIR_BLAST.get(0)), world, x, y, false, 1f, 0, 0, 0);

        this.animation = new Animation<>(GameMenu.AIR_BLAST_TIME / 4f, new Texture[]{
                assetManager.get(AIR_BLAST.get(0)),
                assetManager.get(AIR_BLAST.get(1)),
                assetManager.get(AIR_BLAST.get(2)),
                assetManager.get(AIR_BLAST.get(3))
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

        if (timer >= GameMenu.AIR_BLAST_TIME) {
            GameMenu.removeObject(this);
        }
    }

    public void move() {
        this.VELOCITY.set(0, 0);
    }
}
