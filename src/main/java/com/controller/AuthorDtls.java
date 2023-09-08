package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthorDtls
 */
@WebServlet("/authorDtls")
public class AuthorDtls extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorDtls() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String publisher = request.getParameter("publisher");

		PrintWriter pw = response.getWriter();
		pw.append(name + " == " + publisher);

		try {
			// Register the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// create connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "admin@123");

			PreparedStatement ps = con.prepareStatement("INSERT INTO `user_info`.`author` VALUES (?,?,?)");
			ps.setInt(1, 0);
			ps.setString(2, name);
			ps.setString(3, publisher);

			int value = ps.executeUpdate();
			con.close();

			if (value != 0) {
				response.sendRedirect("http://localhost:8080/course-management-system/author");

			} else {
				pw.append("<center>invalidate details</center>");
				RequestDispatcher rd = request.getRequestDispatcher("author.jsp");
				rd.include(request, response);
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
