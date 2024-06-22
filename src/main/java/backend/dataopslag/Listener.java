package backend.dataopslag;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Loading file Eet-share");
        PersistenceManager.loadUsersFromFile();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Saving Eet-share to file");
        PersistenceManager.saveUsersToFile();
    }
}