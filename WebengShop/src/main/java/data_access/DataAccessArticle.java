package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import data_access.h2.DAOFactoryH2;
import transferobjects.Article;

public class DataAccessArticle implements DataAccess {
	
	//TODO sollte diese Klasse DataAccessH2 heißen und im H2-Package sein?
	Connection connection;
	public DataAccessArticle()
	{
		connection = DAOFactoryH2.getConnection();
	}

	@Override
	public void addArticle(Article newArticle) throws Exception {
	    
		// SQL-Statement definieren
        String sql= "INSERT INTO article (id, name, price, amount) VALUES (?,?,?,?)"; 
            try {        
                PreparedStatement stmt= connection.prepareStatement(sql);
                //setzte Id
                stmt.setInt(1, newArticle.getId());
                //setzte Artikelname
                stmt.setString(2, newArticle.getName());
                //setzte Preis
                stmt.setFloat(3, newArticle.getPrice());
                //setzte Menge
                stmt.setInt(4, newArticle.getAmount());
                
                //statement ausfuehren
                stmt.execute();
                
            } catch (SQLException ex) {
                Logger.getLogger(DataAccessArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}

	@Override
	public void createArticleTable() throws Exception {
        String sql= "CREATE TABLE article (  \r\n" + 
        		"    id INT,                 \r\n" + 
        		"    name VARCHAR(255),      \r\n" + 
        		"    price FLOAT,            \r\n" + 
        		"    amount INT,             \r\n" + 
        		")"; 
        try {        
            PreparedStatement stmt= connection.prepareStatement(sql);
    
            //statement ausfuehren
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	@Override
	public void deleteArticleTable() throws Exception {
		
        String sql= "DROP TABLE article"; 
        try {        
            PreparedStatement stmt= connection.prepareStatement(sql);
    
            //statement ausfuehren
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	@Override
	public List<Article> allArticles() throws SQLException {
		
		int id;
		String name;
		float price;
		int amount;
	      
	    Article article;
	      
	      //Liste zum speichern aller Article
	      List<Article> allArticles = new LinkedList<Article>();
	      int index=0;
	      
	      String sql= "SELECT * FROM article"; 
	      
	      try{
	      PreparedStatement stmt=connection.prepareStatement(sql);
	      
	      ResultSet rs=stmt.executeQuery();
	      
	      //inhalte auslesen
	      while(rs.next()){
	          
	          id           = rs.getInt("id");
	          name         = rs.getString("name");
	          price        = rs.getFloat("price");
	          amount       = rs.getInt("amount");
	          article = new Article(id, name, price, amount);
	          
	          
	          //In Liste einfügen
	          allArticles.add(article);
	       
	      }
	      
	      return allArticles;
	      
	      }
	      catch(SQLException ex){
	          Logger.getLogger(DataAccessArticle.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      return null;
	    }

	@Override
	public void removeBoughtArticle(List<Article> boughtArticle) throws Exception {
		
		
		//Warenkorb durchlaufen
		for (Article currentArticle : boughtArticle) {
			
			//Update Menge in Datenbank für jeden Artikel
			String updateTableSQL = "UPDATE article SET amount = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
			preparedStatement.setInt(1,currentArticle.getAmount()-1);
			preparedStatement.setInt(2, currentArticle.getId());
			
			preparedStatement .executeUpdate();
		}
			
		
	}
	

}
