package mainpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbo.Dbcon;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/servlet/CheckLogin")
public class CheckLogin extends HttpServlet
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		String uid=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String type=request.getParameter("utype");
		HttpSession hs=request.getSession();
		hs.setAttribute("id", uid);
		hs.setAttribute("pwd", pwd);
		hs.setAttribute("utype", type);
		
		try
		{
			Dbcon db=new Dbcon();
			ResultSet rs=db.getadmin(uid, pwd);
			ResultSet rm=db.getmember(uid, pwd);
			if(rs.next())
			{	
				String name=rs.getString("name");
				String pwd1=rs.getString("pwd");
				
				hs.setAttribute("name", name);
				if(type.equalsIgnoreCase("admin")&&pwd.equalsIgnoreCase(pwd1))
					
					response.sendRedirect("AdminProfile");
				
				else
				
					response.sendRedirect("../index.html?msg=invalid user");
				
			}
			if(rm.next())
			{
				String name=rm.getString("name");
				String pwd1=rm.getString("pwd");
				hs.setAttribute("name", name);
				if(type.equalsIgnoreCase("member")&&pwd.equalsIgnoreCase(pwd1))
				
					response.sendRedirect("MemberProfile");
				
				else
				
					response.sendRedirect("../index.html?msg=invalid user");
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}

}
