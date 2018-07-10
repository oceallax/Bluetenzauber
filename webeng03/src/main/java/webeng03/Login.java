package webeng03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns ={ "/LoginServlet" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Erhalte Benutzername aus request
		String user = request.getParameter("user");
		
		
		//Benutzername ist leer
		if(user.isEmpty()) {
			
			//Fehlerseite mit Statuscode
			 response.sendError(response.SC_BAD_REQUEST, "user is missing");
	

		}else {
			
			//User-Input nicht leer
			
			//Erstelle Session
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			
			response.sendRedirect("protected/protectedIndex.html");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
