package backend.dataopslag;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Loading file World");
        PersistenceManager.loadUsersFromFile();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Saving world to file");
        PersistenceManager.saveUsersToFile();
    }
}