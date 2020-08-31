package bookDetails;

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
import dbo.Dbcon;

/**
 * Servlet implementation class ShowAll
 */
@WebServlet("/servlet/ShowAll")
public class ShowAll extends HttpServlet
{


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		RequestDispatcher rd=request.getRequestDispatcher("MemberHeader");
		RequestDispatcher rd1=request.getRequestDispatcher("../footer.html");
		rd.include(request, response);
		
		
		p.print("<!DOCTYPE html>");
		p.print("<html>");
		p.print("<head>");
			p.print("<title>show all</title>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
			p.print("<script type='text/javascript' src='../js/bootstrap.js'></script>");
			p.print("<script type='text/javascript' src='js/jquery.min.js'></script>");
		p.print("</head>");
		p.print("<style>*{text-transfrom:capitalize;}</style>");
		p.print("<body class='container-fluid'>");

		
		p.print("<div class='row ml-4'>");
			try
			{
				BookMaster db=new BookMaster();
				ResultSet rs =db.getData();
				
				while (rs.next())
				{
					
					p.print("<div class='card ml-3 mt-3' width='400px'>");
					
					p.print("<div class='card-body'><p><img src='../images/"+rs.getString(7)+"' height='200px' width='250px'></p></div>");
					p.print("<div class='card-footer'>");
						p.print("<p> isbn: <span class='ml-3 '>"+rs.getString(1)+"</p>");
						p.print("<p> title:<span class='ml-3'>"+rs.getString(2)+"</p>");
						p.print("<p> author:<span class='ml-3'>"+rs.getString(4)+"</p>");
						p.print("<p> publisher:<span class='ml-3'>"+rs.getString(5)+"</p>");
						p.print("<p> category:<span class='ml-3'>"+rs.getString(6)+"</p>");
						//p.print("<p><button class='btn btn-warning'>Issue Book</button></p>");
					p.print("</div>");
					p.print("</div>");
		
				}
				
				
				
				
				
			}
			catch(Exception e)
			{e.printStackTrace();}
					
		p.print("</div>");
				
			





		p.print("</body>");
		p.print("</html>");
				
			




//rd1.include(request, response);
	}

}
