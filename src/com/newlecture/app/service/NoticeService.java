package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entity.Notice;

public class NoticeService {
	private String url = "jdbc:oracle:thin:@211.204.34.28:1521/xepdb1";
	private String uid = "NEWLEC";
	private String pwd = "tntorwndeo1!";
	private String driver = "oracle.jdbc.driver.OracleDriver";

	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {
		
		int start = (page - 1) * 5 + 1;
		int end = page * 5;
		
		String sql = "SELECT * FROM NOTICE_VIEW	WHERE "+field+" LIKE ? AND NUM BETWEEN ? AND ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("title");
			String writerId = rs.getString("WRITER_ID");
			String content = rs.getString("CONTENT");
			Date regDate = rs.getDate("REGDATE");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			
			Notice notice = new Notice(
						id,
						title,
						writerId,
						content,
						regDate,
						hit,
						files
					);
			
			list.add(notice);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return list;
	}
	
	//단일값을 얻어오는 값을 "scalar값을 얻는다"고 표현한다.
		public int getCount() throws SQLException, ClassNotFoundException {
			int count = 0;
			
			String sql = "SELECT COUNT(ID) COUNT FROM NOTICE";
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, uid, pwd);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			List<Notice> list = new ArrayList<Notice>();
			
			if(rs.next()) 
				count = rs.getInt("COUNT");
				
			rs.close();
			st.close();
			con.close();
			
			return count;
		}
	
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
	
		String sql = "INSERT INTO notice (" + 
				"    title," + 
				"    writer_id," + 
				"    content," + 
				"    files" + 
				") VALUES (?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
	
		String sql = "UPDATE NOTICE " +
				"SET" +
				    " TITLE = ?," +
				    "CONTENT = ?," +
				    "FILES = ?" +
				" WHERE" +
				    " ID = ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();
		
		return 0;
	}
	
	public int delete(int id) throws ClassNotFoundException, SQLException {
		
		String sql = "DELETE NOTICE" +
				" WHERE" +
				    " ID = ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();
		
		return 0;
	}

}
