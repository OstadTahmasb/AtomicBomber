package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.controller.ControllerResponse;
import atomic.bomber.controller.menus.ProfileController;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ChangeUsernameMenu extends Menu {
    float SCALE = 2;
    Table table;
    Label errorLabel, usernameLabel;
    TextButton backButton, submitButton;
    TextField usernameField;

    public ChangeUsernameMenu(Main game) {
        super(game);

        table = new Table();
        table.setFillParent(true);

        errorLabel = new Label("", game.getSkin());
        errorLabel.setFontScale(SCALE);
        usernameLabel = new Label("New Username:", game.getSkin());
        usernameLabel.setFontScale(SCALE);
        usernameField = new TextField("", game.getSkin());

        backButton = new TextButton("Back", game.getSkin());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backButtonClicked();
            }
        });

        submitButton = new TextButton("Change", game.getSkin());
        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                submitButtonClicked();
            }
        });

        table.add(errorLabel).padBottom(10);
        table.row();
        table.add(usernameLabel).padBottom(10);
        table.row();
        table.add(usernameField).padBottom(20);
        table.row();
        table.add(submitButton).width(100).padBottom(10);
        table.row();
        table.add(backButton).width(100);

        stage.addActor(table);
    }

    public void backButtonClicked() {
        setScreen(new ProfileMenu(game));
    }

    public void submitButtonClicked() {
        String username = usernameField.getText();

        ControllerResponse response = ProfileController.changeUsername(username);
        errorLabel.setText(response.getError());
        if (response.isFail()) errorLabel.setColor(Color.RED);
        else errorLabel.setColor(Color.GREEN);
    }
}
