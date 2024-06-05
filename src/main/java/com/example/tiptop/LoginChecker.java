package com.example.tiptop;

import java.io.IOException; 
import java.util.List;

import com.example.tiptop.database.MitarbeiterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginChecker")
public class LoginChecker extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Validate username and password
	    if (isValidCredentials(request.getParameter("username"), request.getParameter("password"))) {
	        // Create session
	    	HttpSession session = request.getSession(true);
	        session.setAttribute("role", getUserRole(request.getParameter("username"), request.getParameter("password")));
	        
	        // Redirect to homepage
	        response.sendRedirect("form.jsp");
	    } else {
	        // Handle invalid credentials
	        response.sendRedirect("login.html");
	    }
	}

 protected boolean isValidCredentials(String username, String password) {
	 	 MitarbeiterDAO mdao = new MitarbeiterDAO();
		 // Find customers with matching username and password
	     List<loginMitarbeiter> mitarbeiter = mdao.findWithStatement(" WHERE username = \"" + username + "\" AND password = \"" + password + "\"");
	     System.out.println(username);
	     System.out.println(password);
	     System.out.println(mitarbeiter);
	     
	     if (mitarbeiter.isEmpty()) {
	    	 return false;
	     } else {
	         // Otherwise, redirect to the customers page
	    	 System.out.println("Equals: Passed at " + mitarbeiter.toString());
	    	 return true;
	     }
 }
 protected String getUserRole(String username, String password) {
	 MitarbeiterDAO mdao = new MitarbeiterDAO();
	 String Role = null;
	 List<loginMitarbeiter> mitarbeiter = mdao.findWithStatement(" WHERE username = \"" + username + "\" AND password = \"" + password + "\"");
	 
	 for(loginMitarbeiter ma : mitarbeiter) {
		 Role = ma.getRole();
		 System.out.print("\n" + Role + "test please why dont you work");
	 }
	 System.out.print("\n" + Role + "This is the role");
	 return Role;

 }
	 
}