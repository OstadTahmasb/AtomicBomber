package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.controller.menus.ProfileController;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.*;

import static com.badlogic.gdx.Gdx.*;

public class ProfileMenu extends Menu {
    Table table;
    TextButton changeUsernameButton, changePasswordButton, deleteAccountButton, logoutButton, avatarMenuButton, backButton;
    public ProfileMenu(Main game) {
        super(game);

        table = new Table();
        table.setBounds(0, 0, graphics.getWidth(), graphics.getHeight());

        changeUsernameButton = new TextButton("Change Username", game.getSkin());
        changeUsernameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeUsernameButtonClicked();
            }
        });

        changePasswordButton = new TextButton("Change Password", game.getSkin());
        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changePasswordButtonClicked();
            }
        });

        deleteAccountButton = new TextButton("Delete Account", game.getSkin());
        deleteAccountButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                deleteAccountButtonClicked();
            }
        });

        logoutButton = new TextButton("Logout", game.getSkin());
        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logoutButtonClicked();
            }
        });

        avatarMenuButton = new TextButton("Avatar Menu", game.getSkin());
        avatarMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                avatarMenuButtonClicked();
            }
        });

        backButton = new TextButton("Back", game.getSkin());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backButtonClicked();
            }
        });

        table = new Table();
        table.setFillParent(true);

        table.add(changeUsernameButton).width(200).padBottom(10);
        table.row();
        table.add(changePasswordButton).width(200).padBottom(10);
        table.row();
        table.add(avatarMenuButton).width(200).padBottom(10);
        table.row();
        table.add(logoutButton).width(200).padBottom(10);
        table.row();
        table.add(deleteAccountButton).width(200).padBottom(10);
        table.row();
        table.add(backButton).width(200).padBottom(10);

        stage.addActor(table);
    }

    public void changeUsernameButtonClicked() {
        setScreen(new ChangeUsernameMenu(game));
    }

    public void changePasswordButtonClicked() {
        setScreen(new ChangePasswordMenu(game));
    }

    public void deleteAccountButtonClicked() {
        ProfileController.deleteAccount();
        setScreen(new RegisterMenu(game));
    }

    public void logoutButtonClicked() {
        setScreen(new LoginMenu(game));
    }

    public void avatarMenuButtonClicked() {
        setScreen(new AvatarMenu(game));
    }

    public void backButtonClicked() { setScreen(new MainMenu(game)); }
}
