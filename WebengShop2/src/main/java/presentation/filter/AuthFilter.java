package presentation.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(filterName="/AuthFilter", urlPatterns= "/AddArticle.jsp")
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if(request.getParameter("item")!= null) {
		
			String authToken = request.getParameter("item");
			
			
			//User ist nicht angemeldet
			if(authToken.equals("admin")) {
				
				// Weiterleitung zum nächsten Filter oder zur Ressource am Ende der Filterkette 
				chain.doFilter(request, response);
				
			}else {
				
				//Zugriff verweigert
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Zugriff ist nicht erlaubt");
	
			}
		}else {
			
			//Zugriff verweigert
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Zugriff ist nicht erlaubt");
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
