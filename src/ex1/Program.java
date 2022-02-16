package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@211.204.34.28:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE WHERE HIT > 9";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "NEWLEC", "tntorwndeo1!");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		//가져오기
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("title");
			String writerId = rs.getString("WRITER_ID");
			String content = rs.getString("CONTENT");
			Date regDate = rs.getDate("REGDATE");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			
				System.out.printf("ID: %d, TITLE: %s, WriterID: %s, CONTENT: %s, DATE: %s, HIT: %d, FILES: %d\n", 
						id, title, writerId, content, regDate, hit, files);
			
		}
		
		rs.close();
		st.close();
		con.close();
		
	}
}
