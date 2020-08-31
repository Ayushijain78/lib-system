package dbo;

import java.sql.SQLException;

public class MemberDb  extends Dbcon
{
	
	public MemberDb() throws ClassNotFoundException, SQLException 
	{
		super();
	}
	
	public int addMember(String name,String email,String mobile,String gender,String pwd,String img,String add1,String filepath,int tb) throws SQLException
	{
		pt=cn.prepareStatement("insert into member(name,email,mobile,gender,add1,img,pwd,filepath,totalbooks)values(?,?,?,?,?,?,?,?,?)");
		pt.setString(1,name);
		pt.setString(2, email);
		pt.setString(3, mobile);
		pt.setString(4, gender);
		pt.setString(5, add1);
		
		pt.setString(6, img);
		pt.setString(7,pwd);
		pt.setString(8, filepath);
		pt.setInt(9, tb);
		
		int x=pt.executeUpdate();
		return x;
	}
	public int updatepwd(String id,String pwd) throws SQLException
	{
		pt=cn.prepareStatement("update member set pwd=? where name=? or email=?");
		pt.setString(1, pwd);
		pt.setString(2, id);
		pt.setString(3, id);
		int x=pt.executeUpdate();
		return x;
	}
}
