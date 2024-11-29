package atomic.bomber;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import atomic.bomber.Main;
import games.spooky.gdx.nativefilechooser.NativeFileChooserConfiguration;
import games.spooky.gdx.nativefilechooser.desktop.DesktopFileChooser;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Atomic Bomber");
		config.setWindowedMode(1200, 800);
		Main.fileChooser = new DesktopFileChooser();
		Main.chooserConfiguration = new NativeFileChooserConfiguration();

//		Graphics.DisplayMode primaryMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
//		config.setFullscreenMode(primaryMode);

		new Lwjgl3Application(new Main(), config);
	}
}
