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
import javax.servlet.http.HttpSession;

import dbo.BookMaster;

/**
 * Servlet implementation class SearchBook1
 */
@WebServlet("/servlet/SearchBook1")
public class SearchBook1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession hs=request.getSession();
		String id=(String) hs.getAttribute("id");
		String pwd1=(String) hs.getAttribute("pwd");
		String type=(String) hs.getAttribute("utype");
		if(hs.isNew())
		{
			response.sendRedirect("../index.html");
		}
		
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		
		 
		String s1=request.getParameter("search");
		RequestDispatcher td=request.getRequestDispatcher("MemberHeader");
		td.include(request, response);
		p.print("<!DOCTYPE html>");
		p.print("<html>");
		p.print("<head>");
			p.print("<title></title>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/AddBook.css'>");
			p.print("<script type='text/javascript' src='../js/bootstrap.js'></script>");
			p.print("<script type='text/javascript' src='../js/jquery.min.js'></script>");
		p.print("</head>");
		p.print("<body>");
			
			p.print("<div align='center'>"
					+ "<div class='col-sm-4 pt-4 mt-7' id=div1></div>"
					+ "<div class='col-sm-4 mt-7 pb-7 pt-4 bg-dark'><form>"
					+ "<label for=search class='text-light'>search book</label>"
					+ "<input type='text' class='form-control col-sm-12' name='search'id='search'>"
					+ "<button type='submit' class='btn btn-primary mt-3'>Search</button>"
					+ "</div></form>"
					+ "</div>");
			p.print("<div class='row'>");
			
			try
			{
				BookMaster db=new BookMaster();
			//ResultSet rs=db.getData(s1);
				ResultSet rs1=db.gettotalbooks(s1);
				
				while( rs1.next() )
				{
					p.print("<div class='card ml-3 mt-3 col' >");
					
					p.print("<div class='card-body'><p><img src='../images/"+rs1.getString(3)+"' height='200px' width='250px'></p></div>");
					p.print("<div class='card-footer'>");
						p.print("<p class='isbn'> isbn: <span class='ml-3 '>"+rs1.getString(1)+"</p>");
						p.print("<p class='title'> title:<span class='ml-3'>"+rs1.getString(2)+"</p>");
						p.print("<p class='status'> status:<span class='ml-3'>"+rs1.getString(6)+"</p>");
						p.print("<p class='acc'> accession no:<span class='ml-3'>"+rs1.getString(4)+"</span></p>");
						p.print("<p class='edition'> edition:<span class='ml-3'>"+rs1.getString(5)+"</p>");
						//p.print("<button type='button' class='btn btn-primary myBtn'><a href=IssueForm?acc="+rs1.getString(4)+"><span class='text-light'>issue</span><a></button>");
						p.print("</div>");
					p.print("</div>");
				}
				p.print("</div>");
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			 
		p.print("</body>"
				+ "</html>");
		
		
		
		

	}

}
