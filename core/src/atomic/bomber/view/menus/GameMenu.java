package atomic.bomber.view.menus;

import atomic.bomber.Main;
import atomic.bomber.controller.menus.LoginController;
import atomic.bomber.model.User;
import atomic.bomber.view.CollisionCategory;
import atomic.bomber.view.Menu;
import atomic.bomber.view.WorldContactListener;
import atomic.bomber.view.sprites.*;
import atomic.bomber.view.sprites.Tree;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.Gdx.*;

public class GameMenu extends Menu {
    public static final float PPM = 100, FRAME_RATE = 0.1f, FIRE_TIMER = 2f, AIR_BLAST_TIME = 1f;
    public static float GROUND_LEVEL;
    Random random;
    Viewport viewPort;
    public static OrthographicCamera camera;
    SpriteBatch batch;
    Box2DDebugRenderer b2dr;
    private BomberPlane plane;
    Table pauseTable, dataTable, endWaveTable, endGameTable;
    Window pauseWindow, endWaveWindow, endGameWindow;
    TextButton saveButton, exitButton, muteButton, nextWaveButton, goToMainMenuButton;
    Label killTitleLabel, killNumberLabel, nukeTitleLabel, nukeNumberLabel, hpTitleLabel, hpNumberLabel, winStatusLabel,
            clusterTitleLabel, clusterNumberLabel, waveTitleLabel,  waveNumberLabel, accuracyTitleLabel, accuracyNumberLabel;
    int kill, wave, hp;
    boolean isPaused;
    public static int bombsUsed, nuke, cluster, DIFFICULTY = 2;

    public static World world = new World(new Vector2(0, 0), true);
    private static final ArrayList<GameObject> objects = new ArrayList<>();
    private static final ArrayList<Body> bodiesToBeRemoved = new ArrayList<>();
    private static final ArrayList<GameObject> objectsToBeRemoved = new ArrayList<>();
    private static final ArrayList<BulletToBeAdded> bulletsToBeAdded = new ArrayList<>();
    private static final ArrayList<Vector2> nukeBonusToBeAdded = new ArrayList<>();
    private static final ArrayList<Vector2> clusterBonusToBeAdded = new ArrayList<>();

    public GameMenu(Main game) {
        super(game);

        bombsUsed = 0;
        nuke = 2;
        cluster = 0;
        hp = 2;
        isPaused = false;
        random = new Random();
        camera = new OrthographicCamera();
        camera.zoom = 0.5f;
//        viewPort = new FitViewport(graphics.getWidth() / GameMenu.PPM, graphics.getHeight() / GameMenu.PPM, camera);
        viewPort = new ScreenViewport(camera);
        batch = new SpriteBatch();

        b2dr = new Box2DDebugRenderer();
        world.setContactListener(new WorldContactListener());

        resize(graphics.getWidth(), graphics.getHeight());

        // Making the ground
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0, GameMenu.GROUND_LEVEL);
        ChainShape straightLine = new ChainShape();
        straightLine.createChain(new float[] {-500, 0, 500, 0});
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = straightLine;
        fixtureDef.filter.categoryBits = CollisionCategory.GROUND;
        fixtureDef.filter.maskBits = CollisionCategory.BOMB | CollisionCategory.PLANE;
        Body groundBody = world.createBody(groundBodyDef);
        groundBody.createFixture(fixtureDef);

