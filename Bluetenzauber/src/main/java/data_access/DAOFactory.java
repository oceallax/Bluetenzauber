package data_access;

import data_access.article.DataAccessArticle;
import data_access.article.DataAccessArticle_Imple;
import data_access.article.DataAccessBunchofFlowers;
import data_access.article.DataAccessPlant;
import data_access.h2.DAOFactoryH2;
import data_access.user.DataAccessUser_Imple;
import transferobjects.Configuration;

public abstract class DAOFactory {
	
	// List of DAO types supported by the factory 
	public static final int H2 = 1; 
	// There will be a method for each DAO that can be 
	// created. The concrete factories will have to 
	// implement these methods. 
	public abstract DataAccessArticle_Imple getArticleDAO(); 
	public abstract DataAccessPlant getPlantDAO(); 
	public abstract DataAccessBunchofFlowers getBunchOfFlowersDAO(); 
	public abstract DataAccessUser_Imple getUserDAO(); 
	public abstract Configuration getConfiguration();
	public abstract void shutdown();
	public static DAOFactory getDAOFactory(int whichFactory) { 
		switch (whichFactory) { 
			case H2: 
				return new DAOFactoryH2(); 
			default: 
				return null; 
		} 
	}

}
