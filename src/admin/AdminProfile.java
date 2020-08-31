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

import dbo.Dbcon;

/**
 * Servlet implementation class AdminProfile
 */
@WebServlet("/servlet/AdminProfile")
public class AdminProfile extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma","no-cache");
		response.setHeader("Expires", "0");
		
		
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		HttpSession hs=request.getSession();
		String id=(String) hs.getAttribute("id");
		String pwd=(String) hs.getAttribute("pwd");
		String type=(String) hs.getAttribute("utype");
		
		
		if(hs.isNew())
		{
			response.sendRedirect("../index.html");
		}
		
		String name="",email="",phone="",gender="",img="";
		try
		{
			Dbcon db=new Dbcon();
			ResultSet rs=db.getadmin(id, pwd);
			if(rs.next())
			{
				
				name=rs.getString("name");
				email=rs.getString("email");
				phone=rs.getString("phone");
				
				img=rs.getString("img");
				
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		RequestDispatcher foot=request.getRequestDispatcher("../footer.html");
		RequestDispatcher head=request.getRequestDispatcher("AdminHeader");
		head.include(request, response);
		
		
		
		
		p.print("<html>");
		p.print("<head>");
			p.print("<title></title>");
			p.print("<link rel='stylesheet' href='../css/bootstrap.css'>");
			p.print("<link rel='stylesheet' href='../css/profile.css'>");
			p.print("<script type='text/javascript' src='../js/bootstrap.js'></script>");
			p.print("<script src='../js/jquery.min.js'></script>");
		p.print("</head>");
		
		p.print("<body>");

		
		p.print("<div class='container-fluid' >");
		p.print("<div class='bg-light container' id='d1'>");
		p.print("<img src=../images/"+img+" class='img1 rounded-circle float-right' ><br>	");	
		p.print("<div><h3 class='text-warning'>your profile</h3><hr></div>");
		p.print("<p class='text-left text-danger'>Name:<span class='left '>"+name+"</span></p>");
		
		p.print("<p class='text-left  text-danger'>email:<span class='left'>"+email+"</span></p>");
		
		p.print("<p class='text-left  text-danger'>mobile:<span class='left'>"+phone+"</span></p>");
		
		
		
		
		
		p.print("</div>");
		p.print("</div>");
		
		p.print("</body>");
		p.print("</html>");
		
		
		foot.include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}

}
