package dbo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMaster extends Dbcon
{
	int x;

	public BookMaster() throws ClassNotFoundException, SQLException 
	{
		super();
	}
	public int addbook(int isbn,String title,int author_id,int publisher_id,int category_id,int copies,String filename,String filepath) throws SQLException
	{
		pt=cn.prepareStatement("insert into bookmast(isbn,aut_id,pub_id,cat_id,totalcopies,bookcover,coverpath,title)values(?,?,?,?,?,?,?,?)");
		pt.setInt(1, isbn);
		pt.setInt(2, author_id);
		pt.setInt(3, publisher_id);
		pt.setInt(4, category_id);
		pt.setInt(5, copies);
		pt.setString(6, filename);
		pt.setString(7,filepath);
		pt.setString(8, title);
		x=pt.executeUpdate();
		return x;
		
	}
	
	public int updateBook(int isbn,String title,int author_id,int publisher_id,int category_id,int copies,String filename,String filepath) throws SQLException
	{
		pt=cn.prepareStatement("update bookmast set aut_id=?,pub_id=?,cat_id=?,totalcopies=?,bookcover=?,coverpath=?,title=? where isbn=?" );
		pt.setInt(1,author_id);
		pt.setInt(2, publisher_id);
		pt.setInt(3, category_id);
		pt.setInt(4, copies);
		pt.setString(5,filename);
		pt.setString(6, filepath);
		pt.setString(7, title);
		pt.setInt(8,isbn);
		
				
		int x= pt.executeUpdate();
		return x;
	}
	
	public ResultSet searchBook(String title) throws SQLException
	{
		pt=cn.prepareStatement("select * from bookmast where isbn=? or title=? ");
		pt.setString(1, title);
		pt.setString(2, title);
		ResultSet rs=pt.executeQuery();
		return rs;
		
	}
	public ResultSet getauthor(int id) throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from author where aut_id="+id);
		return rs;
	}
	public ResultSet getauthor() throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from author ");
		return rs;
	}
	
	
	public int insertbookcopies(int isbn,int acc_no,String edition,int rate) throws SQLException
	{
		pt=cn.prepareStatement("insert into bookcopies(isbn,accession_no,edition,rate)values(?,?,?,?)");
		pt.setInt(1, isbn);
		pt.setInt(2, acc_no);
		pt.setString(3, edition);
		pt.setInt(4, rate);
		int x=pt.executeUpdate();
		return x;
		
		
	}
	
	/*SELECT bookmast.isbn,bookmast.title,bookmast.totalcopies,author.aut_name,publisher.pub_name,category.cat_name
FROM bookmast
INNER JOIN author on bookmast.aut_id=author.aut_id
INNER JOIN publisher ON bookmast.pub_id=publisher.pub_id
INNER JOIN category ON bookmast.cat_id=category.cat_id
**/
	
	public ResultSet getData() throws SQLException
	{
		ResultSet rs=st.executeQuery("SELECT bookmast.isbn,bookmast.title,bookmast.totalcopies,author.aut_name,publisher.pub_name,category.cat_name,bookmast.bookcover FROM bookmast INNER JOIN author on bookmast.aut_id=author.aut_id INNER JOIN publisher ON bookmast.pub_id=publisher.pub_id INNER JOIN category ON bookmast.cat_id=category.cat_id"); 
				
		return rs;
	}
	public ResultSet getData(String id) throws SQLException
	{
		pt=cn.prepareStatement("SELECT bookmast.isbn,bookmast.title,author.aut_name,publisher.pub_name,category.cat_name,bookmast.bookcover FROM bookmast INNER JOIN author on bookmast.aut_id=author.aut_id INNER JOIN publisher ON bookmast.pub_id=publisher.pub_id INNER JOIN category ON bookmast.cat_id=category.cat_id where bookmast.isbn=? OR bookmast.title=? OR author.aut_name=? OR publisher.pub_name=?");
		pt.setString(1, id);
		pt.setString(2, id);
		pt.setString(3, id);
		pt.setString(4, id);
		ResultSet rs=pt.executeQuery();
		return rs;
	}
	public ResultSet getcategory() throws SQLException 
	{

		ResultSet rs=st.executeQuery("select * from category ");
		return rs;
	}
	public ResultSet getpublisher() throws SQLException {
		ResultSet rs=st.executeQuery("select * from publisher ");
		return rs;
	}
	
	public ResultSet allMemberData(String mem) throws SQLException 
	{
		pt=cn.prepareStatement("SELECT member.mid,member.name,member.gender,member.add1,member.email,member.mobile,member.totalbooks,member.img,issue_reg.date_issue,issue_reg.Expected_return,issue_reg.accession_id FROM issue_reg INNER JOIN member ON issue_reg.mem_id=member.mid where member.mid=? or member.name=?");
		pt.setString(1, mem);
		pt.setString(2, mem);
		ResultSet rs=pt.executeQuery();
		return rs;	
		
	}
	
	public ResultSet allMemberData() throws SQLException 
	{
		st=cn.createStatement();
		rs=st.executeQuery("SELECT *from member");
		
		return rs;	
		
	}
	
	public ResultSet getbookcopies() throws SQLException
	{
		rs=st.executeQuery("select * from bookcopies ");
		return rs;
	}
	
	public ResultSet gettotalbooks(String s1) throws SQLException
	{
		pt=cn.prepareStatement("SELECT bookmast.isbn,bookmast.title,bookmast.bookcover,bookcopies.accession_no,bookcopies.edition,bookcopies.status,bookcopies.rate from bookmast INNER JOIN bookcopies ON bookmast.isbn=bookcopies.isbn WHERE (bookmast.isbn=? or bookmast.title=?) && bookcopies.status='available'");
		pt.setString(1, s1);
		pt.setString(2, s1);
		rs=pt.executeQuery();
		
		return rs;
	}
	public ResultSet updatestatus() throws SQLException
	{
		st=cn.createStatement();
		rs=st.executeQuery("SELECT member.mid,member.name,member.totalbooks,issue_reg.issue_id,issue_reg.Expected_return,issue_reg.accession_id,issue_reg.status,issue_reg.date_issue FROM member INNER JOIN issue_reg ON member.mid=issue_reg.mem_id");
		return rs;
	}
	
	public int issuebook(String idate,String rdate,int mid,int acc,String status) throws SQLException
	{
		pt=cn.prepareStatement("INSERT INTO `issue_reg` (`date_issue`, `mem_id`, `Expected_return`, `accession_id`, `status`) VALUES (?,?,?,?,?)");
		pt.setString(1,idate);
		pt.setInt(2, mid);
		pt.setString(3, rdate);
		pt.setInt(4,acc);
		pt.setString(5,status);
		
		x=pt.executeUpdate();
		return x;
	}
	public ResultSet searchbook(int acc) throws SQLException 
	{
		st=cn.createStatement();
		rs=st.executeQuery("select * from bookcopies where accession_no="+acc);
		return rs;
	}
	public ResultSet searchMember(String memid) throws SQLException 
	{

		pt=cn.prepareStatement("select * from member where mid=?");
		pt.setString(1, memid);
		rs=pt.executeQuery();
		return rs;
	}
	
	public int updateStatusBookCpoies(String status,int acc) throws SQLException
	{
		
		pt=cn.prepareStatement("update bookcopies set status=? where accession_no=?");
		pt.setString(1,status);
		pt.setInt(2, acc);
		int x=pt.executeUpdate();
		return x;
	}
	
	public int updatetotalBook(int mid,int tb) throws SQLException
	{
		pt =cn.prepareStatement("update member set totalbooks=? where mid=?");
		pt.setInt(1, tb);
		pt.setInt(2, mid);
		int x=pt.executeUpdate();
		return x;
	}

	
	
	/*public int returnbook()
	{
		pt=cn.prepareStatement("insert into return_reg()values()");
	}*/
	public ResultSet IssueData(int issueId) throws SQLException
	{
		pt=cn.prepareStatement("SELECT issue_reg.issue_id,issue_reg.mem_id,member.name,member.totalbooks,issue_reg.date_issue,issue_reg.Expected_return,issue_reg.accession_id from issue_reg INNER JOIN member ON issue_reg.mem_id=member.mid where issue_reg.issue_id=? or issue_reg.accession_id=? or member.mid=?");
		pt.setInt(1,issueId);
		pt.setInt(2,issueId);
		pt.setInt(3,issueId);
		
		rs=pt.executeQuery();
		return rs;
	}
	
	public ResultSet IssueData() throws SQLException
	{
		st=cn.createStatement();
		rs=st.executeQuery("SELECT issue_reg.issue_id,issue_reg.mem_id,member.name,member.totalbooks,issue_reg.date_issue,issue_reg.Expected_return,issue_reg.accession_id from issue_reg INNER JOIN member ON issue_reg.mem_id=member.mid");
		return rs;
	}
	
	public int returnBook(int issueid,String rdate,int fee,int overdays,String feeStatus) throws SQLException
	{
		pt=cn.prepareStatement("insert into return_reg(issue_id,return_date,defaulterfee,overdays,feepay)values(?,?,?,?,?)");
		pt.setInt(1, issueid);
		pt.setString(2, rdate);
		pt.setInt(3, fee);
		pt.setInt(4, overdays);
		pt.setString(5, feeStatus);
		x=pt.executeUpdate();
		return x;
	}
	
	public int updatestatusissuebook( int isid ) throws SQLException
	{
		pt=cn.prepareStatement("delete from issue_reg where issue_id=?");
		
		pt.setInt(1, isid);
		x=pt.executeUpdate();
		return x;
		
	}
	
	public int getnextAcc() throws SQLException
	{
		
		int acc=1;
		st=cn.createStatement();
		rs=st.executeQuery("select MAX(accession_no) from bookcopies ");
		if(rs.next())
			acc+=rs.getInt(1);
		
		return acc;
		
		
	}
	

	
}
