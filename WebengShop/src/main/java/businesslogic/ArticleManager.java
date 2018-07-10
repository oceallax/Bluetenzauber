package businesslogic;

import java.util.List;

import data_access.DAOFactory;
import data_access.DataAccess;
import transferobjects.Article;



public class ArticleManager {
	
	DataAccess articleDAO;
	
	//Konstruktor
	public ArticleManager() {
		super();
		articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
	}
	
	/**Artikel hinzufügen*/
	public void addArticle(Article newArticle) {
		
		//Neues DAO?
		DataAccess articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		try {
			articleDAO.addArticle(newArticle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/** Tabelle erstellen */
	public void createArticleTable() {
		
		//Neues DAO?
		DataAccess articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		try {
			articleDAO.createArticleTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/** Tabelle löschen */
	public void deleteArticleTable() {
		//Neues DAO?
		DataAccess articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		try {
			articleDAO.deleteArticleTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Liste mit allen Artikeln aus der Datenbank holen */
	public List <Article> allArticles() {
		
		List <Article> list = null;
		
		//Neues DAO?
		DataAccess articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		try {
			list = articleDAO.allArticles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
}
