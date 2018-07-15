package example.nosql;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReturnSearch")
public class ReturnSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnSearch.main(null);
		String user=request.getParameter("user");
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Search.jsp");
		requestDispatcher.forward(request, response);
	}
	public static void main(String args[]){
		
	}
}
