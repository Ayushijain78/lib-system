package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbo.BookMaster;

/**
 * Servlet implementation class ReturnBook
 */
@WebServlet("/servlet/ReturnBook")
public class ReturnBook extends HttpServlet
{
	
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
		RequestDispatcher rd=request.getRequestDispatcher("AdminHeader");
		RequestDispatcher rd1=request.getRequestDispatcher("../footer.html");
		
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		rd.include(request, response);
		//String return_date=request.getParameter("rdate");
		p.print("<title></title>");
		p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
		p.print("<link rel='stylesheet' type='text/css' href='../css/AddBook.css'>");
		p.print("<script type='text/javascript' src='../js/bootstrap.js'></script>");
		p.print("<script type='text/javascript' src='../js/jquery.min.js'></script>");
		
		
		try
		{
			
			BookMaster db=new BookMaster();
		
			String msg=request.getParameter("msg");
			ResultSet rs=db.IssueData();
			p.print("<table border class='container table table-bordered table-hover'>");
			p.print("<tr><th>issue id</th><th>member id</th><th>member name</th><th>total books</th><th>issue date</th><th>return date</th><th> accession no</th></tr>");
				while(rs.next())
				{
					
					p.print("<tr><td>"+rs.getString(1)+"<td>"+rs.getString(2)+"<td>"+rs.getString(3)+"<td>"+rs.getString(4)+"<td>"+rs.getString(5)+"<td>"+rs.getString(6)+"<td>"+rs.getString(7)+"<td><button type='button' class='btn btn-warning'><a href=ReturnForm?issue_id="+rs.getString(1)+">return book</a></tr>");
				}
			
			p.print("</table>");
			if(request.getQueryString()!=null)
			 {
				 p.print("<h1>"+msg+"</h1>");
			
			 }
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		



	}
	

}
/*String date1 = "07/15/2016";
		String time1 = "11:00 AM";
		String date2 = "07/17/2016";
		String time2 = "12:15 AM";

		String format = "MM/dd/yyyy hh:mm a";

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		java.util.Date dateObj1 = sdf.parse(date1 + " " + time1);
		java.util.Date dateObj2 = sdf.parse(date2 + " " + time2);
		

		DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");

		long diff = dateObj2.getTime() - dateObj1.getTime();

		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		System.out.println("difference between days: " + diffDays);
*/