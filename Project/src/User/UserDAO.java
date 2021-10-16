package User;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;



import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import oracle.jdbc.internal.XSPrincipal.Flag;


public class UserDAO {
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

	
	public List<UserDTO> select() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<UserDTO> list = new ArrayList<UserDTO>();
		
		try {
			con = getConnection();
			String sql = "select * from membertbl";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				list.add(dto);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}

	


	public boolean join(String id, String password, String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			String sql = "insert into membertbl values(?,?,?)";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return flag;

	}

	public boolean login(String id, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		ResultSet rs = null;
		try {
			String sql = "select pw from membertbl where id = ?";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("pw").equals(password)) {
					flag = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}
		}
		return flag;
	}

	public boolean overlop(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		ResultSet rs = null;
		try {
			String sql = "select id from membertbl where id=?";
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {				
				flag = true;

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}

		}
		return flag;
	}
}
