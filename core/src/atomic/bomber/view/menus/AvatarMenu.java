package atomic.bomber.view.menus;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.controller.menus.AvatarController;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

public class AvatarMenu extends Menu {
    private int i;
    Table table, picturesTable;
    ArrayList<Image> profilePics;
    ArrayList<ClickListener> listeners;
    TextButton backButton, chooseFileButton;

    public AvatarMenu(Main game) {
        super(game);

        table = new Table();
        table.setFillParent(true);

        i = 0;
        profilePics = new ArrayList<>();
        listeners = new ArrayList<>();
        while (i < Assets.PFP.size()) {
            profilePics.add(new Image(Main.assetManager.get(Assets.PFP.get(i))));
            profilePics.get(i).addListener(new ImageListener(i));
            i++;
        }
        picturesTable = new Table();
        for (Image profilePic : profilePics) {
            picturesTable.add(profilePic).width(150).height(150).padLeft(10);
        }

        backButton = new TextButton("Back", game.getSkin());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backButtonClicked();
            }
        });

        chooseFileButton = new TextButton("Choose", game.getSkin());
        chooseFileButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               AvatarController.chooseProfilePicture();
           }
        });

        table.add(picturesTable).padBottom(10);
        table.row();
        table.add(chooseFileButton).padBottom(10);
        table.row();
        table.add(backButton);

        stage.addActor(table);
    }
    public void backButtonClicked() {
        setScreen(new ProfileMenu(game));
    }
}
