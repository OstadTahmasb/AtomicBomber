package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.controller.menus.AvatarController;
import atomic.bomber.controller.menus.LoginController;
import atomic.bomber.controller.menus.ProfileController;
import atomic.bomber.model.User;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.Gdx.*;

public class MainMenu extends Menu {
    Table table;
    TextButton newGameButton, continueGameButton, profileMenuButton, rankingsMenuButton, settingsButton, exitGameButton;
    Image pfp;
    Label usernameLabel;

    public MainMenu(Main game) {
        super(game);

        table = new Table();
        table.setBounds(0, 0, graphics.getWidth(), graphics.getHeight());

        pfp = new Image(AvatarController.getProfilePicture());
        usernameLabel = new Label(LoginController.getCurrentUser().getUsername(), game.getSkin());
        usernameLabel.setFontScale(2);

        newGameButton = new TextButton("New Game", game.getSkin());
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                newGameButtonCLicked();
            }
        });

        continueGameButton = new TextButton("Continue Game", game.getSkin());
        continueGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                continueGameButtonClicked();
            }
        });

        profileMenuButton = new TextButton("Profile Menu", game.getSkin());
        profileMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                profileMenuButtonClicked();
            }
        });

        rankingsMenuButton = new TextButton("Ranking", game.getSkin());
        rankingsMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rankingsMenuButtonClicked();
            }
        });

        settingsButton = new TextButton("Settings", game.getSkin());
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsButtonClicked();
            }
        });

        exitGameButton = new TextButton("Exit", game.getSkin());
        exitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitGameButtonClicked();
            }
        });

        table.add(pfp).width(150).height(150).padBottom(10);
        table.row();
        table.add(usernameLabel).padBottom(10);
        table.row();
        table.add(newGameButton).padBottom(10).width(200);
        table.row();
        table.add(continueGameButton).padBottom(10).width(200);
        table.row();
        table.add(profileMenuButton).padBottom(10).width(200);
        table.row();
        table.add(rankingsMenuButton).padBottom(10).width(200);
        table.row();
        table.add(settingsButton).padBottom(10).width(200);
        table.row();
        table.add(exitGameButton).padBottom(10).width(200);

        stage.addActor(table);
    }

    public void newGameButtonCLicked() {
        setScreen(new GameMenu(game));
    }

    public void continueGameButtonClicked() {
        setScreen(new GameMenu(game));
    }

    public void profileMenuButtonClicked() {
        setScreen(new ProfileMenu(game));
    }

    public void rankingsMenuButtonClicked() {
        setScreen(new RankingsMenu(game));
    }

    public void settingsButtonClicked() {
        setScreen(new SettingsMenu(game));
    }

    public void exitGameButtonClicked() {
        app.exit();
    }
}
