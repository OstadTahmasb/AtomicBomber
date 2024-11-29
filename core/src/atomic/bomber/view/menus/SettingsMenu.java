package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsMenu extends Menu {
    Table table;
    SelectBox<Integer> difficultySelectBox;
    TextButton muteButton, backButton;
    Label difficultyLevelLabel;
    public static int difficulty = 2;
    public static boolean isMute = false;

    public SettingsMenu(Main game) {
        super(game);

        table = new Table();
        table.setFillParent(true);

        difficultySelectBox = new SelectBox<>(game.getSkin());
        difficultySelectBox.setItems(1, 2, 3);
        difficultySelectBox.setSelected(difficulty);

        muteButton = new TextButton("", game.getSkin());
        if (isMute) muteButton.setText("Unmute");
        else muteButton.setText("Mute");
        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                muteButtonClicked();
            }
        });

        backButton = new TextButton("Back", game.getSkin());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backButtonClicked();
            }
        });

        difficultyLevelLabel = new Label("Difficulty:", game.getSkin());
        difficultyLevelLabel.setFontScale(2);

        table.add(difficultyLevelLabel).padBottom(10);
        table.add(difficultySelectBox).padLeft(10).padBottom(10);
        table.row();
        table.add(muteButton).colspan(2).padBottom(10);
        table.row();
        table.add(backButton).colspan(2);

        stage.addActor(table);
    }

    public void muteButtonClicked() {
        isMute = !isMute;
        if (isMute) muteButton.setText("Unmute");
        else muteButton.setText("Mute");
    }

    public void backButtonClicked() {
        difficulty = difficultySelectBox.getSelected();
        setScreen(new MainMenu(game));
    }
}
