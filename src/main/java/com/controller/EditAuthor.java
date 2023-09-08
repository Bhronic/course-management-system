package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;

/**
 * Servlet implementation class EditAuthor
 */
@WebServlet("/editauthor")
public class EditAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAuthor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		PrintWriter pw = response.getWriter();
		try {
			Connection con = new DBConnection().getConnection();
			
		PreparedStatement ps = con.prepareStatement("select * from author where id = ?");
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			pw.append("<html>\r\n"
					+ "<head>\r\n"
					+ "<title>Author register</title>\r\n"
					+ "<meta charset=\"utf-8\">\r\n"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
					+ "<link\r\n"
					+ "	href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\"\r\n"
					+ "	rel=\"stylesheet\">\r\n"
					+ "<script\r\n"
					+ "	src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
					+ "</head>\r\n"
					+ "<body style=\"padding-right: 25%; padding-left: 25%; padding-top: 5%;\">\r\n"
					+ "\r\n"
					+ "	<div class=\"container mt-3\">\r\n"
					+ "		<div class=\"container mt-3\"\r\n"
					+ "			style=\"border: solid 5px #e8f0fe; padding: 20px\">\r\n"
					+ "			<h2>Author form</h2>\r\n"
					+ "			<form action=\"editauthor\" method=\"post\">\r\n"
					+ "				<div class=\"mb-3 mt-3\">\r\n"
					+ "					<label for=\"email\">Name:</label> <input type=\"text\"\r\n"
					+ "						class=\"form-control\" id=\"name\" placeholder=\"Enter name\"\r\n"
					+ "						name=\"name\" value="+rs.getString(2)+">\r\n"
					+ "				</div>\r\n"
					+ "				<div class=\"mb-3\">\r\n"
					+ "					<label for=\"pwd\">Publisher:</label> <input type=\"text\"\r\n"
					+ "						class=\"form-control\" id=\"publisher\" placeholder=\"Enter publisher name\"\r\n"
					+ "						name=\"publisher\" value="+rs.getString(3)+">\r\n"
					+ "				</div>\r\n"
					+ " <input type=\"hidden\" name=\"id\" value="+rs.getInt(1)+">"
					+ "				<button type=\"submit\" class=\"btn btn-primary\">update</button>\r\n"
					+ "			</form>\r\n"
					+ "		</div>\r\n"
					+ "	</div>\r\n"
					+ "</body>\r\n"
					+ "</html>");
		}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String publisher = request.getParameter("publisher");

		PrintWriter pw = response.getWriter();
		pw.append(name + " == " + publisher);

		try {
			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// create connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "admin@123");

			PreparedStatement ps = con.prepareStatement("update author set name =? ,publisher=? where id=?");
			ps.setInt(3, id);
			ps.setString(1, name);
			ps.setString(2, publisher);

			int value = ps.executeUpdate();
			con.close();

			if (value != 0) {
				response.sendRedirect("http://localhost:8080/course-management-system/author");

			} else {
				pw.append("<center>invalidate details</center>");
				RequestDispatcher rd = request.getRequestDispatcher("edirauthor");
				rd.include(request, response);
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
