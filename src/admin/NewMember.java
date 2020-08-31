package admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dbo.MemberDb;

@WebServlet("/servlet/NewMember")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
maxFileSize=1024*1024*10,
maxRequestSize=1024*1024*50)
public class NewMember extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		
		
		HttpSession hs=request.getSession();
		String id=(String) hs.getAttribute("id");
		if(hs.isNew())
		{
			response.sendRedirect("../index.html");
		}
		RequestDispatcher h=request.getRequestDispatcher("AdminHeader");
		RequestDispatcher f=request.getRequestDispatcher("../footer.html");
		
		h.include(request, response);
		p.print("<!DOCTYPE html>");
		p.print("<html>");
		p.print("<head>");
			p.print("<title></title>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/searchUpdate.css'>");
			p.print("<script type='text/javascript' src='js/jquery.min.js'></script>");
		  	p.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js'></script>");
		p.print("</head>");
		p.print("<style >");
		
			
		p.print("</style>");
		p.print("<body>");
					p.print("<div class='container' id='f1' align='center'>");
					p.print("<div id='div1' class='mb-6'>");
					p.print("<h2 class='card-header text-capitalize text-danger text-center mt-0'>Add new member</h2><hr>");
						p.print("<form class='form-group' method='post' action='AddMember' enctype ='multipart/form-data' >");
							
							p.print("<div align='center'>");
								p.print("<img src='../images/sample.png' width='200' heigth='200' class='bg-dark' id='blah1'>");
								p.print("<div class='text-left col-sm-6'>");
								
						 		p.print("<input type='file' name='img' class='form-control mt-4 ' placeholder='image' id='img' onchange='readURL(this)'>");
								p.print("</div>");
							p.print("</div>");
					
							p.print("<div class='row bg-light ' style='width:1000px'>");
								p.print("<div class='text-left col-sm-4'>");
								p.print("<label for='name' class='mt-2'>name</label>");
								p.print("<input type='text' name='name' class='form-control' placeholder='name' id='name'>");
								p.print("</div>");
								p.print("<div class='text-left col-sm-4'>");
						 		p.print("<label class='mt-2' for='email'>email</label>");
								p.print("<input type='email' name='email' class='form-control' placeholder='email'>");
								p.print("</div>");
								p.print("<div class='text-left col-sm-4'>");
						 		p.print("<label class ='mt-2' for='mobile'>mobile</label>");
								p.print("<input type='number' name='mobile' class='form-control' placeholder='mobile' id='mobile' >");
								p.print("</div>");
								p.print("<div class=' text-left col-sm-6'>");
								p.print("<label for='tb' class='mt-2'>total books</label>");
						 		p.print("<input type='number' name='tb' class='form-control' placeholder='total books' id='tb' value='0'>");
								p.print("</div>");
								
								p.print("<div class=' text-left col-sm-6'>");
								p.print("<label for='pwd' class='mt-2'>password</label>");
						 		p.print("<input type='password' name='pwd' id='pwd' class='form-control ' placeholder='password' >");
								p.print("</div>");
								p.print("<div class='text-left col-sm-6'>");
						 		p.print("<label for='add1' class='mt-2 '>address</label>");
								p.print("<textarea name='add1' class='form-control' placeholder='address' id='add1'></textarea>");
								p.print("</div>");
								
								p.print("<div class='text-left col-sm-6 mt-4' align='center' >");
									p.print("<label class='radio-inline mr-4' >");
										p.print("<input type='radio' name='gender' class='ml-2' checked value='female'>female</label>");
									p.print("<label class='radio-inline mr-4'>");
										p.print("<input type='radio' name='gender' class='ml-2' value='male'>male</label>");
									p.print("</div>");
							p.print("</div>");
							p.print("<div align='center'><button class='btn btn-danger col-4 mt-5'>Add member</button></div>");
						p.print("</form>");

					p.print("</div>");
					p.print("</div>");
					
				f.include(request, response);		
						
						
						

		p.print("</body>");
		p.print("</html>");
		

		
		p.print("<script>");
		p.print("function readURL(input) {\r\n" + 
				"        if (input.files && input.files[0]) {\r\n" + 
				"            var reader = new FileReader();\r\n" + 
				"\r\n" + 
				"            reader.onload = function (e) {\r\n" + 
				"                $('#blah1')\r\n" + 
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
	
}
