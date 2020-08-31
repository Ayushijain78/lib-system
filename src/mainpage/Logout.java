package mainpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/Logout")
public class Logout extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession hs=request.getSession();
		if(hs.isNew())
		{
			response.sendRedirect("../index.html");
		}
		hs.removeAttribute("id");
		hs.invalidate();
		response.sendRedirect("../index.html");
	}

}

