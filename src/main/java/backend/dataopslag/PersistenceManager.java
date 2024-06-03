package backend.dataopslag;

import backend.classes.User;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.util.List;

public class PersistenceManager {

    public static void loadUsersFromFile() {
        Path userStorage = Path.of("/home/userdata.obj");

        try {
            InputStream is = Files.newInputStream(userStorage);
            ObjectInputStream ois = new ObjectInputStream(is);

            List<User> loadedUsers = (List<User>) ois.readObject();
            User.setUsers(loadedUsers);

            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsersToFile() {
        Path homeDirectory = Path.of("/home");
        try {
            if (!Files.exists(homeDirectory)) {
                Files.createDirectory(homeDirectory);
            }

            List<User> saveToUsers = User.getUsers();
            Path userStorage = Path.of("/home/userdata.obj");

            OutputStream os = Files.newOutputStream(userStorage);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(saveToUsers);
            os.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}