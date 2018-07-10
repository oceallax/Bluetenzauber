package data_access.article;

import java.sql.SQLException;
import java.util.List;

import transferobjects.Article;



public interface DataAccessArticle {
	
	/*TODO
	 * Interface DataAccessArticle
	 * Superclass Article
	 * Subclass DataAccessPlant & DataAccessBunchofFlowers
	 */
	
	public void addArticle(Article newArticle) throws Exception;
	public void removeBoughtArticle(List <Article> boughtArticle) throws Exception;
	public void createArticleTables() throws Exception;
	public void deleteArticleTables() throws Exception;
	public List <Article> getArticles() throws SQLException;
	public List <Article> searchForArticle(String articleName) throws SQLException;
	public Article getArticle(int id) throws SQLException;


}
