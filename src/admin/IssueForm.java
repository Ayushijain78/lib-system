package admin;

import java.awt.print.Book;
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
import javax.websocket.SendResult;

import dbo.BookMaster;

/**
 * Servlet implementation class IssueForm
 */
@WebServlet("/servlet/IssueForm")
public class IssueForm extends HttpServlet 
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		
		PrintWriter p=res.getWriter();
		res.setContentType("text/html");
		
		RequestDispatcher rd=req.getRequestDispatcher("AdminHeader");
		RequestDispatcher fot=req.getRequestDispatcher("../footer.html");
		rd.include(req, res);
		String acc=req.getParameter("acc");
		HttpSession hs=req.getSession();
		String mid=req.getParameter("mid");
		
	
		
		BookMaster db;
		
		p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
		p.print("<link rel='stylesheet' type='text/css' href='../css/AddBook.css'>");
		p.print("<script type='text/javascript' src='../js/bootstrap.js'></script>");
		p.print("<script type='text/javascript' src='../js/jquery.min.js'></script>");
	
		
		p.print("<div class='bg-light'>");
		p.print("<div class='' align='center'>"
				+ "<div class=''>"
				+ "<form>"
				+ "<div class='col-sm-5'>"
				+ "<label>enter member id</label>"
				+ "<input type='text' name='mid' class='form-control' placeholder='enter id'>"
				+ "<input tyep='text' name='acc_id' value='"+acc+"'  class='bg-light' style='border:2px solid #f8f9fa; color:white'>"
				+ "<center><input type='submit' class=' btn btn-primary mt-1 mb-6 col-sm-7' value='search'></center>"
				+ "</div>"
				+ "</form>"
				+ "</div>"
				+ "</div></div>");
		
		
		
		
	String name="",email="",mobile="",img="";
	int tb=0,mid1=0;
	try
	{
		db=new BookMaster();
		ResultSet rs=db.searchMember(mid);
		if(rs.next())
		{
			name=rs.getString("name");
			email=rs.getString("email");
			mobile=rs.getString("mobile");
			img=rs.getString("img");
			tb=rs.getInt("totalbooks");
			mid1=rs.getInt("mid");
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	DateTimeFormatter my = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
    
	 LocalDate ld = LocalDate.now();
	 String idate=my.format(ld);
	 String msg="";
	 msg=req.getParameter("msg");
	 LocalDate date1=ld.plusDays(15);
	 String rdate=my.format(date1);
	 String acc_id=req.getParameter("acc_id");
	
		p.print( "<div class='container' align ='center' style=' width:1000px;'>"
			+ "<div class='bg-light'>"
			+ "<form action='Issue_reg' method='post'>"
			+ "<div class='col-sm-12'>"
			+ "<img src='../images/"+img+"' class='img rounded-circle' width='200px' height='200'>"
			+ "</div>"
			+ "<div class='row'>"
			+ "<div class='col-sm-3'>"
			+ "<label>member id</label>"
			+ "<input type='text' name='mid' class='form-control' value="+mid1+" readonly>"
			+ "</div>"
			+ "<div class='col-sm-3'>"
			+ "<label class='text-left'>name</label>"
			+ "<input type='text' name='mid1' class='form-control' value="+name+" readonly>"
			+ "</div>"
			+ "<div class='col-sm-3'>"
			+ "<label class='text-left'>email</label>"
			+ "<input type='text' class='form-control' name='email' value='"+email+"' readonly>"
			+ "</div>"
			+ "<div class='col-s-3 ml-4'>"
			+ "<label>total books</label>"
			+ "<input type='text' class='form-control' name='tb' value="+tb+" readonly >"
			+ "</div>"
			+ "<div class='col-sm-3 mt-4'>"
			+ "<label issue date></label>"
			+ "<input type='text' name='idate' class='form-control' value='"+idate+"' readonly>"
			+"</div>"
			+ "<div class='col-sm-3'>"
			+ "<label>Expected return date</label>"
			+ "<input type='text' class='form-control' name='rdate' value="+rdate+" readonly>"
			+ "</div>"
			+ "<div class='col-sm-3'>"
			+ "<label>accession number</label>"
			+ "<input type='text' class='form-control' name='acc' value='"+acc_id+"' readonly>"
			+ "</div>"
			+ "<div class='col mt-3'>"
			+ "<center><input type='submit' class='btn btn-warning  mt-3 col-sm-10' value='issue book' style='float:left'></center>"
			+ "</div>"
			+ "</form>"
			+ "</div></div>");
		 
	p.print("</div>");
		fot.include(req, res);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*String rrdate=request.getParameter("rdate");
		String iidate=request.getParameter("idate");
		
		String msg="",name="";
		int tb=0,mid1 = 0;
		
		String mid=request.getParameter("mid");
		try
		{
			BookMaster db=new BookMaster();
			
			
			ResultSet rs=db.searchMember(mid);
			
			if(rs.next())
			{
				tb=rs.getInt("totalbooks");

				tb=tb+1;
				
				if(tb<5 )
				{
					int x=db.issuebook(iidate, rrdate, Integer.parseInt(mid),Integer.parseInt(acc),"issued");
					int y=db.updateStatusBookCpoies("issued", Integer.parseInt(acc));		
					int z=db.updatetotalBook(Integer.parseInt(mid),tb);
					response.sendRedirect("ReturnBook");
				}
				else
				{
					p.print("<h1>no book can issue you</h1> ");
				}
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		DateTimeFormatter my = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	    
		 LocalDate ld = LocalDate.now();
		 String idate=my.format(ld);
		 
		 LocalDate date1=ld.plusDays(30);
		 String rdate=my.format(date1);
		
		
		p.print("<title></title>");
		p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
		p.print("<link rel='stylesheet' type='text/css' href='../css/AddBook.css'>");
		p.print("<script type='text/javascript' src='../js/bootstrap.js'></script>");
		p.print("<script type='text/javascript' src='../js/jquery.min.js'></script>");
	
		
		
		
		p.print("<center>");	
		p.print("<div class='container' id='f1' style='width:500px'>");

			p.print("<div id='div1' align='center'>");
			p.print("<form method=''>");
					
						p.print("<div class='row' >");
									
						
						p.print("<div class='col-sm-6'>");
						p.print("<label>member id</label>");
						p.print("<input list='nam' name='mid' id='namr' class='form-control' placeholder='member id' >");
						
						p.print("<datalist id='nam'>");
						try
						{
							BookMaster db=new BookMaster();
							ResultSet r=db.allMemberData();
							while(r.next())
							{
								p.print("<option value="+r.getString("mid")+">"+r.getString("name")+"</option>");
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
							
								
						p.print("</datalist>");
						p.print("</div>");
						p.print("<div>"
								+ "<input type='button' class='btn btn-danger' value='click' name='b1'>"
								+ "</div>");
			
						p.print("<div class='col-sm-6'>");
						p.print("<label class='text-left'>issue date</label>");
						p.print("<input type='text' name='idate' class='form-control' placeholder='name' value="+idate+">");
						p.print("</div>");
						
						p.print("<div class='col-sm-6'>");
				 		p.print("<label>return date</label>");
						p.print("<input type='date' name='rdate' class='form-control' placeholder='expected return date' value=''>");
						p.print("</div>");
						p.print("<div class='col-sm-6'>");
						p.print("<label>return date</label>");
						p.print("<input type='text' name='acc' class='form-control' placeholder='mobile' value="+acc+">");
						p.print("</div>");
					
						p.print("<div class='col-sm-6'>");
						
						p.print("<input type='submit' class='btn btn-primary' >");
						p.print("</div>");
					p.print("</div>");
				p.print("</div>");
			p.print("</div>");
			p.print("</form>");
		p.print("</center>");*/
		
	}
	
}
