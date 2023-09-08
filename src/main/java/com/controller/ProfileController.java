package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		String emailId = (String) session.getAttribute("emailId");
		
		System.out.println("creation time =>"+session.getCreationTime());
		System.out.println("last access time =>"+session.getLastAccessedTime());
		
		PrintWriter pw = response.getWriter();
		
		if(!Objects.isNull(name)) {
		
		pw.append("<html>"
				+"<head>\r\n"
				+ "  <title>Bootstrap Example</title>\r\n"
				+ "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
				+ "</head>"
				+ "<body>"
				+"<div class=\"container-fluid p-5 bg-primary text-white text-center\">\r\n"
				+ "			  <h1>Welcome "+ name+"</h1>\r\n"
				+ "			  <p>"+name+" your email id is "+emailId+"</p> \r\n"
				+ "<a href=\"logout\">\r\n"
				+ "         <button type=\"button\" class=\"btn btn-warning\">Logout</button>"
				+ "        </a>"		
				+ "			</div>"
				
				);
		}else {
			pw.append("<html><body><center><b>invalid user.please login again!</b></center></body></html>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.include(request, response);
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
