package InFo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class InfoDAO {

	List<InfoDTO> list;

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##javaUser";
		String password = "12345";
		Connection con = null;
		try {
			con = (Connection) DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
<<<<<<< HEAD
	
	public Vector<InfoDTO> select(String id) {
=======

	public List<InfoDTO> select(String id) {
>>>>>>> branch 'master' of https://github.com/Jung0322/SwingProject.git
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<InfoDTO> list = new ArrayList<InfoDTO>();

		try {
			con = getConnection();
			String sql = "SELECT * FROM INFO WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				InfoDTO dto = new InfoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setMoney(rs.getInt("money"));
				dto.setContent(rs.getString("content"));
				dto.setSort(rs.getString("sort"));
				dto.setDay(rs.getDate("day"));
				dto.setId(rs.getString("id"));

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

	public InfoDTO search(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		InfoDTO dto = null;

		try {
			con = getConnection();

			String sql = "SELECT DAY,CONTENT, MONEY FROM INFO WHERE ID=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new InfoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setMoney(rs.getInt("money"));
				dto.setContent(rs.getString("content"));
				dto.setSort(rs.getString("sort"));
				dto.setDay(rs.getDate("day"));
				dto.setId(rs.getString("id"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
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
			pstmt.setNString(6, id);;

			int result = pstmt.executeUpdate();
			if (result > 0) {
				updateFlag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return updateFlag;

	}

	public InfoDTO noSelect(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InfoDTO dto = null;
		try {
			con = getConnection();
			String sql = "select * from info where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			dto = new InfoDTO();
			dto.setId(rs.getString("id"));
			dto.setNo(rs.getInt("no"));
			dto.setContent(rs.getString("content"));
			dto.setDay(rs.getDate("day"));
			dto.setMoney(rs.getInt("money"));
			dto.setSort(rs.getString("sort"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;

	}

	// 입력(Input)
	public Boolean Input(String date, String content, String money, String sort, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insertFlag = false;

		try {
			con = getConnection();

			String sql = "insert into InFo(no,money,content,sort,day,id) values (seq_no.nextval,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(money));
			pstmt.setString(2, content);
			pstmt.setString(3, sort);
			pstmt.setString(4, date);
			pstmt.setString(5, id);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				insertFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return insertFlag;
	}

<<<<<<< HEAD
	public Vector<InfoDTO> list(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
=======
	public Vector<InfoDTO> select() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<InfoDTO> vetList = null;

		try {
			con = getConnection();
			String sql = "select * from info";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			vetList = new Vector<InfoDTO>();

			while (rs.next()) {
				InfoDTO dto = new InfoDTO();
				dto.setDay(rs.getDate("day"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setMoney(rs.getInt("money"));
				dto.setSort(rs.getString("sort"));
				dto.setNo(rs.getInt("no"));
				vetList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return vetList;
	}
>>>>>>> branch 'master' of https://github.com/Jung0322/SwingProject.git
}
