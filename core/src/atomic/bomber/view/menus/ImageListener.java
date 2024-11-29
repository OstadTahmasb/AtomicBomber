package atomic.bomber.view.menus;

import atomic.bomber.controller.menus.AvatarController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ImageListener extends ClickListener {
    private final int index;

    public ImageListener(int index) {
        this.index = index;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        AvatarController.setProfilePicture(index);
    }
}
