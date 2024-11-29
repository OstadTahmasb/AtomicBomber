package atomic.bomber;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;

public class Assets {
//    public static String SKIN = "skins/Biological_Attack_UI_Skin/biologicalattackui/biological-attack-ui.json";
    public static String SKIN = "skins/flat-earth/skin/flat-earth-ui.json";
//    public static String SKIN = "skins/neon/skin/default.json";
//    public static String SKIN = "skins/level-plane/skin/level-plane-ui.json";

    public static ArrayList<AssetDescriptor<Texture>> PFP = new ArrayList<>(Arrays.asList(
            new AssetDescriptor<>("profiles/1.png", Texture.class),
            new AssetDescriptor<>("profiles/2.png", Texture.class),
            new AssetDescriptor<>("profiles/3.png", Texture.class),
            new AssetDescriptor<>("profiles/4.png", Texture.class)
    ));
    public static AssetDescriptor<Texture> PLANE = new AssetDescriptor<>("game/plane2.png", Texture.class);
    public static AssetDescriptor<Texture> TANK = new AssetDescriptor<>("game/tank1.png", Texture.class);
    public static AssetDescriptor<Texture> BUNKER = new AssetDescriptor<>("game/bunker2.png", Texture.class);
    public static AssetDescriptor<Texture> BUILDING = new AssetDescriptor<>("game/building2.png", Texture.class);
    public static AssetDescriptor<Texture> TREE = new AssetDescriptor<>("game/tree.png", Texture.class);
    public static AssetDescriptor<Texture> IRON_BOMB = new AssetDescriptor<>("game/ironbomb.png", Texture.class);
    public static AssetDescriptor<Texture> NUKE_BOMB = new AssetDescriptor<>("game/nukebomb.png", Texture.class);
    public static AssetDescriptor<Texture> TRUCK = new AssetDescriptor<>("game/truck.png", Texture.class);
    public static AssetDescriptor<Texture> MIG = new AssetDescriptor<>("game/mig1.png", Texture.class);
    public static AssetDescriptor<Texture> ZSU = new AssetDescriptor<>("game/zsu57.png", Texture.class);
    public static AssetDescriptor<Texture> BULLET = new AssetDescriptor<>("game/bullet1.png", Texture.class);
    public static AssetDescriptor<Texture> NUKE_BONUS = new AssetDescriptor<>("game/bonusnuke.png", Texture.class);
    public static AssetDescriptor<Texture> CLUSTER_BONUS = new AssetDescriptor<>("game/bonuscluster.png", Texture.class);

    public static ArrayList<AssetDescriptor<Texture>> FIRE = new ArrayList<>(Arrays.asList(
            new AssetDescriptor<>("animations/fire/fire1.png", Texture.class),
            new AssetDescriptor<>("animations/fire/fire2.png", Texture.class),
            new AssetDescriptor<>("animations/fire/fire3.png", Texture.class)
    ));

    public static ArrayList<AssetDescriptor<Texture>> AIR_BLAST = new ArrayList<>(Arrays.asList(
            new AssetDescriptor<>("animations/airblast/airblast1.png", Texture.class),
            new AssetDescriptor<>("animations/airblast/airblast2.png", Texture.class),
            new AssetDescriptor<>("animations/airblast/airblast3.png", Texture.class),
            new AssetDescriptor<>("animations/airblast/airblast4.png", Texture.class)
    ));

    public static ArrayList<AssetDescriptor<Texture>> BIG_BLAST = new ArrayList<>(Arrays.asList(
            new AssetDescriptor<>("animations/bigblast/bigblast1.png", Texture.class),
            new AssetDescriptor<>("animations/bigblast/bigblast2.png", Texture.class),
            new AssetDescriptor<>("animations/bigblast/bigblast3.png", Texture.class),
            new AssetDescriptor<>("animations/bigblast/bigblast4.png", Texture.class)
    ));

    public static AssetDescriptor<Texture> NUKE_ANIMATION = new AssetDescriptor<Texture>("animations/nuke.png", Texture.class);
}

