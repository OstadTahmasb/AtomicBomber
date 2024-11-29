package atomic.bomber;

import atomic.bomber.view.menus.StartMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import data.UserData;
import games.spooky.gdx.nativefilechooser.NativeFileChooser;
import games.spooky.gdx.nativefilechooser.NativeFileChooserConfiguration;

public class Main extends Game {
	public static AssetManager assetManager = new AssetManager();
	public static NativeFileChooser fileChooser;
	public static NativeFileChooserConfiguration chooserConfiguration;

	@Override
	public void create() {
		manageAssets();
		chooserConfiguration.directory = Gdx.files.absolute(System.getProperty("user.home"));
		setScreen(new StartMenu(this));

		UserData.load();
	}

	public void manageAssets() {
		assetManager.load(Assets.SKIN, Skin.class);

		for (int i = 0; i < Assets.PFP.size(); i++) {
			assetManager.load(Assets.PFP.get(i));
		}
		for (int i = 0; i < Assets.FIRE.size(); i++) {
			assetManager.load(Assets.FIRE.get(i));
		}
		for (int i = 0; i < Assets.AIR_BLAST.size(); i++) {
			assetManager.load(Assets.AIR_BLAST.get(i));
		}
		for (int i = 0; i < Assets.BIG_BLAST.size(); i++) {
			assetManager.load(Assets.BIG_BLAST.get(i));
		}
		assetManager.load(Assets.NUKE_ANIMATION);

		assetManager.load(Assets.PLANE);
		assetManager.load(Assets.TANK);
		assetManager.load(Assets.BUILDING);
		assetManager.load(Assets.BUNKER);
		assetManager.load(Assets.TREE);
		assetManager.load(Assets.IRON_BOMB);
		assetManager.load(Assets.NUKE_BOMB);
		assetManager.load(Assets.TRUCK);
		assetManager.load(Assets.MIG);
		assetManager.load(Assets.ZSU);
		assetManager.load(Assets.BULLET);
		assetManager.load(Assets.NUKE_BONUS);
		assetManager.load(Assets.CLUSTER_BONUS);

		assetManager.finishLoading();
	}

	public Skin getSkin() {
		return assetManager.get(Assets.SKIN);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1);
		super.render();
	}

	public void dispose() {
		UserData.save();
	}
}
