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
 * Servlet implementation class EditProfile
 */
@WebServlet("/edit-profile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			Integer id = Integer.parseInt(request.getParameter("id")); 

			Connection con = new DBConnection().getConnection();

			PreparedStatement statement = con.prepareStatement("select * from user where id=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				PrintWriter pw = response.getWriter();
				pw.append("<html>\r\n"
						+ "<head>\r\n"
						+ "  <title>Bootstrap Example</title>\r\n"
						+ "  <meta charset=\"utf-8\">\r\n"
						+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
						+ "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
						+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
						+ "</head>\r\n"
						+ "<body style=\"padding-right: 25%;padding-left: 25%;padding-top: 5%;\">\r\n"
						+ "\r\n"
						+ "<div class=\"container mt-3\">\r\n"
						+ "<div class=\"container mt-3\" style=\"border: solid 5px #e8f0fe; padding: 20px\">\r\n"
						+ "  <h2>Registration form</h2>\r\n"
						+ "  <form action=\"edit-profile\" method=\"post\">\r\n"
						+ "    \r\n"
						+ "    <div class=\"mb-3\">\r\n"
						+ "      <label for=\"pwd\">Name:</label>\r\n"
						+ "      <input type=\"text\" class=\"form-control\" id=\"name\" placeholder=\"Enter name\" "
						+ "     name=\"name\" value="+rs.getString(3)+">"
						
						+ "      <input type=\"hidden\" class=\"form-control\" id=\"id\" "
						+ "     name=\"id\" value="+rs.getString(1)+">"
						
						+ "    </div><div class=\"mb-3 mt-3\">\r\n"
						+ "      <label for=\"email\">Email:</label>\r\n"
						+ "      <input type=\"email\" class=\"form-control\" id=\"emailId\" placeholder=\"Enter email\" "
						+ "     name=\"email\" value="+rs.getString(2)+">\r\n"
						+ "    </div>\r\n"
						+ "    <div class=\"mb-3\">\r\n"
						+ "      <label for=\"pwd\">Password:</label>\r\n"
						+ "      <input type=\"text\" class=\"form-control\" id=\"password\" placeholder=\"Enter password\" "
						+ "		name=\"password\" value="+rs.getString(4)+">\r\n"
						+ "    </div>\r\n"
						+ "    <button type=\"submit\" class=\"btn btn-primary\">Update</button>\r\n"
						+ "  </form>\r\n"
						+ "</div>\r\n"
						+ "</div>\r\n"
						+ "</body>\r\n"
						+ "</html>");
				System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
			}else {
				PrintWriter pw = response.getWriter();
				pw.append("<html><body><center><b>email id and password is not valid.</b></center></body></html>");
				//RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login?id="+id);
				//requestDispatcher.include(request, response);
				response.sendRedirect("http://localhost:8080/course-management-system/login?id="+id);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			User user = new User();
			user.setEmailId(request.getParameter("email"));
			user.setId(Integer.parseInt(request.getParameter("id")));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));

			Connection con = new DBConnection().getConnection();

			PreparedStatement statement = con.prepareStatement("update user set name=?,email_id=?,password=? where id=?");
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmailId());
			statement.setString(3, user.getPassword());
			statement.setInt(4, user.getId());
			int value = statement.executeUpdate();
			
			if (value != 0) {
				PrintWriter pw = response.getWriter();
				pw.append("<html><body><center><b> edit profile successfully</b></center></body></html>");
				//RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login?id="+id);
				//requestDispatcher.include(request, response);
				response.sendRedirect("http://localhost:8080/course-management-system/login?id="+user.getId());
			}else {
				PrintWriter pw = response.getWriter();
				pw.append("<html><body><center><b> invalid parameter.please check.</b></center></body></html>");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit-profile?id ="+user.getId());
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