        pauseTable = new Table();
        saveButton = new TextButton("Save", game.getSkin());
        exitButton = new TextButton("Exit", game.getSkin());
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pauseMenuExitButtonClicked();
            }
        });
        muteButton = new TextButton("Mute", game.getSkin());
        pauseTable.add(muteButton).padBottom(10);
        pauseTable.row();
        pauseTable.add(saveButton).padBottom(10);
        pauseTable.row();
        pauseTable.add(exitButton).padBottom(10);
        pauseWindow.add(pauseTable);

        killTitleLabel = new Label("Kills:", game.getSkin());
        killNumberLabel = new Label("", game.getSkin());
        nukeTitleLabel = new Label("Nukes:", game.getSkin());
        nukeNumberLabel = new Label("", game.getSkin());
        clusterTitleLabel = new Label("Clusters:", game.getSkin());
        clusterNumberLabel = new Label("", game.getSkin());
        waveTitleLabel = new Label("Wave:", game.getSkin());
        waveNumberLabel = new Label("", game.getSkin());
        hpTitleLabel = new Label("HP:", game.getSkin());
        hpNumberLabel = new Label("", game.getSkin());
        accuracyTitleLabel = new Label("Accuracy:", game.getSkin());
        accuracyTitleLabel.setFontScale(2);
        accuracyNumberLabel = new Label("0%", game.getSkin());
        accuracyNumberLabel.setFontScale(2);

        dataTable = new Table();
        dataTable.add(killTitleLabel);
        dataTable.add(killNumberLabel).padLeft(10);
        dataTable.row();
        dataTable.add(nukeTitleLabel);
        dataTable.add(nukeNumberLabel).padLeft(10);
        dataTable.row();
        dataTable.add(clusterTitleLabel);
        dataTable.add(clusterNumberLabel).padLeft(10);
        dataTable.row();
        dataTable.add(waveTitleLabel);
        dataTable.add(waveNumberLabel).padLeft(10);
        dataTable.row();
        dataTable.add(hpTitleLabel);
        dataTable.add(hpNumberLabel).padLeft(10);
        dataTable.setPosition(50, graphics.getHeight() - 40);
        stage.addActor(dataTable);

        nextWaveButton = new TextButton("Next Wave", game.getSkin());
        nextWaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextWave();
            }
        });
        endWaveTable = new Table();
        endWaveTable.setFillParent(true);
