package data_access;

import java.sql.SQLException;
import java.util.List;

import transferobjects.Article;

public interface DataAccess {
	
	public void addArticle(Article newArticle) throws Exception;
	public void removeBoughtArticle(List <Article> boughtArticle) throws Exception;
	public void createArticleTable() throws Exception;
	public void deleteArticleTable() throws Exception;
	public List <Article> allArticles() throws SQLException;


}
