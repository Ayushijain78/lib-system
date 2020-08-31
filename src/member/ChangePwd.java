package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbo.MemberDb;


@WebServlet("/servlet/ChangePwd")
public class ChangePwd extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		String msg="";
		RequestDispatcher rd=request.getRequestDispatcher("MemberHeader");
		RequestDispatcher rd1=request.getRequestDispatcher("../footer.html");
		rd.include(request, response);
		p.print("<!DOCTYPE html>");
		p.print("<html>");
		p.print("<head>");
			p.print("<link rel='stylesheet' type='text/css' href='../css/bootstrap.css'>");
			p.print("<script type='text/javascript' src='../js/jquery.min.js'></script>");
		  	p.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js'></script>");

		p.print("</head>");
		p.print("<style type='text/css'>");
		p.print(".bg-light\r\n" + 
				"				{\r\n" + 
				"				box-shadow: 3px 3px 3px 4px #ccc;\r\n" + 
				"				width: 50%;"+
				"				margin-top:7%;	" + 
				"				}");
		p.print("</style>");
		p.print("<body>");

			p.print("<div align='center'class='mt-7 pt-7'>");
				p.print("<div class='bg-light mt-7'>");
					p.print("<form action='UpdatePwd' method='post'>");
					p.print("<div class='form-group mt-3'>");
					p.print("<label for='pwd2'>old password</label>");
					p.print("<input type='password' name='pwd2' id='pwd2' class='mr-7'>");
					p.print("</div>");
						p.print("<div class='form-group mt-3'>");
							p.print("<label for='pwd'>new password</label>");
							p.print("<input type='password' name='pwd' id='pwd' class='mr-7'>");
						p.print("</div>");
						p.print("<div class='form-group mt-3'>");
							p.print("<label for='pwd1'>confirm password</label>");
							p.print("<input type='password' name='pwd1' id='pwd1' class='mr-4'>");
							p.print("<div id='error'></div>");
						p.print("</div>");
						p.print("<div>");
							p.print("<input type='submit' name='chnge' class='btn btn-danger mb-7 mt-3 ml-4 ' id='b1'>");
							
						p.print("</div>");
					p.print("</form>");
				p.print("</div>");
			p.print("</div>");

			if(request.getQueryString()!=null)
			{
				p.print("<div class='text-danger'>"+msg+"</div>");
			}

		p.print("</body>");
		p.print("</html>");
		p.print("<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1440 320'><path fill='#273036' fill-opacity='0.8' d='M0,32L20,53.3C40,75,80,117,120,149.3C160,181,200,203,240,224C280,245,320,267,360,240C400,213,440,139,480,128C520,117,560,171,600,192C640,213,680,203,720,170.7C760,139,800,85,840,74.7C880,64,920,96,960,133.3C1000,171,1040,213,1080,213.3C1120,213,1160,171,1200,149.3C1240,128,1280,128,1320,133.3C1360,139,1400,149,1420,154.7L1440,160L1440,320L1420,320C1400,320,1360,320,1320,320C1280,320,1240,320,1200,320C1160,320,1120,320,1080,320C1040,320,1000,320,960,320C920,320,880,320,840,320C800,320,760,320,720,320C680,320,640,320,600,320C560,320,520,320,480,320C440,320,400,320,360,320C320,320,280,320,240,320C200,320,160,320,120,320C80,320,40,320,20,320L0,320Z'></path></svg>");
		rd1.include(request, response);
		p.print("<script type='text/javascript'>");
			
		p.print("	\r\n" + 
			"	$(function ()\r\n" + 
			"	{\r\n" + 
			"		$('#b1').click(function()\r\n" + 
			"		{\r\n" + 
			"			var p1=$('#pwd').val();\r\n"
			+ "			" + 
			"			var p2=$('#pwd1').val();\r\n" + 
			"			if(p2==p1)\r\n" + 
			"			{\r\n" + 
			"				return true;\r\n" + 
			"			}\r\n" + 
			"			else\r\n" + 
			"			{\r\n" + 
			"				$('#error').html('password did not match').css('color','red');\r\n" + 
			"				return false;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"		});\r\n" + 
			"	});");
			
		p.print("</script>");
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