//        endWaveTable.add(waveTitleLabel);
//        endWaveTable.add(waveNumberLabel).padLeft(10);
//        endWaveTable.row();
//        endWaveTable.add(killTitleLabel);
//        endWaveTable.add(killNumberLabel).padLeft(10);
//        endWaveTable.row();
        endWaveTable.add(accuracyTitleLabel).padBottom(10);
        endWaveTable.add(accuracyNumberLabel).padLeft(10);
        endWaveTable.row();
        endWaveTable.add(nextWaveButton).colspan(2);
        endWaveWindow.addActor(endWaveTable);

        endGameTable = new Table();
        endGameWindow = new Window("End Game", game.getSkin());
        goToMainMenuButton = new TextButton("Back", game.getSkin());
        goToMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                goToMainMenuButtonClicked();
            }
        });
        winStatusLabel = new Label("", game.getSkin());

        wave = 1;
        initWave(wave);
    }

    public void spawnTank() {
        objects.add(new Tank(world, getRandomPlace(), 0));
    }

    public void spawnTruck() {
        objects.add(new Truck(world, getRandomPlace(), 0));
    }

    public void spawnBuilding() {
        objects.add(new Building(world, getRandomPlace(), 0));
    }

    public void spawnBunker() {
        objects.add(new Bunker(world, getRandomPlace(), 0));
    }

    public void spawnTree() {
        objects.add(new Tree(world, getRandomPlace(), 0));
    }

    public void spawnZSU() {
        objects.add(new ZSU(world, getRandomPlace(), DIFFICULTY));
    }

    public void initWave(int number) {
        if (number > 3) {
            endGame();
        }
        clearWave();
        plane = new BomberPlane(world, 0, 0);
        objects.add(plane);
        for (int i = 0; i < 3; i++) spawnTank();
        for (int i = 0; i < 2; i++) spawnTruck();
        for (int i = 0; i < 2; i++) spawnTree();
        spawnBuilding();
        spawnBunker();
        if (number == 2) {
            for (int i = 0; i < 2; i++) spawnZSU();
        } else if (number == 3) {
            objects.add(new Mig(world, -graphics.getWidth() * camera.zoom / 2f / PPM, DIFFICULTY));
        }
    }

    public void nextWave() {
        if (isPaused) {
            isPaused = false;
            resume();
        }
        wave++;
        initWave(wave);
    }

    public void clearWave() {
        for (GameObject obj : objects) {
            removeBody(obj);
            removeObject(obj);
        }
    }

    public void endWave() {
        accuracyNumberLabel.setText(bombsUsed * 2 / kill);
        isPaused = true;
        stage.addActor(endWaveWindow);
    }

    public float getRandomPlace() {
        float random = ((float) Math.random()) - 0.5f;
        return random * graphics.getWidth() * camera.zoom / PPM;
    }

    public void endGame() {
        if (kill > 0) accuracyNumberLabel.setText(bombsUsed * 2 / kill);
        else accuracyNumberLabel.setText(0);
        if (wave > 3) winStatusLabel.setText("You Won!");
        else winStatusLabel.setText("You Lost!");
        isPaused = true;
        accuracyNumberLabel.setFontScale(1);
        accuracyTitleLabel.setFontScale(1);
        endGameTable.setFillParent(true);
        endGameTable.add(winStatusLabel).colspan(2);
        endGameTable.row();
        endGameTable.add(dataTable).colspan(2);
        endGameTable.row();
        endGameTable.add(accuracyTitleLabel);
        endGameTable.add(accuracyNumberLabel).padBottom(10);
        endGameTable.row();
        endGameTable.add(goToMainMenuButton).colspan(2);
        endGameWindow.addActor(endGameTable);
        endGameWindow.setPosition(graphics.getWidth() / 4f, graphics.getHeight() / 4f);
        endGameWindow.setWidth(graphics.getWidth() / 2f);
        endGameWindow.setHeight(graphics.getHeight() / 2f);
        stage.addActor(endGameWindow);
    }

    public void handleInput() {

        if (input.isKeyJustPressed(Keys.P)) {
            nextWave();
        }
        if (input.isKeyJustPressed(Keys.G)) {
            nuke++;
        }
        if (input.isKeyJustPressed(Keys.CONTROL_LEFT) || input.isKeyJustPressed(Keys.CONTROL_RIGHT)) {
            cluster++;
        }
        if (input.isKeyJustPressed(Keys.T)) {
            spawnTank();
        }
        if (input.isKeyJustPressed(Keys.H)) {
            if (hp < 2) hp++;
        }
        if (!plane.isDead()) plane.handleInput();
    }

    public void update(float deltaTime) {
        if (hp <= 0 || wave > 3) {
            endGame();
        }
        handleInput();
        if (!world.isLocked()) {
            if (plane.isDead())  {
                hp--;
                if (hp > 0) {
                    plane = new BomberPlane(world, 0, 0);
                    objects.add(0, plane);
                }
            }
            for (Body body : GameMenu.bodiesToBeRemoved) {
                if (!(body.getUserData() == null)) {
                    world.destroyBody(body);
                }
            }
            bodiesToBeRemoved.clear();
            for (BulletToBeAdded blt : GameMenu.bulletsToBeAdded) {
                GameMenu.addObject(new Bullet(blt));
            }
            bulletsToBeAdded.clear();
            for (Vector2 vector : clusterBonusToBeAdded) {
                GameMenu.addObject(new ClusterBonus(world, vector.x, vector.y));
            }
            clusterBonusToBeAdded.clear();
            for (Vector2 vector : nukeBonusToBeAdded) {
                GameMenu.addObject(new NukeBonus(world, vector.x, vector.y));
            }
            nukeBonusToBeAdded.clear();
        }
        world.step(1/60f, 6, 2);
        camera.update();

        for (GameObject object : objects) {
            object.update(deltaTime);
        }

        for (GameObject obj : objectsToBeRemoved) {
            objects.remove(obj);
            kill += obj.KILL;
        }
        objectsToBeRemoved.clear();

        batch.setProjectionMatrix(camera.combined);
        b2dr.render(world, camera.combined);

        killNumberLabel.setText(kill);
        nukeNumberLabel.setText(nuke);
        clusterNumberLabel.setText(cluster);
        waveNumberLabel.setText(wave);
        hpNumberLabel.setText(hp);

        if (objects.size() <= 1) {
            endWave();
        }
    }

    public void render(float deltaTime) {
        if (input.isKeyJustPressed(Keys.ESCAPE)) {
            isPaused = !isPaused;
            if (isPaused) pause();
            else resume();
        }
        if (!isPaused) update(deltaTime);
//        gl.glClearColor(0, 0, 0, 1);
//        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(deltaTime);
        draw();
        stage.draw();
    }

    public void draw() {
        batch.begin();
        for (GameObject object : objects) {
            object.draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewPort.update(width, height);
        camera.viewportWidth = width / PPM;
        camera.viewportHeight = height / PPM;
        camera.update();
        GameMenu.GROUND_LEVEL = -graphics.getHeight() * camera.zoom / 4f / PPM;
        if (pauseWindow == null) pauseWindow = new Window("Pause", game.getSkin());
        pauseWindow.setHeight(graphics.getHeight() / 2f);
        pauseWindow.setWidth(graphics.getWidth() / 2f);
        pauseWindow.setPosition(graphics.getWidth() / 4f, graphics.getHeight() / 4f);

        if (endWaveWindow == null) endWaveWindow = new Window("End Of Wave", game.getSkin());
        endWaveWindow.setWidth(graphics.getWidth() / 2f);
        endWaveWindow.setHeight(graphics.getHeight() / 2f);
        endWaveWindow.setPosition(graphics.getWidth() / 4f, graphics.getHeight() / 4f);
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
    }

    @Override
    public void pause() {
        stage.addActor(pauseWindow);
    }

    @Override
    public void resume() {
        stage.clear();
        stage.addActor(dataTable);
    }

    public static void addObject(GameObject obj) {
        GameMenu.objects.add(obj);
    }

    public static void removeBody(GameObject obj) {
        if (!bodiesToBeRemoved.contains(obj.body)) GameMenu.bodiesToBeRemoved.add(obj.body);
    }

    public static void removeObject(GameObject obj) {
        objectsToBeRemoved.add(obj);
    }

    public static void setFireAnimation(GameObject obj) {
        obj.die();
        objects.add(new Fire(world, obj.body.getPosition().x - 0.1f, obj.getY(), obj));
    }

    public static void setAirBlastAnimation(GameObject obj) {
        obj.die();
        removeBody(obj);
        removeObject(obj);
        objects.add(new AirBlast(world, obj.getX(), obj.getY()));
    }

    public static void setBigBlastAnimation(GameObject obj) {
        objects.add(new BigBlast(world, obj.getX(), obj.getY() - 0.1f));
    }

    public static void setNukeAnimation(GameObject obj) {
        objects.add(new NukeBlast(world, obj.getX() - 1.5f, obj.getY() - 0.2f));
    }

    public static void addBullet(BulletToBeAdded blt) {
        GameMenu.bulletsToBeAdded.add(blt);
    }

    public static void addNukeBonus(GameObject obj) { nukeBonusToBeAdded.add(new Vector2(obj.getX(), obj.getY())); }

    public static void addClusterBonus(GameObject obj) { clusterBonusToBeAdded.add(new Vector2(obj.getX(), obj.getY())); }

    public static void setDifficulty(int difficulty) { GameMenu.DIFFICULTY = difficulty; }

    public void pauseMenuExitButtonClicked() {
        if (LoginController.getCurrentUser() != null) setScreen(new MainMenu(game));
        else setScreen(new StartMenu(game));
    }

    public void goToMainMenuButtonClicked() {
        User user = LoginController.getCurrentUser();
        if (user != null){
            setScreen(new MainMenu(game));
            user.setGameData(wave, kill, DIFFICULTY, (float) (bombsUsed * 2) / (kill + 1));
        }
        else setScreen(new StartMenu(game));
    }
}
