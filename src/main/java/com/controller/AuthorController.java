package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthorController
 */
@WebServlet("/author")
public class AuthorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthorController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		String emailId = (String) session.getAttribute("emailId");

		PrintWriter pw = response.getWriter();
		if (name != null) {
			pw.append("<html>" + "<head>\r\n" + "  <title>Bootstrap Example</title>\r\n"
					+ "  <meta charset=\"utf-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
					+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
					+ "</head>" + "<body>" + "<div class=\"container-fluid p-5 bg-primary text-white text-center\">\r\n"
					+ "			  <h1>Welcome " + name + "</h1>\r\n" + "			  <p>" + name + " your email id is "
					+ emailId + "</p> \r\n" + "<a href=\"logout\">\r\n"
					+ "         <button type=\"button\" class=\"btn btn-warning\">Logout</button>" + "        </a>"
					+ "			</div>" + "<div class=\"container-fluid\">"
					+ "<a href=\"author.jsp\"\"style=\"float: left;\">Add new Author</a>" + "</div>");
			pw.append("<center>" + "<div class=\"container\">" + "<table class=\"table\">");
			pw.append("<tr>" + "<td>ID</td><td>NAME</td><td>PUBLISHER</td><td colspan=2>ACTION</td>" + "</tr>");

			try {
				// Register the Driver class
				Class.forName("com.mysql.cj.jdbc.Driver");

				// create connection
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root",
						"admin@123");

				PreparedStatement ps = con.prepareStatement("select * from author");
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					pw.append("<tr>" 
							+ "<td>" + rs.getInt(1) 
							+ "</td>" + "<td>" + rs.getString(2) 
							+ "</td>" + "<td>"+ rs.getString(3) + "</td>" 
							+ "<td>" + "<a href=\"editauthor?id="+rs.getInt(1)+"\">edit</a>" + "</td>" 
							+ "<td>"+ "<a href=\"deleteauthor?id="+rs.getInt(1)+"\">delete</a>" + "</td>" + "</tr>");
				}

				pw.append("</table></div></center></body></html>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			pw.append("<html><body><center><b>invalid user.please login again!</b></center></body></html>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
