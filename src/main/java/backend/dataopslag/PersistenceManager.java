package backend.dataopslag;

import backend.classes.*;

import javax.ws.rs.GET;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PersistenceManager {

    public static void loadUsersFromFile() {
        Path userStorage = Path.of("/home/userdata.obj");

        try {
            InputStream is = Files.newInputStream(userStorage);
            ObjectInputStream ois = new ObjectInputStream(is);

            List<User> loadedUsers = (List<User>) ois.readObject();
            User.setUsers(loadedUsers);

            List<Huishouden> loadedHuishoudens = (List<Huishouden>) ois.readObject();
            Huishouden.setHuishoudens(loadedHuishoudens);

            List<Winkel> loadedWinkels = (List<Winkel>) ois.readObject();
            Winkel.setWinkels(loadedWinkels);

            List<Product> loadedProducts = (List<Product>) ois.readObject();
            Product.setProducten(loadedProducts);

            List<Recept> loadedRecepts = (List<Recept>) ois.readObject();
            Recept.setRecepten(loadedRecepts);

            List<Kookboek> loadedKookboeken = (List<Kookboek>) ois.readObject();
            Kookboek.setKookboeken(loadedKookboeken);

            List<Boodschappenlijstje> loadedBoodschappenlijstjes = (List<Boodschappenlijstje>) ois.readObject();
            Boodschappenlijstje.setBoodschappenlijstjes(loadedBoodschappenlijstjes);

            List<Favorietenlijstje> loadedFavorietenlijstjes = (List<Favorietenlijstje>) ois.readObject();
            Favorietenlijstje.setFavorietenlijstjes(loadedFavorietenlijstjes);

            List<Gebruiker> loadedGebruikers = (List<Gebruiker>) ois.readObject();
            Gebruiker.setGebruikers(loadedGebruikers);

            List<Hoofd> loadedHoofden = (List<Hoofd>) ois.readObject();
            Hoofd.setHoofden(loadedHoofden);

            List<Lid> loadedLid = (List<Lid>) ois.readObject();
            Lid.setLeden(loadedLid);

            List<Invite> loadedInvites = (List<Invite>) ois.readObject();
            Invite.setInvites(loadedInvites);

            List<ProductenInHuis> loadedProductenInHuis = (List<ProductenInHuis>) ois.readObject();
            ProductenInHuis.setProductenInHuis(loadedProductenInHuis);

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

            List<User> saveToUsers = User.getUsers();  // ik weet dat ik alles in een "wrapper" klasse zou kunnen maken maar ik kwam hier te laat achter dat ik niks opsloeg naast de users en anders zou ik veel om moeten gooien :(
            List<Huishouden> saveToHuishoudens = Huishouden.getHuishoudens();
            List<Winkel> saveToWinkel = Winkel.getWinkels();
            List<Product> saveToProducten = Product.getProducten();
            List<Recept> saveToRecepten = Recept.getRecepten();
            List<Kookboek> saveToKookboeken = Kookboek.getKookboeken();
            List<Boodschappenlijstje> saveToBoodschappenlijstjes = Boodschappenlijstje.getBoodschappenlijstjes();
            List<Favorietenlijstje> saveToFavorietenlijstjes = Favorietenlijstje.getFavorietenlijstjes();
            List<Gebruiker> saveToGebruikers = Gebruiker.getGebruikers();
            List<Hoofd> saveToHoofd = Hoofd.getHoofden();
            List<Lid> saveToLid = Lid.getLeden();
            List<Invite> saveToInvites = Invite.getInvites();
            List<ProductenInHuis> saveToProductenThuis = ProductenInHuis.getProductenInHuis();
            Path userStorage = Path.of("/home/userdata.obj");

            OutputStream os = Files.newOutputStream(userStorage);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(saveToUsers);
            oos.writeObject(saveToHuishoudens);
            oos.writeObject(saveToWinkel);
            oos.writeObject(saveToProducten);
            oos.writeObject(saveToRecepten);
            oos.writeObject(saveToKookboeken);
            oos.writeObject(saveToBoodschappenlijstjes);
            oos.writeObject(saveToFavorietenlijstjes);
            oos.writeObject(saveToGebruikers);
            oos.writeObject(saveToHoofd);
            oos.writeObject(saveToLid);
            oos.writeObject(saveToInvites);
            oos.writeObject(saveToProductenThuis);
            os.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}