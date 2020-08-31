package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
 * Servlet implementation class ReturnForm
 */
@WebServlet("/servlet/ReturnForm")
public class ReturnForm extends HttpServlet {
	
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
			DateTimeFormatter my = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		    rd.include(request, response);
			 LocalDate ld = LocalDate.now();
			 String return_date=my.format(ld);
				
			String expected_return="",issue_date="",memid="",name="";
			String acc="";int tb=0;
			int issueId=Integer.parseInt(request.getParameter("issue_id"));
			int overdays=0,defaulterfee=0;
			String b11=request.getParameter("b11");
			String feepay=request.getParameter("feepay");
			
			String status="available";
			
			
			
			
			
			
			if(request.getQueryString()!=null)
			{
			try
			{
				BookMaster db=new BookMaster();
				ResultSet rs=db.IssueData(issueId);
					if(rs.next())
				{
					issue_date=rs.getString(5);
					acc=rs.getString(7);
					expected_return=rs.getString(6);
					memid=rs.getString(2);
					name=rs.getString(3);
					tb=rs.getInt(4);
					
					
					System.out.println(return_date);
					System.out.println(expected_return);
					
					String date[]=return_date.split("-");
					
					
					int size=date.length;
					int d1[]=new int[size];
						
						d1[0] = Integer.parseInt(date[0]);
					
					
					String date1[]=expected_return.split("-");
					int size1=date1.length;
					int d2[]=new int[size1];
					
						d2[0] = Integer.parseInt(date1[0]);
					
					if(d1[0]>=d2[0])
					{
						overdays=d1[0]-d2[0];
						defaulterfee=20*overdays;
						System.out.println(defaulterfee);
						System.out.println(overdays);
						
						
					}
					
					/*tb-=1;
					int y=db.updateStatusBookCpoies(status, Integer.parseInt(acc));		
					int z=db.updatetotalBook(Integer.parseInt(memid),tb);*/
					

				}
			}
			
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			
				/*try {
				BookMaster db=new BookMaster();
				//int x=db.returnBook(Integer.parseInt(issueId),return_date,defaulterfee, overdays, feepay);
				if(x==1)
				{
					tb-=1;
					if(tb>0)
					{   int y=db.updateStatusBookCpoies(status, Integer.parseInt(acc));		
						int z=db.updatetotalBook(Integer.parseInt(memid),tb);
						int a=db.updatestatusissuebook(Integer.parseInt(issueId));
					}
				}
				}
				catch (Exception e) {
					e.printStackTrace();// TODO: handle exception
				}*/
			
			
			p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/AddBook.css'>");
			p.print("<script type='text/javascript' src='../js/bootstrap.js'></script>");
			p.print("<script type='text/javascript' src='../js/jquery.min.js'></script>");
			
			p.print("<div class='container' >");
			
			p.print("<div class='bg-light col-sm-12' >");
				p.print("<form action='Return_reg' method='post'>");
					p.print("<div class='row'>");
						p.print("<div class='col-sm-4'>");
							p.print("<label class='text-left' >member id</label>");
							p.print("<input class='form-control' type='text' name='mid' value="+memid+">");

						p.print("</div>");
						p.print("<div class='col-sm-4'>");
							p.print("<label class='text-left'>member name</label>");
							p.print("<input class='form-control' type='text' name='name' value="+name+">");

						p.print("</div>");
						p.print("<div class='col-sm-4'>");
						p.print("<label class='text-left'>issue id</label>");
						p.print("<input class='form-control' type='text' name='issid' value="+issueId+">");

						p.print("</div>");
						p.print("<div class='col-sm-4'>");
							p.print("<label class='text-left'>total books</label>");
							p.print("<input class='form-control' type='text' name='tb' value="+tb+">");

						p.print("</div>");
						p.print("<div class='col-sm-4'>");
							p.print("<label class='text-left'>issue date</label>");
							p.print("<input class='form-control' type='text' name='issuedate' value="+issue_date+">");

						p.print("</div>");
						p.print("<div class='col-sm-4'>");
							p.print("<label class='text-left'>return date</label>");
							p.print("<input class='form-control' type='text' name='returndate' value="+return_date+">");

						p.print("</div>");

						p.print("<div class='col-sm-4'>");
							p.print("<label class='text-left'>expected return date</label>");
							p.print("<input class='form-control' type='text' name='exreturndate' value="+expected_return+">");
						p.print("</div>");

						p.print("<div class='col-sm-4 mt-3'>");
						p.print("<label>overdays</label>");
						p.print("<input class='text-danger col-sm-2' readonly name='overdays' value="+overdays+">");

						p.print("</div>");
						p.print("<div class='col-sm-4 mt-3'>");
							p.print("<label>defaulterfee</label>");
							p.print("<input class='text-danger col-sm-2' readonly name='defee' value="+defaulterfee+">");
						p.print("</div>");
						p.print("<div class='col-sm-4 mt-3'>");
							p.print("<label class='checkbox-inline'><input type='checkbox' name='feepay' id='c1' value='' >paid</label>");
						p.print("</div>");
						p.print("<div class='col-sm-4'>");
							
							p.print("<input class='btn btn-danger' type='submit' value='return book'>");

						p.print("</div>");
					p.print("</div>");
				p.print("</form>");
			p.print("</div>");
		p.print("<div>");
		
		p.print("<script type=\"text/javascript\">\r\n" + 
				"	\r\n" + 
				"	 $(document).ready(function(){\r\n" +
				"             "+
				"        $('input[type=\"checkbox\"]').click(function(){\r\n" + 
				"            if($(this).prop(\"checked\") == true){\r\n" + 
				"                $(this).attr(\"value\",\"paid\");\r\n" + 
				"            }\r\n" + 
				"            else if($(this).prop(\"checked\") == false){\r\n" + 
				"                 $(this).attr(\"value\",\"due\");\r\n" + 
				"            }\r\n" + 
				"        });\r\n" + 
				"    });\r\n" + 
				"</script>");
		
		
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		doGet(request, response);
	}
}
