package atomic.bomber.model;

import atomic.bomber.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.google.gson.Gson;
import data.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class User implements Serializable {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private int pfp;
    private int id;
    private int wave, kill, difficulty;
    private float accuracy;
    public User(String username, String password) {
        Random random = new Random();
        this.id = random.nextInt();
        this.username = username;
        this.password = password;
        pfp = random.nextInt(Assets.PFP.size());

        User.allUsers.add(this);
    }

    public static User getUserByUsername(String username) {
        for (User user : User.allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    public int getId() { return this.id; }

    public int getProfileNumber() { return this.pfp;}

    public void setProfileNumber(int pfp) { this.pfp = pfp;}

    public void setGameData(int wave, int kill, int difficulty, float accuracy) {
        this.wave = wave;
        this.kill = kill;
        this.difficulty = difficulty;
        this.accuracy = accuracy;
    }

    public int getKill() { return this.kill; }
    public int getWave() { return this.wave; }
    public int getDifficulty() { return this.difficulty; }
    public float getAccuracy() { return this.accuracy; }

    public static void setUsers(ArrayList<User> users) {
        User.allUsers = users;
    }

    public static ArrayList<User> getUsers() {
        return User.allUsers;
    }

    public static void removeUser(User user) { User.allUsers.remove(user); }

    public void save() {
        File file = new File("Data/Users/" + id + "/login-data.json");

        Gson gson = new Gson();
        try {
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            gson.toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
