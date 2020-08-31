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
 * Servlet implementation class AddBook
 */
@WebServlet("/servlet/AddBook")
public class AddBook extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		HttpSession hs=request.getSession();
		hs.getAttribute("id");
		BookMaster db;
		
		if(hs.isNew())
		{
			response.sendRedirect("../index.html");
		}
		RequestDispatcher head=request.getRequestDispatcher("AdminHeader");
		RequestDispatcher foot=request.getRequestDispatcher("../footer.html");
		head.include(request, response);
		String msg="";
		msg=request.getParameter("msg");
	
		
		p.print("<!DOCTYPE html>");
		p.print("<html>");
		p.print("<head>");
			p.print("<title></title>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/AddBook.css'>");
			p.print("<link rel='stylesheet' type='text/css' href='../js/bootstrap.js'>");
			p.print("<script type='text/javascript' src='js/jquery.min.js'></script>");
		p.print("</head>");
	
			p.print("<body>");
			p.print("<center>");	
						p.print("<div class='container' id='f1' >");
						p.print("<div id='div1' >");
						p.print("<h2 class='text-capitalize text-danger text-center '>Add book details</h2><hr>");
						
							p.print("<form class='form-group' method='post' action='AddBookDB' enctype ='multipart/form-data' >");
								p.print("<div class='row bg-light' style=' width:1000px' >");
								
								p.print("<div class='col-sm-12 '>");
						 		p.print("<img src='' width='150px' width='150px' id='blah'>");
						 		
								p.print("<div class='col-sm-6'>");
						 		p.print("<label for='cover' class='text-left'>book cover</label><input id='cover' type='file' name='filename' class='form-control ' placeholder='bookcover' onchange='readURL(this)' value=''>");
						 		p.print("</div>");
						 		//p.print("<div class='col-sm-6 mt-3'></div>");
						 		
						 		p.print("</div>");
									p.print("<div class='col-sm-6'>");
									p.print("<label for='isbv' class='text-left'>isbn</label><input type='number' name='isbn' class='form-control ' placeholder='isbn' id='isbn'>");
									p.print("</div>");
									p.print("<div class='col-sm-6'>");
							 
									p.print("<label for='title' class='text-left'>title</label><input type='text' name='title' class='form-control ' placeholder='title'>");
									p.print("</div>");
									p.print("<div class='col-sm-6'>");
									p.print("<label for='aid'class='text-left'>author id</label>"
											+ "<input id='aid' list='a_id' name='a_id' class='form-control ' placeholder='author id'>");
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
									
									p.print("<div class='col-sm-6 '>");
							 		p.print("<label for='pid' class='text-left'>publisher id</label><input id='pid' list='p_id' name='p_id' class='form-control ' placeholder='publisher id'  >");
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
									
									
							 		p.print("<div class='col-sm-4 '>");
									p.print("<label  for='cid' class='text-left'>category id</label><input id='cid' list='c_id' name='c_id' class='form-control  ' placeholder='category id'  >");
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
									
									
									
							 		p.print("<div class='col-sm-4'>");
							 		p.print("<label for='copy' class='text-left'>total copies</label><input type='number' name='copies' class='form-control' placeholder='copies' id='t1'>");
							 		p.print("</div>");
							 		p.print("<div class='col-sm-4 mt-4'>");
							 		p.print("<button type='button' id='b1' class=' btn btn-primary mr-7 col-5'>copy</button>");
							 		
							 		
									p.print("</div>");
									p.print("<div class='e1 col-sm-12'></div>");
								p.print("</div>");
								p.print("<div align='center'>");
								p.print("<button class='btn btn-danger col-4 mt-5 mb-7'>Add book</button></div>");
							p.print("</form>");

						p.print("</div>");
						p.print("</div></center>");
						if(request.getQueryString() != null)
						{p.print("<h1 class='text-center bg-danger text-warning' >"+msg+"</h1><br><br>");}
int acc2=0;						
try
{
	db=new BookMaster();
	acc2=db.getnextAcc();
	p.print("<input type='text' class='text-light' value='"+acc2+"' id='accc' style='border:none'>");
}
catch(Exception e)
{
	e.printStackTrace();
}

		p.print("</style>");
		p.print("</body >");
		
		String copy=request.getParameter("copies");
 		
		foot.include(request, response);
		
		
		
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
		
		p.print("<script type=\"text/javascript\">\r\n" + 
				"	\r\n" + 
				"$(function()\r\n" + 
				"{\r\n" + 
				"	$(\"#b1\").click(function()\r\n" + 
				"	{\r\n" + 
				"		var sum=0;\r\n" + 
				"		var a=[];\r\n" + 
				"		var isbn=$(\"#isbn\").val();\r\n"
				+ "		var acc2=parseInt($(\"#accc\").val());" + 
				"		var no=parseInt($(\"#t1\").val());\r\n" + 
				"		console.log(no);\r\n" + 
				"		for(var i=0;i<no;i++)\r\n" + 
				"		{\r\n" + 
				"			console.log(isbn);\r\n" + 
				"				a.push(\"<tr><td><input type='text'class='ml-3 mt-3'name='isbn1' placeholder='isbn number' value=\"+isbn+\"><td><input type='text'class='ml-3 mt-3'name='accession_no'  placeholder='accession number'  value=\"+acc2+\"><td><input type='text'class='ml-3 mt-3'name='edition' placeholder='edition'><td><input type='text'class='ml-3 mt-3' name='rate' placeholder='rate'></tr>\")	\r\n" + 
				"				acc2=acc2+1 " + 
				"		}\r\n"
				+ "			" + 
				"		$(\".e1\").html(a);\r\n" + 
				"	})\r\n" + 
				"})\r\n" + 
				"</script>");
		
		

	}
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		doGet(request, response);
	}*/

}
