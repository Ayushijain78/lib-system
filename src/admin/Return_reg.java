package admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbo.BookMaster;

/**
 * Servlet implementation class Return_reg
 */
@WebServlet("/servlet/Return_reg")
public class Return_reg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		/*DateTimeFormatter my = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	    
		 LocalDate ld = LocalDate.now();
		 String return_date=my.format(ld);
			
		String expected_return="",issue_date="",memid="",name="";
		String acc="";int tb=0;
		String issueId=request.getParameter("issue_id");
		int overdays=0,defaulterfee=0;
		String b11=request.getParameter("b11");
		String feepay=request.getParameter("feepay");
		
		String status="available";
		
		
		
		
		
		
		if(request.getQueryString()!=null)
		{
		try
		{
			BookMaster db=new BookMaster();
			ResultSet rs=db.IssueData(issueId);
				if(rs.next())
			{
				issue_date=rs.getString(5);
				acc=rs.getString(7);
				expected_return=rs.getString(6);
				memid=rs.getString(2);
				name=rs.getString(3);
				tb=rs.getInt(4);
				
				
				System.out.println(return_date);
				System.out.println(expected_return);
				
				String date[]=return_date.split("-");
				
				
				int size=date.length;
				int d1[]=new int[size];
					
					d1[0] = Integer.parseInt(date[0]);
				
				
				String date1[]=expected_return.split("-");
				int size1=date1.length;
				int d2[]=new int[size1];
				
					d2[0] = Integer.parseInt(date1[0]);
				
				if(d1[0]>=d2[0])
				{
					overdays=d1[0]-d2[0];
					defaulterfee=20*overdays;
					System.out.println(defaulterfee);
					System.out.println(overdays);
					
					
				}
				*/
				/*tb-=1;
				int y=db.updateStatusBookCpoies(status, Integer.parseInt(acc));		
				int z=db.updatetotalBook(Integer.parseInt(memid),tb);
				

			}
		}
		
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		*/
		
		int issueId=Integer.parseInt(request.getParameter("issid"));
		String return_date=request.getParameter("returndate");
		int defaulterfee=Integer.parseInt(request.getParameter("defee"));
		int overdays=Integer.parseInt(request.getParameter("overdays"));
		
		String feepay=request.getParameter("feepay");
		String status="available";
		
		System.out.println("isueid"+issueId);
		System.out.println("rdaye"+return_date);
		System.out.println("def"+defaulterfee);
		System.out.println("feepay"+feepay);
	
		
		
		String acc="",memid="";
		int tb=0;
		
			try {
			BookMaster db=new BookMaster();
		
			ResultSet rs=db.IssueData(issueId);
			if(rs.next())
			{
				acc=rs.getString("accession_id");
				memid=rs.getString("mem_id");
				tb=rs.getInt("totalbooks");
				
			}
			
			
			int x=db.returnBook(issueId,return_date,defaulterfee, overdays, feepay);
			if(x==1)
			{
					tb-=1;
					int y=db.updateStatusBookCpoies(status, Integer.parseInt(acc));		
					int z=db.updatetotalBook(Integer.parseInt(memid),tb);
					int a=db.updatestatusissuebook(issueId);
				response.sendRedirect("ReturnBook?msg=book returned successfully");
			}

			response.sendRedirect("ReturnBook?msg=something went wrong");
			}
			catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
