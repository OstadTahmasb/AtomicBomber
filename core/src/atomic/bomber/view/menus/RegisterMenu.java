package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.controller.ControllerResponse;
import atomic.bomber.controller.menus.RegisterController;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.Gdx.*;

public class RegisterMenu extends Menu {
    float SCALE = 2;
    Table table;
    Label usernameLabel, passwordLabel, confirmPasswordLabel, errorLabel;
    TextField usernameField, passwordField, confirmPasswordField;
    TextButton submitButton, backButton;

    public RegisterMenu(Main game) {
        super(game);

        table = new Table();
        table.setBounds(0, 0, graphics.getWidth(), graphics.getHeight());

        errorLabel = new Label("", game.getSkin());
        errorLabel.setFontScale(SCALE);

        usernameLabel = new Label("Username:", game.getSkin());
        usernameLabel.setFontScale(SCALE);
        usernameField = new TextField("", game.getSkin());
        passwordLabel = new Label("Password:", game.getSkin());
        passwordLabel.setFontScale(SCALE);
        passwordField = new TextField("", game.getSkin());
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        confirmPasswordLabel = new Label("Confirm Password:", game.getSkin());
        confirmPasswordLabel.setFontScale(SCALE);
        confirmPasswordField = new TextField("", game.getSkin());
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        submitButton = new TextButton("Register", game.getSkin());
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

        table.add(errorLabel).padBottom(10);
        table.row();
        table.add(usernameLabel).padBottom(10);
        table.row();
        table.add(usernameField).padBottom(10);
        table.row();
        table.add(passwordLabel).padBottom(10);
        table.row();
        table.add(passwordField).padBottom(10);
        table.row();
        table.add(confirmPasswordLabel).padBottom(10);
        table.row();
        table.add(confirmPasswordField).padBottom(15);
        table.row();
        table.add(submitButton).padBottom(10);
        table.row();
        table.add(backButton);

        stage.addActor(table);
    }

    public void submitButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        ControllerResponse response = RegisterController.register(username, password, confirmPassword);

        errorLabel.setText(response.getError());
        if (response.isFail()) {
            errorLabel.setColor(Color.RED);
        } else {
            errorLabel.setColor(Color.GREEN);
        }

    }

    public void backButtonClicked() {
        setScreen(new StartMenu(game));
    }

    public void setErrorLabel(String error, boolean isFail) {
        errorLabel.setText(error);
        if (isFail) {
            errorLabel.setColor(250, 0, 0, 0);
        }
    }
}
