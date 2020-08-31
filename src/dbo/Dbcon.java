package dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class Dbcon {
	Connection cn;
	Statement st;
	ResultSet rs;
	PreparedStatement pt;
	public Dbcon() throws ClassNotFoundException, SQLException 
	{

		Class.forName("com.mysql.cj.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/liabrarydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		st=cn.createStatement();
	}
	public ResultSet getadmin(String id ,String pwd) throws SQLException
	{
		pt=cn.prepareStatement("select*from admin where (phone=? or name=? or email=?) and pwd=?");
		pt.setString(1,id);
		pt.setString(2,id);
		pt.setString(3,id);
		pt.setString(4,pwd);
		rs=pt.executeQuery();
		return rs;
	}
	public ResultSet getmember(String id ,String pwd) throws SQLException
	{
		pt=cn.prepareStatement("select*from member where (mobile=? or name=? or email=?) and pwd=?");
		pt.setString(1,id);
		pt.setString(2,id);
		pt.setString(3,id);
		pt.setString(4,pwd);
		rs=pt.executeQuery();
		return rs;
	}
	public ResultSet getIsbn(int isbn) throws SQLException
	{
			
			ResultSet rs=st.executeQuery("select* from bookmast where isbn="+isbn);
			
			return rs;
	}

}
