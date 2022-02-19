package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program2 {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String title = "TEST2";
		String writerId = "newlec";
		String content = "hahaha";
		String files = "";
	
		String url = "jdbc:oracle:thin:@211.204.34.28:1521/xepdb1";
		String sql = "INSERT INTO notice (" + 
				"    title," + 
				"    writer_id," + 
				"    content," + 
				"    files" + 
				") VALUES (?,?,?,?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "NEWLEC", "tntorwndeo1!");
//		Statement st = con.createStatement();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		//실행 - 2가지 함수만 보면 된다. executeQuery? executeUpdate? 무엇으로 할 것인가?
		//prepared를 사용할 때엔 executeQuery를 쓰면 안된다. 결과집합을 전달하는 것이 아니기 때문에!
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();
		
	}
}
