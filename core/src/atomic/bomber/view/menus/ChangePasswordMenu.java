package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.controller.ControllerResponse;
import atomic.bomber.controller.menus.ProfileController;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ChangePasswordMenu extends Menu  {
    float SCALE = 2;
    Table table;
    Label errorLabel, newPasswordLabel, confirmPasswordLabel, oldPasswordLabel;
    TextField newPasswordField, oldPasswordField, confirmPasswordField;
    TextButton submitButton, backButton;

    public ChangePasswordMenu(Main game) {
        super(game);

        table = new Table();
        table.setFillParent(true);

        errorLabel = new Label("", game.getSkin());
        errorLabel.setFontScale(SCALE);
        oldPasswordLabel = new Label("Old Password:", game.getSkin());
        oldPasswordLabel.setFontScale(SCALE);
        oldPasswordField = new TextField("", game.getSkin());
        oldPasswordField.setPasswordMode(true);
        oldPasswordField.setPasswordCharacter('*');
        newPasswordLabel = new Label("New Password:", game.getSkin());
        newPasswordLabel.setFontScale(SCALE);
        newPasswordField = new TextField("", game.getSkin());
        newPasswordField.setPasswordMode(true);
        newPasswordField.setPasswordCharacter('*');
        confirmPasswordLabel = new Label("Confirm Password", game.getSkin());
        confirmPasswordLabel.setFontScale(SCALE);
        confirmPasswordField = new TextField("", game.getSkin());
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        submitButton = new TextButton("Change", game.getSkin());
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
        table.add(oldPasswordLabel).padBottom(10);
        table.row();
        table.add(oldPasswordField).padBottom(10);
        table.row();
        table.add(newPasswordLabel).padBottom(10);
        table.row();
        table.add(newPasswordField).padBottom(10);
        table.row();
        table.add(confirmPasswordLabel).padBottom(10);
        table.row();
        table.add(confirmPasswordField).padBottom(20);
        table.row();
        table.add(submitButton).width(100).padBottom(10);
        table.row();
        table.add(backButton).width(100).padBottom(10);

        stage.addActor(table);
    }

    public void submitButtonClicked() {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        ControllerResponse response = ProfileController.changePassword(oldPassword, newPassword, confirmPassword);
        errorLabel.setText(response.getError());
        if (response.isFail()) errorLabel.setColor(Color.RED);
        else errorLabel.setColor(Color.GREEN);
    }

    public void backButtonClicked() {
        setScreen(new ProfileMenu(game));
    }
}
