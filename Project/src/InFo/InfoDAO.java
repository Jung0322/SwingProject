package InFo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InfoDAO {

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##java";
		String password = "12345";
		Connection con = null;
		try {
			con = (Connection) DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public boolean update(Date day, String content, int sort,int money,int no, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean updateFlag = false;
		
	try {
		con = getConnection();
		String sql = "update InFo set date=? and content=? and sort=? and money=? where no=? and id=?";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setDate(1, day);
		pstmt.setNString(2, content);
		pstmt.setInt(3, sort);
		pstmt.setInt(4, money);
		pstmt.setInt(5, no);
		pstmt.setNString(6, id);
		
		int result=pstmt.executeUpdate();
		if(result>0) {
			updateFlag = true;
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			pstmt.close();
			con.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	return updateFlag;
	
	}
	
}
