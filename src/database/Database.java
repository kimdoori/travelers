package database;

import java.sql.*;

import model.Corn;

public class Database {

	private String url = "jdbc:mysql://localhost:3306/travelers";
	private String id = "root";
	private String pw = "doori0228!";

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public Object executQuery(String query, Object object) {

		Object result = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);

			switch (query) {
			case "insert_corn":
				result = insert_corn((Corn) object);
				break;
			case "update_corn":
				result = update_corn((Corn) object);
				break;
			case "delete_corn":
				result = delete_corn((Corn) object);
				break;
			case "select_corn":
				result = select_corn((Corn) object);
				break;
			}


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결에 실패했습니다.");
			result = null;
			
		} finally {
			// PreparedStatement 객체 해제
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			// Connection 해제
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}

		return result;
	}

	public boolean insert_corn(Corn corn) {

		boolean result = false;
		try {
			String sql = "insert into corn values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, corn.getId());
			pstmt.setString(2, corn.getPw());
			pstmt.setString(3, corn.getName());
			pstmt.setString(4, corn.getNickname());
			pstmt.setString(5, corn.getBirth());
			pstmt.setString(6, corn.getPhone());
			pstmt.setInt(7, 0);

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("새로운 회원 추가에 성공했습니다.");

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("새로운 회원 추가에 실패했습니다.");
			// 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
			result = false;
		}
		
		return result;
	}
	
	public boolean update_corn(Corn corn) {

		boolean result = false;
		try {
			String sql = "update corn set (pw,name,nickname,birth,phone) = (?,?,?,?,?) where id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, corn.getPw());
			pstmt.setString(2, corn.getName());
			pstmt.setString(3, corn.getNickname());
			pstmt.setString(4, corn.getBirth());
			pstmt.setString(5, corn.getPhone());
			pstmt.setString(6, corn.getId());

			pstmt.executeUpdate();

			conn.commit();

			System.out.println("회원 정보 수정에 성공했습니다.");

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 수정에 실패했습니다.");
			result = false;
		}
		
		return result;
	}
	
	public boolean delete_corn(Corn corn) {

		boolean result = false;
		try {
			String sql = "delete from corn where id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, corn.getId());
			
			pstmt.executeUpdate();

			conn.commit();

			System.out.println("회원 삭제에 성공했습니다.");

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 삭제에 실패했습니다.");
			result = false;
		}
		
		return result;
	}
	
	public ResultSet select_corn(Corn corn) {
		ResultSet rs = null;

		try {
			String sql = "select * from corn where id = ? or name = ? or nickname = ?"; // sql 쿼리

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, corn.getId());
			pstmt.setString(2, corn.getName());
			pstmt.setString(3, corn.getNickname());
			
			
			rs = pstmt.executeQuery();
			
			System.out.println("회원 조회에 성공했습니다.");


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 조회에 실패했습니다.");
			rs = null;
		}
		
		return rs;
	}
	
	
	
	
	
}
