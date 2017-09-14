package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.vo.BookVo;
import model.dao.BookDao;
@WebServlet("/book")
public class BookServlet extends HttpServlet {
    public BookServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		getCustomers(request, response);
	}
	
	public void getCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;		
		try {			
			ArrayList allList = BookDao.getBooks();	
			System.out.println(allList);
			request.setAttribute("allList", allList);
			url = "asdasdasd.jsp";
			
		} catch (SQLException e) {			
			request.setAttribute("error", "sad");
			url = "error.jsp";
		}
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}
	
}
