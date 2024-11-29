package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.model.User;
import atomic.bomber.view.Menu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.badlogic.gdx.Gdx.*;

public class RankingsMenu extends Menu {
    private ArrayList<User> users;
    private Table table;
    private Window[] windows;

    public RankingsMenu(Main game) {
        super(game);

        users = User.getUsers();
        sortByKill();
        int rank = 0;

        tableInit();

    }

    public void tableInit() {
        stage.clear();
        table = new Table();
        table.setBounds(0, 0, graphics.getWidth(), graphics.getHeight());
        windows = new Window[10];
        Window.WindowStyle windowStyle = new Window.WindowStyle(new BitmapFont(), Color.WHITE, game.getSkin().getDrawable("window-c"));
        for (int i = 0; i < Math.min(10, users.size()); i++) {
            User userI = users.get(i);
            windows[i] = new Window("", windowStyle);
            table.add(windows[i]).width(graphics.getWidth());
            Table innerTable = new Table();
            innerTable.align(Align.left);
            innerTable.add(new Label(i + 1 + " ", game.getSkin())).width(50);
            innerTable.add(new Label(userI.getUsername(), game.getSkin())).width(100);
            innerTable.add(new Label("Kill: " + userI.getKill(), game.getSkin())).width(100);
            innerTable.add(new Label("Wave: " + userI.getWave(), game.getSkin())).width(100);
            innerTable.add(new Label("Difficulty: " + userI.getDifficulty(), game.getSkin())).width(100);
            innerTable.add(new Label("Accuracy: " + userI.getAccuracy(), game.getSkin())).width(100);
            windows[i].add(innerTable);
            table.row();
            table.add(new UIBox(Color.BLACK)).width(graphics.getWidth() * 0.9f).height(2);
            table.row();
        }
        if (windows[0] != null)
            windows[0].setColor(new Color(0xFFB500FF));
        if (windows[1] != null)
            windows[1].setColor(new Color(0x979797FF));
        if (windows[2] != null)
            windows[2].setColor(new Color(0xBC4A00FF));

        Table lowerTable = new Table();
        lowerTable.setWidth(graphics.getWidth());
        TextButton backButton = new TextButton("Back", game.getSkin());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backButtonCLicked();
            }
        });
        TextButton sortByKillButton = new TextButton("Sort Kill", game.getSkin());
        sortByKillButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortByKill();
            }
        });
        TextButton sortByAccuracyButton = new TextButton("Sort Accuracy", game.getSkin());
        sortByAccuracyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortByAccuracy();
            }
        });
        TextButton sortByDifficultyButton = new TextButton("Sort Difficulty", game.getSkin());
        sortByDifficultyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortByDifficulty();
            }
        });

        lowerTable.add(sortByKillButton);
        lowerTable.add(sortByAccuracyButton);
        lowerTable.add(sortByDifficultyButton);
        lowerTable.add(backButton);

        lowerTable.setPosition(0, 50);
        stage.addActor(lowerTable);
        stage.addActor(table);
    }

    public void sortByKill() {
        Collections.sort(users, Comparator.comparingInt(User::getKill));
        Collections.reverse(users);
        tableInit();

    }

    public void sortByAccuracy() {
        Collections.sort(users, Comparator.comparingDouble(User::getAccuracy));
        Collections.reverse(users);
        tableInit();

    }

    public void sortByDifficulty() {
        Collections.sort(users, Comparator.comparingInt(User::getDifficulty));
        Collections.reverse(users);
        tableInit();

    }

    private void backButtonCLicked() {
        setScreen(new MainMenu(game));
    }
}