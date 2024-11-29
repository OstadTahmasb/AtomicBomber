package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.controller.ControllerResponse;
import atomic.bomber.controller.menus.LoginController;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.Gdx.*;

public class LoginMenu extends Menu {
    float SCALE = 2;
    Table table;
    Label usernameLabel, passwordLabel, errorLabel;
    TextButton submitButton, backButton;
    TextField usernameText, passwordText;
    CheckBox rememberLogin;

    public LoginMenu(Main game) {
        super(game);

        table = new Table();
        table.setBounds(0, 0, graphics.getWidth(), graphics.getHeight());

        errorLabel = new Label("", game.getSkin());
        errorLabel.setFontScale(SCALE);

        usernameLabel = new Label("username:", game.getSkin());
        usernameLabel.setFontScale(SCALE);
        usernameLabel.setHeight(usernameLabel.getHeight() * SCALE);
        usernameLabel.setWidth(usernameLabel.getWidth() * SCALE);
        usernameText = new TextField("", game.getSkin());
        passwordLabel = new Label("password:", game.getSkin());
        passwordLabel.setFontScale(SCALE);
        passwordText = new TextField("", game.getSkin());
        passwordText.setPasswordMode(true);
        passwordText.setPasswordCharacter('*');

        submitButton = new TextButton("Login", game.getSkin());
        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                submitButtonClicked();
            }
        });

        backButton = new TextButton("Back", game.getSkin());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backButtonClicked();
            }
        });

        rememberLogin = new CheckBox("Remember Me", game.getSkin());

        table.add(errorLabel).padBottom(10);
        table.row();
        table.add(usernameLabel).padBottom(10);
        table.row();
        table.add(usernameText).padBottom(10);
        table.row();
        table.add(passwordLabel).padBottom(10);
        table.row();
        table.add(passwordText).padBottom(10);
        table.row();
        table.add(rememberLogin).padBottom(15);
        table.row();
        table.add(submitButton).padBottom(10);
        table.row();
        table.add(backButton);

        stage.addActor(table);
    }

    public void submitButtonClicked() {
        String username = usernameText.getText();
        String password = passwordText.getText();
        ControllerResponse response = LoginController.login(username, password);

        if (response.isFail()) {
            errorLabel.setText(response.getError());
            errorLabel.setColor(Color.RED);
        } else {
            setScreen(new MainMenu(game));
        }
    }

    public void backButtonClicked() {
        setScreen(new StartMenu(game));
    }
}
