
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertTrue;


public class TestHelloDataSource {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	ServletContext servletContext;
	
	Connection con;
	String testString = "HelloDataSourceTest";

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		JdbcDataSource dataSource = new JdbcDataSource();
	   	
        dataSource.setUrl("jdbc:h2:./build/HelloDataSourceTestDB");		// use seperate TestDB which can't be modified by app
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        try
        {
        	con = dataSource.getConnection();
        	Statement stmt = con.createStatement();
        	stmt.execute("DROP TABLE IF EXISTS TEST");
        	stmt.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
        	stmt.execute("INSERT INTO TEST VALUES(1,'" + testString + "')");
        }
        catch(SQLException e)
        {
        	System.out.println(e.getMessage());
        }
	}
	@After
	public void finalize() throws SQLException
	{
		
		try
        {
        	Statement stmt = con.createStatement();
        	stmt.execute("DROP TABLE IF EXISTS TEST;");
        }
        catch(SQLException e)
        {
        	System.out.println(e.getMessage());
        }
		con.close();
	}

	@Test
	public void testGet() throws Exception {

		StringWriter writer = new StringWriter();

		// initialize mock data (Mockito)
		when(request.getContextPath()).thenReturn("/");
		when(response.getWriter()).thenReturn(new PrintWriter(writer));
		when(servletContext.getAttribute("connection")).thenReturn(con);
		
		
		// start get-request
		HelloDataSource testServlet = new HelloDataSource() {
			public ServletContext getServletContext() {
				return servletContext; // return the mock
			}
		};
		testServlet.doGet(request, response);
		
		// Verify if testServlet calls 1x servletContext.getAttribute('connection')
		verify(servletContext, times(1)).getAttribute("connection");
		// initialize expected result
		

		System.out.println(writer.toString());		
		// when response and expected result are not equal, test fails
		assertTrue(writer.toString().contains(testString));
		
		
	}

}
