package data_access.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import data_access.DAOFactory;
import data_access.article.DataAccessArticle;
import data_access.article.DataAccessArticle_Imple;
import data_access.article.DataAccessBunchofFlowers;
import data_access.article.DataAccessPlant;
import data_access.user.DataAccessUser_Imple;
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
	public DataAccessArticle_Imple getArticleDAO() {
		return new DataAccessArticle_Imple();
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



	@Override
	public DataAccessPlant getPlantDAO() {
		return new DataAccessPlant();
	}



	@Override
	public DataAccessBunchofFlowers getBunchOfFlowersDAO() {
		return new DataAccessBunchofFlowers();
	}



	@Override
	public DataAccessUser_Imple getUserDAO() {
		return new DataAccessUser_Imple();
	}

}
