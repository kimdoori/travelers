package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Corn;
import model.Pop;

public class PopCornDAO {

	private static PopCornDAO instance;

	private Connection connection;

	private PopCornDAO() {

		try {
			System.out.println("연결");

			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/travelers?useUnicode=true&characterEncoding=utf8", "root",
					"doori0228!");

		} catch (SQLException e) {

			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 없습니디ㅏ.");
			e.printStackTrace();

		}

	}

	public static PopCornDAO getInstance() {

		if (instance == null)

			instance = new PopCornDAO();

		return instance;

	}

	// 게시글 추가 함수
	public void insertPop(Pop pop) {

		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement("insert into pop(title,location,content) values(?,?,?)");

			pstmt.setString(1, pop.getTitle());
			pstmt.setString(2, pop.getLocation());
			pstmt.setString(3, pop.getContent());

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("게시글 추가에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// 게시글 업데이트 함수
	public void updatePop(Pop pop) {

		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement("update pop set title = ?, location = ?, "

					+ "content = ? where id = ?");

			pstmt.setInt(4, pop.getId());
			pstmt.setString(1, pop.getTitle());
			pstmt.setString(2, pop.getLocation());
			pstmt.setString(3, pop.getContent());

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("게시글 업데이트에 실패했습니다.");
			e.printStackTrace();
		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// 유저의 게시글 전체 삭제
	public void deleteAllPop(String corn_id) {

		PreparedStatement pstmt = null;

		try {

			pstmt = connection.prepareStatement("delete from pop where corn_id = ?");

			pstmt.setString(1, corn_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("전체 게시글 삭제에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// 게시글 지정 삭제
	public void deletePop(int pop_id) {

		PreparedStatement pstmt = null;

		try {

			pstmt = connection.prepareStatement("delete from pop where id = ?");

			pstmt.setInt(1, pop_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("게시글 삭제에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// 지정한 id값을 가진 게시글 조회
	public Pop selectOnePop(int pop_id) {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		Pop pop = new Pop();

		try {

			pstmt = connection.prepareStatement("select * from pop where id = ?");

			pstmt.setInt(1, pop_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				pop.setId(rs.getInt("id"));
				pop.setTitle(rs.getString("title"));
				pop.setLocation(rs.getString("location"));
				pop.setContent(rs.getString("content"));
				pop.setLike_num(rs.getInt("like_num"));
				pop.setReg_Date(rs.getString("reg_date"));

			}

		} catch (SQLException e) {

			System.out.println("게시글 조회에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return pop;

	}

	// 모든 게시물 조회
	public List<Pop> selectAllPop() {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Pop> list = new ArrayList<Pop>();

		try {

			pstmt = connection.prepareStatement("select * from pop");

			rs = pstmt.executeQuery();

			while (rs.next())

			{
				Pop pop = new Pop();

				pop.setId(rs.getInt("id"));
				pop.setTitle(rs.getString("title"));
				pop.setLocation(rs.getString("location"));
				pop.setContent(rs.getString("content"));
				pop.setLike_num(rs.getInt("like_num"));
				pop.setReg_Date(rs.getString("reg_date"));

				list.add(pop);

			}

		} catch (SQLException e) {

			System.out.println("모든 게시글 조회에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return list;

	}

	// 유저의 게시물 조회
	public List<Pop> selectAllPop(String corn_id) {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Pop> list = new ArrayList<Pop>();

		try {

			pstmt = connection.prepareStatement("select * from pop where corn_id = ?");
			pstmt.setString(1, corn_id);

			rs = pstmt.executeQuery();

			while (rs.next())

			{
				Pop pop = new Pop();

				pop.setId(rs.getInt("id"));
				pop.setTitle(rs.getString("title"));
				pop.setLocation(rs.getString("location"));
				pop.setContent(rs.getString("content"));
				pop.setLike_num(rs.getInt("like_num"));
				pop.setReg_Date(rs.getString("reg_date"));

				list.add(pop);

			}

		} catch (SQLException e) {

			System.out.println("모든 게시글 조회에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return list;

	}

	// 회원 등록
	// 0-> 성공 1->이미 있는 회원 40->실패
	public int insertCorn(Corn corn) {

		PreparedStatement pstmt = null;

		int return_code = -1;

		try {
			pstmt = connection.prepareStatement("insert into corn values(?,?,?,?,?,?,?)");

			pstmt.setString(1, corn.getId());
			pstmt.setString(2, corn.getPw());
			pstmt.setString(3, corn.getName());
			pstmt.setString(4, corn.getNickname());
			pstmt.setString(5, corn.getBirth());
			pstmt.setString(6, corn.getPhone());
			pstmt.setInt(7, 0);

			pstmt.executeUpdate();
			return_code = 0;

		} catch (SQLException e) {

			System.out.println("회원 등록에 실패했습니다.");

			if (e.getErrorCode() == 1062) {// 이미 있는 회원
				return_code = 1;
			}
			// e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return return_code;

	}

	// 회원 정보 업데이트
	public void updatCorn(Corn corn) {

		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement("update corn set pw = ?, name = ?, "

					+ "nickname = ?, birth = ?, phone = ? where id = ?");

			pstmt.setString(6, corn.getId());
			pstmt.setString(1, corn.getPw());
			pstmt.setString(2, corn.getName());
			pstmt.setString(3, corn.getNickname());
			pstmt.setString(4, corn.getBirth());
			pstmt.setString(5, corn.getPhone());

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("회원 정보 수정에 실패했습니다.");
			e.printStackTrace();
		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// 회원 삭제
	public void deleteCorn(String corn_id) {

		PreparedStatement pstmt = null;

		try {

			deleteAllPop(corn_id);

			pstmt = connection.prepareStatement("delete from corn where id = ?");

			pstmt.setString(1, corn_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("회원 삭제에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// 모든 유저 조회
	public List<Corn> selectAllCorn() {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Corn> list = new ArrayList<Corn>();

		try {

			pstmt = connection.prepareStatement("select * from corn");

			rs = pstmt.executeQuery();

			while (rs.next())

			{
				Corn corn = new Corn();
				
				corn.setId(rs.getString("id"));
				corn.setPw(rs.getString("pw"));
				corn.setName(rs.getString("name"));
				corn.setNickname(rs.getString("nickname"));
				corn.setBirth(rs.getString("birth"));
				corn.setPhone(rs.getString("phone"));
				corn.setLike_num(rs.getInt("like_num"));

				list.add(corn);

			}

		} catch (SQLException e) {

			System.out.println("모든 유저 조회에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return list;

	}

	// 회원 조회
	public Corn selectOneCorn(String corn_id) {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		Corn corn = new Corn();

		try {

			pstmt = connection.prepareStatement("select * from pop where id = ?");

			pstmt.setString(1, corn_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				corn.setId(rs.getString("id"));
				corn.setPw(rs.getString("pw"));
				corn.setName(rs.getString("name"));
				corn.setNickname(rs.getString("nickname"));
				corn.setBirth(rs.getString("birth"));
				corn.setPhone(rs.getString("phone"));
				corn.setLike_num(rs.getInt("like_num"));

			}

		} catch (SQLException e) {

			System.out.println("회원 조회에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return corn;

	}

	// 검색 기능 (키워드가 들어간 유저와 게시글 )
	public List<Pop> searchPop(String keyword) {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Pop> list = new ArrayList<Pop>();

		try {

			String sql = "select * from pop where title like '%" + keyword + "%' or location like '%" + keyword
					+ "%' or content  '%" + keyword + "%'";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next())

			{
				Pop pop = new Pop();

				pop.setId(rs.getInt("id"));
				pop.setTitle(rs.getString("title"));
				pop.setLocation(rs.getString("location"));
				pop.setContent(rs.getString("content"));
				pop.setLike_num(rs.getInt("like_num"));
				pop.setReg_Date(rs.getString("reg_date"));

				list.add(pop);

			}

		} catch (SQLException e) {

			System.out.println("게시글 검색에  실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return list;

	}

	public List<Corn> searchCorn(String keyword) {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Corn> list = new ArrayList<Corn>();

		try {

			String sql = "select * from corn where id like '%" + keyword + "%' or name like '%" + keyword
					+ "%' or nickname  '%" + keyword + "%'";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next())

			{
				Corn corn = new Corn();

				corn.setId(rs.getString("id"));
				corn.setPw(rs.getString("pw"));
				corn.setName(rs.getString("name"));
				corn.setNickname(rs.getString("nickname"));
				corn.setBirth(rs.getString("birth"));
				corn.setPhone(rs.getString("phone"));
				corn.setLike_num(rs.getInt("like_num"));

				list.add(corn);

			}

		} catch (SQLException e) {

			System.out.println("회원 검색에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return list;

	}

	// 회원 좋아요
	public void updateLike(Corn corn) {

		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement("update corn set like_num = ? where id = ?");

			pstmt.setString(2, corn.getId());
			pstmt.setInt(1, corn.getLike_num() + 1);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("회원 좋아요 업데이트에 실패했습니다.");
			e.printStackTrace();
		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// 게시글 좋아요
	public void updateLike(Pop pop) {

		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement("update pop set like_num = ? where id = ?");

			pstmt.setInt(2, pop.getId());
			pstmt.setInt(1, pop.getLike_num() + 1);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("게시글 좋아요 업데이트에 실패했습니다.");
			e.printStackTrace();
		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	// 로그인 0->성공 1->비밀번호 틀림 2->없음 40->실패
	public int checkCorn(Corn corn) {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		int return_code = -1;

		try {

			pstmt = connection.prepareStatement("select * from corn where id = ?");

			pstmt.setString(1, corn.getId());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (corn.getPw().equals(rs.getString("pw")))
					return_code = 0;
				else
					return_code = 1;

			} else {
				return_code = 2;
			}

		} catch (SQLException e) {

			System.out.println("로그인 확인에 실패했습니다.");
			return_code = 40;
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return return_code;

	}

}
