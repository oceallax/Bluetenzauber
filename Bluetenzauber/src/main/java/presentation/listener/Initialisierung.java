package presentation.listener;

import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import transferobjects.Configuration;

/**
 * Application Lifecycle Listener implementation class Initialisierung
 * 
 * Erstellen Sie einen ServletContextListener, der  beim   Start   der   Anwendung   die   SQL-
 * Verbindung   aus   dem   Servlet-Kontext   ausliest   und   in   dem   statischen   Feld   der   Klasse   
 * Configuration speichert.   Schauen   Sie   sich   dazu   das   Projekt   HelloDataSource
 * nochmals   an. Wodurch   wird   das Kontextattribut connection erzeugt ?
 *
 */
@WebListener("Initialisierung")
public class Initialisierung implements ServletContextListener {
	
	public Connection con = null;
    /**
     * Default constructor. 
     */
    public Initialisierung() {
        // TODO Auto-generated constructor stub
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	con = (Connection) sce.getServletContext().getAttribute("connection");
    	Configuration.setConnection(con);
    }

	
}
