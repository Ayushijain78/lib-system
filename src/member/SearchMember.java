package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbo.BookMaster;

/**
 * Servlet implementation class SearchMember
 */
@WebServlet("/servlet/SearchMember")
public class SearchMember extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		String memid="",name="",email="",mobile="",img="";
		int tb=0;
		memid=request.getParameter("mid");
		try
		{
			BookMaster db=new BookMaster();
			ResultSet rs=db.searchMember(memid);
		
			if(rs.next())
			{
				memid=rs.getString("mid");
				name=rs.getString("name");
				email=rs.getString("email");
				mobile=rs.getString("mobile");
				img=rs.getString("img");
				tb=rs.getInt("totalbooks");
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		PrintWriter p=response.getWriter();
		response.setContentType("text/html");
		p.print("<div>");
		p.print("<form");
		p.print("<input type='text' name='mid'>");
		p.print("<input type='text' name='' value="+email+">");
		p.print("<input type='text' name='' value="+name+">");
		p.print("<input type='submit'>");
		p.print("</form");
		p.print("</div>");
		
		
	}

}
