package atomic.bomber.controller.menus;

import atomic.bomber.Assets;
import atomic.bomber.Main;
import atomic.bomber.model.User;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import games.spooky.gdx.nativefilechooser.NativeFileChooserCallback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AvatarController {
    public static Texture getProfilePicture() {
        User user = LoginController.getCurrentUser();
        int pfp = user.getProfileNumber();

        if (pfp == -1) return new Texture(new FileHandle(new File("Data/Users/" + user.getId() + "/pfp.png")));
        else return Main.assetManager.get(Assets.PFP.get(pfp));
    }

    public static void setProfilePicture(int index) {
        User user = LoginController.getCurrentUser();
        user.setProfileNumber(index);
        user.save();
        File file = new File("Data/Users/" + user.getId() + "/pfp.png");
        file.delete();
    }

    public static void chooseProfilePicture() {
        User user = LoginController.getCurrentUser();

        Main.fileChooser.chooseFile(Main.chooserConfiguration, new NativeFileChooserCallback() {
            @Override
            public void onFileChosen(FileHandle file) {
                if (!file.name().endsWith(".png")) return;
                user.setProfileNumber(-1);
                File dest = new File("Data/Users/" + user.getId() + "/pfp.png");
                if (dest.exists()) dest.delete();
                try {
                    Files.copy(file.file().toPath(), dest.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                user.save();
            }

            @Override
            public void onCancellation() {}

            @Override
            public void onError(Exception exception) {}
        });
    }
}
