
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TestHelloServlet {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGet() throws Exception {
	
		StringWriter writer = new StringWriter();

		// initialize mock data (Mockito)
		when(request.getContextPath()).thenReturn("/");	
		when(response.getWriter()).thenReturn(new PrintWriter(writer));

		// start get-request
		new HelloServlet().doGet(request, response);
		System.out.println(writer);
		
		// initialize expected result
		String expResult = "Hello Servlet\nServed at: " + request.getContextPath();
		
		// when response and expected result are not equal, test fails
		assertEquals(expResult, writer.toString());
	}

	@Test
	public void testPost() throws Exception {
		StringWriter writer = new StringWriter();

		// initialize mock data (Mockito)
		when(request.getContextPath()).thenReturn("/");	
		when(response.getWriter()).thenReturn(new PrintWriter(writer));

		// start get-request
		new HelloServlet().doPost(request, response);
		System.out.println(writer);
		
		// initialize expected result
		String expResult = "Hello Servlet\nServed at: " + request.getContextPath();
		
		// when response and expected result are not equal, test fails
		assertEquals(expResult, writer.toString());
	}
}
