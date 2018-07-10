package transferobjects;

import java.sql.Connection;
import data_access.h2.DAOFactoryH2;

public class Configuration {
	
	public static Connection connection = null;

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		Configuration.connection = connection;
	}
	
	/*public Configuration()
	{
		connection = DAOFactoryH2.getConnection();
	}*/
	
	

}
