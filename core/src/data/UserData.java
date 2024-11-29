package data;

import atomic.bomber.model.User;

import java.io.*;
import java.util.ArrayList;

public class UserData {
    private static final String FILE_NAME = "USERS_DATA";
    public static void save() {
        ArrayList<User> allUsers = User.getUsers();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(UserData.FILE_NAME))) {
            oos.writeObject(allUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UserData.FILE_NAME))) {
            ArrayList<User> readUsers = (ArrayList<User>) ois.readObject();
            User.setUsers(readUsers);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}