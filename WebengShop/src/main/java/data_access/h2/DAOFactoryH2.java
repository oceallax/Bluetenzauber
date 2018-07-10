package data_access.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import data_access.DAOFactory;
import data_access.DataAccess;
import data_access.DataAccessArticle;
import presentation.listener.Initialisierung;
import transferobjects.Configuration;

public class DAOFactoryH2 extends DAOFactory {
	

	private static Connection openConnection() {
			
			//Connection von TransferObject
			connection = Configuration.getConnection();
			System.out.println("Connected to database");

		return connection;
	}
	
	
	private static Connection connection;

	public static Connection getConnection() {
		if(connection == null)
			connection = openConnection();
		return connection;
	}

     

	@Override
	public DataAccess getArticleDAO() {
		return new DataAccessArticle();
	}
	
	
	@Override
	public Configuration getConfiguration() {
		return new Configuration();
	} 



	@Override
	public void shutdown() {
		try {
			connection.createStatement().execute("SHUTDOWN");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
