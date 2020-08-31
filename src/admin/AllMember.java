package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbo.BookMaster;

/**
 * Servlet implementation class AllMember
 */
@WebServlet("/servlet/AllMember")
public class AllMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		RequestDispatcher td=request.getRequestDispatcher("AdminHeader");
		RequestDispatcher ts=request.getRequestDispatcher("../footer.html");
		
		td.include(request, response);
		p.print("<title></title>");
		p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
		p.print("<link rel='stylesheet' type='text/css' href='../css/searchUpdate.css'>");
		p.print("<script type='text/javascript' src='js/jquery.min.js'></script>");
	  	p.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js'></script>");
		try
		{
			BookMaster db=new BookMaster();
			ResultSet rs=db.allMemberData();
			p.print("<div class='col-sm-12' >");
			p.print("<table class='table table-bordered table-responsive table-hover ml-7' style='margin-left:130px'>");
			p.print("<tr class='bg-dark text-light'><th>member id</th><th>member name</th><th>gender</th><th>email</th><th>phone</th><th>address</th><th>total books</th><th>image</th></tr>");
			while(rs.next())
			{
				p.print("<tr class='bg-light'><td>"+rs.getString("mid")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("gender")+"</td><td>"+rs.getString("email")+"</td><td>"+rs.getString("mobile")+"</td><td>"+rs.getString("add1")+"</td><td>"+rs.getString("totalbooks")+"</td><td><img src='../images/"+rs.getString("img")+"' width='80px' height='80px' class='img rounded-circle'></td></tr>");
				
			}
			p.print("</table>");
			p.print("</div>");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
			ts.include(request, response);
	}
			
	

}
