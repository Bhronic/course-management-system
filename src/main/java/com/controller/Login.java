package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DBConnection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		try {
			Connection con = new DBConnection().getConnection();

			PreparedStatement statement = con.prepareStatement("select * from user where id=?");
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				
				PreparedStatement blogSt = con.prepareStatement("select * from blog");
				ResultSet bolgRs = blogSt.executeQuery();
				
				PrintWriter pw = response.getWriter();
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
						+ "			  <h1>Welcome "+ rs.getString(3)+"</h1>\r\n"
						+ "			  <p>"+rs.getString(3)+" your email id is "+rs.getString(2)+"</p> \r\n"
						+ "<a href=\"logout\">\r\n"
						+ "         <button type=\"button\" class=\"btn btn-warning\">Logout</button>"
						+ "        </a>"		
						+ "			</div>"
						+ "<div class=\"container-fluid\">"
						+ "<a href=\"edit-profile?id="+rs.getString(1)+" \"style=\"float: left;\">Edit profile</a>"
						+ "</div>"
						+ "<br>"
						+ "<center>"
						+ "<div class=\"container\">"
						+ "<table class=\"table\">"
						+ "<tr><td>ID</td><td>TITLE</td><td>CONTENT</td><td colspan=2>Action<td>"
						+ "</tr>");
				while (bolgRs.next()) {
					pw.append("<tr>"
							+ "<td>"+bolgRs.getString(1)+"</td>"
									+ "<td>"+bolgRs.getString(2)+"</td>"
											+ "<td>"+bolgRs.getString(3)+"</td>"
													+ "<td>Edit</td>"
															+ "<td>Delete</td>"
							+ "</tr>");
				}
				
				pw.append("</table></div></center></body></html>");
				System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
			}else {
				PrintWriter pw = response.getWriter();
				pw.append("<html><body><center><b>email id and password is not valid.</b></center></body></html>");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {

			String emailId = request.getParameter("emailId"); 
			String password = request.getParameter("password");

			Connection con = new DBConnection().getConnection();
			System.out.println("create a connection.");

			PreparedStatement statement = con.prepareStatement("select * from user where email_id=? and password =?");
			statement.setString(1, emailId);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			
			
			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("name", rs.getString(3));
				session.setAttribute("emailId", rs.getString(2));
				
				System.out.println("creation time =>"+session.getCreationTime());
				System.out.println("last access time =>"+session.getLastAccessedTime());
				
				PreparedStatement blogSt = con.prepareStatement("select * from blog");
				ResultSet bolgRs = blogSt.executeQuery();
				
				PrintWriter pw = response.getWriter();
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
						+ "			  <h1>Welcome "+ rs.getString(3)+"</h1>\r\n"
						+ "			  <p>"+rs.getString(3)+" your email id is "+rs.getString(2)+"</p> \r\n"
						+ "<a href=\"logout\">\r\n"
						+ "         <button type=\"button\" class=\"btn btn-warning\">Logout</button>"
						+ "        </a>"		
						+ "			</div>"
						+ "<div class=\"container-fluid\">"
						+ "<a href=\"edit-profile?id="+rs.getString(1)+" \"style=\"float: left;\">Edit Details </a><br>"
						+ "<a href=\"profile\"\"style=\"float: left;\">Profile</a> <br>"
						+ "<a href=\"author\"\"style=\"float: left;\">Author Details</a>"
						+ "</div>"
						+ "<br>"
						+ "<center>"
						+ "<div class=\"container\">"
						+ "<table class=\"table\">"
						+ "<tr><td>ID</td><td>TITLE</td><td>CONTENT</td><td colspan=2>Action<td>"
						+ "</tr>");
				while (bolgRs.next()) {
					pw.append("<tr>"
							+ "<td>"+bolgRs.getString(1)+"</td>"
									+ "<td>"+bolgRs.getString(2)+"</td>"
											+ "<td>"+bolgRs.getString(3)+"</td>"
													+ "<td>Edit</td>"
															+ "<td>Delete</td>"
							+ "</tr>");
				}
				
				pw.append("</table></div></center></body></html>");
				System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
			}else {
				PrintWriter pw = response.getWriter();
				pw.append("<html><body><center><b>email id and password is not valid.</b></center></body></html>");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
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
