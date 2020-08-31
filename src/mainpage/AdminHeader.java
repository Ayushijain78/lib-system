package mainpage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminHeader
 */
@WebServlet("/servlet/AdminHeader")
public class AdminHeader extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		HttpSession hs=request .getSession();
		String name=(String)hs.getAttribute("name");
		p.print("<html>");
		p.print("<head>");
		
		
		  p.print("<title>member</title>");
		  p.print("<link rel='stylesheet' type='text/css'href='../css/bootstrap.css'>");
		  p.print("<link rel='stylesheet' type='text/css' href='../css/navbar.css'>");
		  p.print("<script type='text/javascript' src='../js/bootstrap.js'></script>");
		  p.print("<script type='text/javascript' src='../js/jquery.min.js'></script>");
		p.print("</head>");
		p.print("<body>");
		p.print("<nav class='navbar navbar-expand-sm bg-dark navbar-dark'>");
		
		  p.print("<a class='navbar-brand text-light'>Liabrary management</a>");
		  
		
		  p.print("<ul class='navbar-nav ml-auto'>");
		    p.print("<li class='nav-item'>");
		      p.print("<a class='nav-link' href='AdminProfile'>"+name+"</a>");
		    p.print("</li>");
		   p.print(" <li class='nav-item'>");
		      p.print("<a class='nav-link' href='AddBook'>Add book</a>");
		   p.print(" </li>");
		    p.print("<li class='nav-item'>");
		      p.print("<a class='nav-link' href='SearchUpdate'>update book</a>");
		   p.print(" </li>");
		     p.print("<li class='nav-item'>");
		     p.print(" <a class='nav-link' href='IssueBook'>issue books</a>");
		   p.print(" </li>");
		    p.print(" <li class='nav-item'>");
		     p.print(" <a class='nav-link' href='ReturnBook'>return Book</a>");
		   p.print(" </li>");
		   p.print(" <li class='nav-item'>");
		     p.print(" <a class='nav-link' href='NewMember'>new member?</a>");
		   p.print(" </li>");
		   p.print(" <li class='nav-item'>");
		     p.print(" <a class='nav-link' href='AllMember'>All members</a>");
		   p.print(" </li>");
		     p.print("<li class='nav-item'>");
		      p.print("<a class='nav-link' href='Logout'>Logout</a>");
		    p.print("</li>");
		  p.print("</ul>");
		p.print("</nav><br>");
		p.print("</body>");
		p.print("</html>");
	}

}
