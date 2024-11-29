package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.controller.menus.LoginController;
import atomic.bomber.view.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.Gdx.*;

public class StartMenu extends Menu {
    Table table;
    TextButton startButton, loginButton, signUpButton;
    Label title;

    public StartMenu(Main game) {
        super(game);

        table = new Table(game.getSkin());
        table.setBounds(0, 0, graphics.getWidth(), graphics.getHeight());

        startButton = new TextButton("Start", game.getSkin());
        signUpButton = new TextButton("Sign Up", game.getSkin());
        loginButton = new TextButton("Login", game.getSkin());

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startButtonClicked();
            }
        });
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loginButtonClicked();
            }
        });
        signUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                signUpButtonClicked();
            }
        });

//        table.setDebug(true);
        table.add(startButton).width(100).padBottom(10);
        table.row();
        table.add(loginButton).width(100).padBottom(10);
        table.row();
        table.add(signUpButton).width(100);


        stage.addActor(table);
    }

    @Override
    public void show() {
        super.show();
    }

    public void startButtonClicked() {
        LoginController.setCurrentUser(null);
        setScreen(new GameMenu(game));
    }

    public void loginButtonClicked() {
        setScreen(new LoginMenu(game));
    }

    public void signUpButtonClicked() {
        setScreen(new RegisterMenu(game));
    }
}
