package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;
import com.model.User;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet("/register")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration() {
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
		try {
			User user = new User();
			user.setEmailId(request.getParameter("emailId"));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));

			Connection con = new DBConnection().getConnection();

			PreparedStatement statement = con.prepareStatement("insert into user(email_id,name,password) value(?,?,?)");
			statement.setString(1, user.getEmailId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPassword());
			int value = statement.executeUpdate();
			
			if (value != 0) {
				PrintWriter pw = response.getWriter();
				pw.append("<html><body><center><b> register successfully</b></center></body></html>");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.include(request, response);
			}else {
				PrintWriter pw = response.getWriter();
				pw.append("<html><body><center><b> invalid parameter.please check.</b></center></body></html>");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registrationForm.jsp");
				requestDispatcher.include(request, response);
			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
