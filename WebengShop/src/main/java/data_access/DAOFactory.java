package data_access;

import data_access.h2.DAOFactoryH2;
import transferobjects.Configuration;

public abstract class DAOFactory {
	
	// List of DAO types supported by the factory 
	public static final int H2 = 1; 
	// There will be a method for each DAO that can be 
	// created. The concrete factories will have to 
	// implement these methods. 
	public abstract DataAccess getArticleDAO(); 
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
