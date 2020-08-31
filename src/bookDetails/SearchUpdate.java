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
import javax.servlet.http.HttpSession;

import dbo.BookMaster;

/**
 * Servlet implementation class SearchUpdate
 */
@WebServlet("/servlet/SearchUpdate")
public class SearchUpdate extends HttpServlet
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		BookMaster db;
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		HttpSession hs=request.getSession();
		hs.getAttribute("id");
		if(hs.isNew())
		{
			response.sendRedirect("../index.html");
		}
		RequestDispatcher rd=request.getRequestDispatcher("AdminHeader");
		RequestDispatcher fd=request.getRequestDispatcher("../footer.html");
		
		
		String msg="";
		msg=request.getParameter("msg");
		String bid=request.getParameter("bid");
		
		
		rd.include(request, response);
		p.print("<!DOCTYPE html>");
		p.print("<html>");
		p.print("<head>");
			p.print("<title></title>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/searchUpdate.css'>");
			p.print("<link rel='stylesheet' type='text/css' href='../js/bootstrap.js'>");
			p.print("<script type='text/javascript' src='js/jquery.min.js'></script>");
		p.print("</head>");
		p.print("<script type='text/javascript'></script>");
			

		p.print("</script>");
		p.print("<style >");
		
		p.print("</style>");
			p.print("<body>");
			
						p.print("<div class='container' id='f1'>");
						
						p.print("<div id='div1'>");
							p.print("<div class='card card-header mt-4'>");
								p.print("<div align='center'>");
								p.print("<form><input type='text' name='bid' placeholder='search book' class='form-control col-sm-6' >");
								p.print("<input type='submit' class='btn btn-success col-sm-4 mt-3' value='search'></form>");
								p.print("</div>");
								p.print("</div>");
						int isbn=0,a_id=0,p_id=0,c_id=0,copy=0;
						String title="",cover="",coverpath="";
						try
						{   
							 db=new BookMaster();
							ResultSet rs=db.searchBook(bid);
							if(rs.next())
							{
								isbn=Integer.parseInt(rs.getString("isbn"));
								title=rs.getString("title");
								a_id=Integer.parseInt(rs.getString("aut_id"));
								p_id=Integer.parseInt(rs.getString("pub_id"));
								c_id=Integer.parseInt(rs.getString("cat_id"));
								cover=rs.getString("bookcover");
								coverpath=rs.getString("coverpath");
								copy=Integer.parseInt(rs.getString("totalcopies"));
								
							}
							else
							{
								//response.sendRedirect("SearchUpdate");
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
							
					
						p.print("<h2 class='text-capitalize text-danger text-center mt-2'>update book details</h2><hr>");
							p.print("<form class='form-group' method='post' action='SearchUpdateDB' enctype ='multipart/form-data' >");
								p.print("<div class='row'>");
									
									p.print("<div class='col-sm-6 mt-3'>");
									p.print("<label for='isbn'>isbn</label><input id='isbn' type='number' name='isbn' class='form-control ' placeholder='isbn' value="+isbn+">");
									p.print("</div>");
									
									p.print("<div class='col-sm-6 mt-3'>");
									p.print("<label for='title'>title</label><input id='title' type='text' name='title' class='form-control ' placeholder='title'  value="+title+">");
									p.print("</div>");
									
									p.print("<div class='col-sm-6 mt-3'>");
									p.print("<label for='aid'>author id</label>"
											+ "<input id='aid' list='a_id' name='a_id' class='form-control ' placeholder='author id'  value="+a_id+">");
									p.print("<datalist id='a_id'>");
									try
										{
											db=new BookMaster();
											ResultSet aut=db.getauthor();
											while(aut.next())
											{
												p.print("<option value="+aut.getString(1)+">"+aut.getString(2)+"</option>");
												
											}
										}
										catch(Exception e)
										{
											e.printStackTrace();
										}
									p.print("</datalist>");
									p.print("</div>"); 
									
									p.print("<div class='col-sm-6 mt-3'>");
							 		p.print("<label for='pid'>publisher id</label><input id='pid' list='p_id' name='p_id' class='form-control ' placeholder='publisher id'  value="+p_id+">");
							 		p.print("<datalist id='p_id'>");
									try
										{
											db=new BookMaster();
											ResultSet aut=db.getpublisher();
											while(aut.next())
											{
												p.print("<option value="+aut.getString(1)+">"+aut.getString(2)+"</option>");
												
											}
										}
										catch(Exception e)
										{
											e.printStackTrace();
										}
									p.print("</datalist>");
							 		p.print("</div>");
									
									p.print("<div class='col-sm-6 mt-3'>");
									p.print("<label  for='cid'>category id</label><input id='cid' list='c_id' name='c_id' class='form-control  ' placeholder='category id'  value="+c_id+">");
									p.print("<datalist id='c_id'>");
									try
										{
											db=new BookMaster();
											ResultSet aut=db.getcategory();
											while(aut.next())
											{
												p.print("<option value="+aut.getString(1)+">"+aut.getString(2)+"</option>");
												
											}
										}
										catch(Exception e)
										{
											e.printStackTrace();
										}
									p.print("</datalist>");
									p.print("</div>");
									
									p.print("<div class='col-sm-6 mt-3'>");
							 		p.print("<label for='copy'>total copies</label><input id='copy' type='number' name='copies' class='form-control ' placeholder='copies'  value="+copy+">");
									p.print("</div>");
									
									p.print("<div class='col-sm-6 mt-3'>");
							 		p.print("<label for='cover'>book cover</label><input id='cover' type='file' name='filename' class='form-control ' placeholder='bookcover' onchange='readURL(this)' value="+cover+">");
							 		p.print("</div>");
									
							 		p.print("<div class='col-sm-6 mt-3'>");
							 		p.print("<img src='../images/"+cover+"' width='200px' width='200px' id='blah'>");
							 		p.print("</div>");
									
									
									p.print("</div>");
									p.print("<div align='center'><button class='btn btn-danger col-4 mt-5'>update book</button></div>");
									p.print("</form>");
									//if(request.getQueryString() != null)
									//{p.print("<h1 class='text-center bg-danger text-warning' >"+msg+"</h1><br><br>");}
						p.print("</div>");
						p.print("</div>");


	
		p.print("</body >");
		fd.include(request, response);
		p.print("<script>");
		p.print("function readURL(input) {\r\n" + 
				"        if (input.files && input.files[0]) {\r\n" + 
				"            var reader = new FileReader();\r\n" + 
				"\r\n" + 
				"            reader.onload = function (e) {\r\n" + 
				"                $('#blah')\r\n" + 
				"                    .attr('src', e.target.result)\r\n" + 
				"                    .width(150)\r\n" + 
				"                    .height(200);\r\n" + 
				"            };\r\n" + 
				"\r\n" + 
				"            reader.readAsDataURL(input.files[0]);\r\n" + 
				"        }\r\n" + 
				"    }");
		
		p.print("</script>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
