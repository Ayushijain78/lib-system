package member;

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
 * Servlet implementation class MemberProfile
 */
@WebServlet("/servlet/MemberProfile")
public class MemberProfile extends HttpServlet
{
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		HttpSession hs=request.getSession();
		String id=(String) hs.getAttribute("id");
		String pwd=(String) hs.getAttribute("pwd");
		String type=(String) hs.getAttribute("utype");
		
		String name="",email="",phone="",totalbooks="",gender="",img="",add="";
		try
		{
			Dbcon db=new Dbcon();
			ResultSet rs=db.getmember(id, pwd);
			if(rs.next())
			{
				
				name=rs.getString("name");
				email=rs.getString("email");
				phone=rs.getString("mobile");
				totalbooks=rs.getString("totalbooks");
				gender=rs.getString("gender");
				img=rs.getString("img");
				add=rs.getString("add1");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		RequestDispatcher foot=request.getRequestDispatcher("../footer.html");
		RequestDispatcher head=request.getRequestDispatcher("MemberHeader");
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
		
		p.print("<p class='text-left text-danger'>gender:<span class='left'>"+gender+"</span></p>");
		
		p.print("<p class='text-left text-danger'>total issued books:<span class='left'>"+totalbooks+"</span></p>");
		
		p.print("<p class='text-left text-danger'>address:<span class='left'>"+add+"</span></p>");
		
		
		p.print("</div>");
		p.print("</div>");
		
		p.print("</body>");
		p.print("</html>");
		
		
		foot.include(request, response);
		
	}

	

}
