package admin;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbo.BookMaster;

/**
 * Servlet implementation class Issue_reg
 */
@WebServlet("/servlet/Issue_reg")
public class Issue_reg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		String rrdate=request.getParameter("rdate");
		String iidate=request.getParameter("idate");
		String acc=request.getParameter("acc");
		int tb=0;
		tb=Integer.parseInt(request.getParameter("tb"));
		
		System.out.println(rrdate);
		System.out.println(iidate);
		System.out.println(acc);
		System.out.println(tb);
		String mid=request.getParameter("mid");
		try
		{
			BookMaster db=new BookMaster();
			
			
				tb=tb+1;
				
				if(tb<5 )
				{
					int x=db.issuebook(iidate, rrdate, Integer.parseInt(mid),Integer.parseInt(acc),"issued");
					int y=db.updateStatusBookCpoies("issued", Integer.parseInt(acc));		
					int z=db.updatetotalBook(Integer.parseInt(mid),tb);
					response.sendRedirect("ReturnBook?msg=issued sucessfully");
				}
				else
				{
					response.sendRedirect("IssueForm?msg=unsucessful transaction");
				}
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
