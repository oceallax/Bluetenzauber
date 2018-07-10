package presentation;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businesslogic.ArticleManager;
import transferobjects.Article;

/**
 * Servlet implementation class Bootstrapping
 */
@WebServlet(urlPatterns ={ "/Bootstrapping" })
public class Bootstrapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bootstrapping() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Tabelle mit Artikeln erstellen
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        System.out.println("Tabellen erstellt, Artikel drin");
        
        ArticleManager articleManager = new ArticleManager();
       
        Article article1 = new Article (1, "Seife",       10,  4);
        Article article2 = new Article (2, "Waschmittel",  8,  6);
        Article article3 = new Article (3, "Schwamm",      2,  1);
        Article article4 = new Article (4, "Puder",      100,  9);
        Article article5 = new Article (5, "Creme",        3, 20);
        
        articleManager.deleteArticleTable();
        articleManager.createArticleTable();
        
        articleManager.addArticle(article1);
        articleManager.addArticle(article2);
        articleManager.addArticle(article3);
        articleManager.addArticle(article4);
        articleManager.addArticle(article5);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
