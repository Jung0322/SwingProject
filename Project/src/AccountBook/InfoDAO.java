package AccountBook;

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
		Connection con=null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return con;
	}
	
	

}
