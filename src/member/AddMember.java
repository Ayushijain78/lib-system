package member;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dbo.BookMaster;
import dbo.Dbcon;
import dbo.MemberDb;

/**
 * Servlet implementation class AddMember
 */
@WebServlet("/servlet/AddMember")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
maxFileSize=1024*1024*10,
maxRequestSize=1024*1024*50)

public class AddMember extends HttpServlet 
{
	@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		
		HttpSession hs=request.getSession();
		String id=(String) hs.getAttribute("id");
		String pwd1=(String) hs.getAttribute("pwd");
		String type=(String) hs.getAttribute("utype");
		if(hs.isNew())
		{
			response.sendRedirect("../index.html");
		}
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
	
		int tb=Integer.parseInt(request.getParameter("tb"));
		String add1=request.getParameter("add1");
		String pwd=request.getParameter("pwd");
		String gender=request.getParameter("gender");
		Part part=request.getPart("img");
		String filename=extractFileName(part);
		String filepath="E:\\advance java\\library\\WebContent\\images\\" + File.separator + filename;
		File fileSaveDir=new File(filepath);
		part.write(filepath + File.separator);
		
		
		
		
		try
		{
			MemberDb db=new MemberDb();
			int x=db.addMember(name, email, mobile, gender, pwd, filename, add1, filepath,tb);
			if(x==1)
			
				response.sendRedirect("AllMember?msg=memeber added successfully");
			
			else
			
				response.sendRedirect("NewMember?msg=memeber registration failed");
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	private String extractFileName(Part part)
	{
		String contentDisp=part.getHeader("content-disposition");
		String[] items=contentDisp.split(";");
		for(String s:items)
		{
			if(s.trim().startsWith("filename"))
				return s.substring(s.indexOf("=")+2, s.length()-1);
		}
		return "";
	}
}

