package dbo;

import java.io.File;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class SearchUpdateDB
 */
@WebServlet("/servlet/SearchUpdateDB")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
maxFileSize=1024*1024*10,
maxRequestSize=1024*1024*50)

public class SearchUpdateDB extends HttpServlet {
	@Override

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		int isbn=Integer.parseInt(request.getParameter("isbn"));
		String title=request.getParameter("title");
		int author_id=Integer.parseInt(request.getParameter("a_id"));
		int publisher_id=Integer.parseInt(request.getParameter("p_id"));
		int category_id=Integer.parseInt(request.getParameter("c_id"));
		int copies=Integer.parseInt(request.getParameter("copies"));
		Part part=request.getPart("filename");
		String filename=extractFileName(part);
		String filepath="E:\\advance java\\library\\WebContent\\images\\" + File.separator + filename;
		File fileSaveDir=new File(filepath);
		part.write(filepath + File.separator);
		try
		{
			Dbcon d=new Dbcon();
			BookMaster db=new BookMaster();
		
			
				int x=db.updateBook(isbn,title,author_id,publisher_id,category_id,copies, filename, filepath);
				if(x>0)
					response.sendRedirect("SearchUpdate?msg=Book Updated Successfully");
				else
					response.sendRedirect("SearchUpdate?msg=Unsuccessful");
			
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
