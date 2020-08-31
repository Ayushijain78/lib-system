package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbo.MemberDb;

/**
 * Servlet implementation class UpdatePwd
 */
@WebServlet("/servlet/UpdatePwd")
public class UpdatePwd extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		
		
		HttpSession hs=request.getSession();
		String id=(String) hs.getAttribute("id");
		p.print(id);
		String pwd=(String) hs.getAttribute("pwd");
		String type=(String) hs.getAttribute("utype");
		String pwd1=request.getParameter("pwd1");
		String pwd2=request.getParameter("pwd2");
		
		
			try
			{
				
						MemberDb md=new MemberDb();
						int x=md.updatepwd(id, pwd1);
						
						if(x==1)
						
							response.sendRedirect("ChangePwd?msg=suceesfully");
							
						
						else
							response.sendRedirect("ChangePwd?msg=unsuceesful");
							
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
		
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
