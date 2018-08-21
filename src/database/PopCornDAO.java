package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Corn;
import model.Location;
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
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();

		}

	}

	public static PopCornDAO getInstance() {

		if (instance == null)

			instance = new PopCornDAO();

		return instance;

	}

	// 게시글 추가 함수
	// 0-> 성공 1->실패
	public int insertPop(Pop pop, String corn_id) {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		int return_code = -1;

		try {

			pstmt = connection
					.prepareStatement("insert into pop(corn_id,title,tag,location,reason,content) values(?,?,?,?,?,?)");

			pstmt.setString(1, corn_id);
			pstmt.setString(2, pop.getTitle());
			pstmt.setString(3, pop.getTag());
			pstmt.setString(4, pop.getLocation());
			pstmt.setString(5, pop.getReason());
			pstmt.setString(6, pop.getContent());

			pstmt.executeUpdate();

			pstmt2 = connection.prepareStatement("SELECT LAST_INSERT_ID();");
			rs = pstmt2.executeQuery();

			String[] photo = pop.getPhoto();

			if (rs.next()) {

				int id = rs.getInt("LAST_INSERT_ID()");
				for (int i = 0; i < 3; i++) {
					// System.out.println(id);
					// System.out.println(photo[i]);

					if (photo[i].equals(""))
						continue;
					insertPopPhoto(photo[i], id);
				}

			}

			return_code = 0;

		} catch (SQLException e) {

			System.out.println("게시글 추가에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return return_code;

	}

	public int insertPopPhoto(String photo, int id) {

		PreparedStatement pstmt = null;

		int return_code = -1;

		try {
			pstmt = connection.prepareStatement("insert into pop_detail values(?,?)");

			pstmt.setInt(1, id);
			pstmt.setString(2, photo);

			pstmt.executeUpdate();

			return_code = 0;

		} catch (SQLException e) {

			System.out.println("사진 추가에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;

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

	public void deletePopPhoto(int id) {
		PreparedStatement pstmt = null;

		try {

			pstmt = connection.prepareStatement("delete from pop_detail where id = ?");

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("사진 삭제에 실패했습니다.");
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
	public int updatePop(Pop pop, String corn_id) {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		int return_code = -1;

		try {
			pstmt = connection
					.prepareStatement("update pop set title = ?, tag = ?, location = ?, reason = ?, content = ? where id = ?");

			pstmt.setString(1, pop.getTitle());
			pstmt.setString(2, pop.getTag());
			pstmt.setString(3, pop.getLocation());
			pstmt.setString(4, pop.getReason());
			pstmt.setString(5, pop.getContent());
			pstmt.setInt(6, pop.getId());

			pstmt.executeUpdate();

			String[] photo = pop.getPhoto();

			deletePopPhoto(pop.getId());

			for (int i = 0; i < 3; i++) {
				if (photo[i].equals(""))
					continue;
				insertPopPhoto(photo[i], pop.getId());

			}

			return_code = 0;

		} catch (SQLException e) {

			System.out.println("게시글 추가에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return return_code;
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
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		Pop pop = new Pop();

		try {

			pstmt = connection.prepareStatement("select * from pop where id = ? ");
			pstmt.setInt(1, pop_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				pstmt2 = connection.prepareStatement("select * from corn where id = ?");
				pstmt2.setString(1, rs.getString("corn_id"));
				rs2 = pstmt2.executeQuery();

				if (rs2.next()) {

					pop.setId(rs.getInt("id"));
					pop.setCorn_id(rs.getString("corn_id"));
					pop.setCorn_name(rs2.getString("name"));
					pop.setTitle(rs.getString("title"));
					pop.setTag(rs.getString("tag"));
					pop.setReason(rs.getString("reason"));
					pop.setLocation(rs.getString("location"));
					pop.setContent(rs.getString("content"));
					pop.setLike_num(rs.getInt("like_num"));
					pop.setReg_Date(rs.getString("reg_date"));
					pop.setComment_num(getPopComment(rs.getInt("id")));

					pstmt3 = connection.prepareStatement("select * from pop_detail where id = ?");
					pstmt3.setInt(1, rs.getInt("id"));
					rs3 = pstmt3.executeQuery();

					String[] photo = new String[3];
					int i = 0;
					while (rs3.next()) {
						photo[i] = rs3.getString("photo");
						i++;
					}
					pop.setPhoto(photo);

				}

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
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}

		return pop;

	}

	// 모든 게시물 조회
	public List<Pop> selectAllPop() {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		List<Pop> list = new ArrayList<Pop>();

		try {

			pstmt = connection.prepareStatement("select * from pop order by reg_date desc ");

			rs = pstmt.executeQuery();

			while (rs.next()) {

				pstmt2 = connection.prepareStatement("select * from corn where id = ?");
				pstmt2.setString(1, rs.getString("corn_id"));
				rs2 = pstmt2.executeQuery();

				if (rs2.next()) {

					Pop pop = new Pop();

					pop.setId(rs.getInt("id"));
					pop.setCorn_id(rs.getString("corn_id"));
					pop.setCorn_name(rs2.getString("name"));
					pop.setTitle(rs.getString("title"));
					pop.setTag(rs.getString("tag"));
					pop.setReason(rs.getString("reason"));
					pop.setLocation(rs.getString("location"));
					pop.setContent(rs.getString("content"));
					pop.setLike_num(rs.getInt("like_num"));
					pop.setReg_Date(rs.getString("reg_date"));
					pop.setComment_num(getPopComment(rs.getInt("id")));

					pstmt3 = connection.prepareStatement("select * from pop_detail where id = ?");
					pstmt3.setInt(1, rs.getInt("id"));
					rs3 = pstmt3.executeQuery();

					String[] photo = new String[3];
					int i = 0;
					while (rs3.next()) {
						photo[i] = rs3.getString("photo");
						i++;
					}
					pop.setPhoto(photo);

					list.add(pop);

				}

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
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}

		return list;

	}

	// 유저의 게시물 조회
	public List<Pop> selectAllPop(String corn_id) {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;

		List<Pop> list = new ArrayList<Pop>();

		try {

			pstmt = connection.prepareStatement("select * from pop where corn_id = ? order by reg_date desc");
			pstmt.setString(1, corn_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				pstmt2 = connection.prepareStatement("select * from corn where id = ?");
				pstmt2.setString(1, rs.getString("corn_id"));
				rs2 = pstmt2.executeQuery();

				if (rs2.next()) {

					Pop pop = new Pop();

					pop.setId(rs.getInt("id"));
					pop.setCorn_id(rs.getString("corn_id"));
					pop.setCorn_name(rs2.getString("name"));
					pop.setTitle(rs.getString("title"));
					pop.setTag(rs.getString("tag"));
					pop.setReason(rs.getString("reason"));
					pop.setLocation(rs.getString("location"));
					pop.setContent(rs.getString("content"));
					pop.setLike_num(rs.getInt("like_num"));
					pop.setReg_Date(rs.getString("reg_date"));
					pop.setComment_num(getPopComment(rs.getInt("id")));

					pstmt3 = connection.prepareStatement("select * from pop_detail where id = ?");
					pstmt3.setInt(1, rs.getInt("id"));
					rs3 = pstmt3.executeQuery();

					String[] photo = new String[3];
					int i = 0;
					while (rs3.next()) {
						photo[i] = rs3.getString("photo");
						i++;
					}
					pop.setPhoto(photo);

					list.add(pop);

				}

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
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}

		return list;

	}

	// 회원 등록
	// 0-> 성공 1->이미 있는 회원 2->실패
	public int insertCorn(Corn corn) {

		PreparedStatement pstmt = null;

		int return_code = -1;

		try {
			pstmt = connection.prepareStatement("insert into corn values(?,?,?,?,?,?,?,?)");

			pstmt.setString(1, corn.getId());
			pstmt.setString(2, corn.getPw());
			pstmt.setString(3, corn.getName());
			pstmt.setString(4, corn.getNickname());
			pstmt.setString(5, corn.getBirth());
			pstmt.setString(6, corn.getPhone());
			pstmt.setInt(7, 0);
			pstmt.setString(8, "image/none.png");

			pstmt.executeUpdate();
			return_code = 0;

		} catch (SQLException e) {

			System.out.println("회원 등록에 실패했습니다.");
			return_code = 2;

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
	public int updateCorn(Corn corn) {

		PreparedStatement pstmt = null;

		int return_code = -1;

		try {
			pstmt = connection.prepareStatement(
					"update corn set pw = ?,name = ?,nickname = ?,birth = ?, phone = ?, profile= ? where id = ? ");

			pstmt.setString(7, corn.getId());
			pstmt.setString(1, corn.getPw());
			pstmt.setString(2, corn.getName());
			pstmt.setString(3, corn.getNickname());
			pstmt.setString(4, corn.getBirth());
			pstmt.setString(5, corn.getPhone());
			pstmt.setString(6, corn.getProfile());

			pstmt.executeUpdate();
			return_code = 0;

		} catch (SQLException e) {

			System.out.println("회원 정보 수정에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;
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
				corn.setProfile(rs.getString("profile"));
				corn.setLike_num(rs.getInt("like_num"));
				corn.setPop_num(getPopCount(rs.getString("id")));
				corn.setMylike_num(getMyLikeCount(rs.getString("id")));

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

			pstmt = connection.prepareStatement("select * from corn where id = ?");

			pstmt.setString(1, corn_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				corn.setId(rs.getString("id"));
				corn.setPw(rs.getString("pw"));
				corn.setName(rs.getString("name"));
				corn.setNickname(rs.getString("nickname"));
				corn.setBirth(rs.getString("birth"));
				corn.setPhone(rs.getString("phone"));
				corn.setProfile(rs.getString("profile"));
				corn.setLike_num(rs.getInt("like_num"));
				corn.setPop_num(getPopCount(rs.getString("id")));
				corn.setMylike_num(getMyLikeCount(rs.getString("id")));

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

			String sql = "select * from pop where location like '%" + keyword + "%' order by like_num desc";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next())

			{
				Pop pop = selectOnePop(rs.getInt("id"));

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

	
	// 검색 기능 (키워드가 들어간 유저와 게시글 )
		public List<Pop> searchPop(String keyword,String tag) {

			PreparedStatement pstmt = null;

			ResultSet rs = null;

			List<Pop> list = new ArrayList<Pop>();

			try {

				String sql = "select * from pop where location like '%" + keyword + "%' and tag = '"+tag+"' order by like_num desc";

				pstmt = connection.prepareStatement(sql);
				System.out.println(pstmt);

				rs = pstmt.executeQuery();

				int i=0;
				while (rs.next())
				{	i++;
					Pop pop = selectOnePop(rs.getInt("id"));

					list.add(pop);
					if(i == 2)
						break;

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

	public boolean isLike(String like_corn_id, String corn_id) {
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		boolean return_code = false;

		try {

			pstmt = connection.prepareStatement("select * from corn_like where like_corn_id = ? and corn_id = ?");

			pstmt.setString(1, like_corn_id);
			pstmt.setString(2, corn_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return_code = true;

			} else {
				return_code = false;
			}

		} catch (SQLException e) {

			System.out.println("좋아요 확인에 실패했습니다.");
			return_code = false;
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

	public boolean isLike(int pop_id, String corn_id) {
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		boolean return_code = false;

		try {

			pstmt = connection.prepareStatement("select * from pop_like where pop_id = ? and corn_id = ?");

			pstmt.setInt(1, pop_id);
			pstmt.setString(2, corn_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return_code = true;

			} else {
				return_code = false;
			}

		} catch (SQLException e) {

			System.out.println("좋아요 확인에 실패했습니다.");
			return_code = false;
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

	// --회원 좋아요
	public int deleteLike(Corn like_corn, Corn corn) {
		PreparedStatement pstmt = null;

		int return_code = -1;
		try {
			pstmt = connection.prepareStatement("delete from corn_like where like_corn_id = ? and corn_id = ?");

			pstmt.setString(1, like_corn.getId());
			pstmt.setString(2, corn.getId());

			pstmt.executeUpdate();

			updateLike(like_corn, -1);
			return_code = 0;

		} catch (SQLException e) {

			System.out.println("좋아요 취소에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;
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

	public int insertLike(Corn like_corn, Corn corn) {
		PreparedStatement pstmt = null;

		int return_code = -1;
		try {
			pstmt = connection.prepareStatement("insert into corn_like values(?,?) ");

			pstmt.setString(1, like_corn.getId());
			pstmt.setString(2, corn.getId());

			pstmt.executeUpdate();

			updateLike(like_corn, 1);
			return_code = 0;

		} catch (SQLException e) {

			System.out.println("좋아요 등록에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;
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

	public void updateLike(Corn corn, int order) {

		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement("update corn set like_num = ? where id = ?");

			pstmt.setString(2, corn.getId());
			pstmt.setInt(1, corn.getLike_num() + order);

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
	// --회원 좋아요

	// --게시글 좋아요
	public int deleteLike(Pop pop, Corn corn) {
		PreparedStatement pstmt = null;

		int return_code = -1;
		try {
			pstmt = connection.prepareStatement("delete from pop_like where pop_id = ? and corn_id = ?");

			pstmt.setInt(1, pop.getId());
			pstmt.setString(2, corn.getId());

			pstmt.executeUpdate();

			updateLike(pop, -1);
			return_code = 0;

		} catch (SQLException e) {

			System.out.println("좋아요 취소에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;
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

	public int insertLike(Pop pop, Corn corn) {
		PreparedStatement pstmt = null;

		int return_code = -1;
		try {
			pstmt = connection.prepareStatement("insert into pop_like values(?,?) ");

			pstmt.setInt(1, pop.getId());
			pstmt.setString(2, corn.getId());

			pstmt.executeUpdate();

			updateLike(pop, 1);
			return_code = 0;

		} catch (SQLException e) {

			System.out.println("좋아요 등록에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;
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

	public void updateLike(Pop pop, int order) {

		PreparedStatement pstmt = null;

		try {
			pstmt = connection.prepareStatement("update pop set like_num = ? where id = ?");

			pstmt.setInt(2, pop.getId());
			pstmt.setInt(1, pop.getLike_num() + order);

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
	// --게시글 좋아요

	// 로그인
	// 0->성공 1->비밀번호 틀림 2->없음 3->실패
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
			return_code = 3;
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

	// 댓글 등록
	public int insertComment(Comment comment) {

		PreparedStatement pstmt = null;

		int return_code = -1;

		try {

			pstmt = connection
					.prepareStatement("insert into comment(pop_id,corn_id,corn_name,comment) values(?,?,?,?)");

			pstmt.setInt(1, comment.getPop_id());
			pstmt.setString(2, comment.getCorn_id());
			pstmt.setString(3, comment.getCorn_name());
			pstmt.setString(4, comment.getComment());

			pstmt.executeUpdate();

			return_code = 0;

		} catch (SQLException e) {

			System.out.println("댓글 추가에 실패했습니다.");
			e.printStackTrace();
			return_code = 1;

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

	// 댓글 가져오기
	public List<Comment> selectAllComment(int pop_id) {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Comment> list = new ArrayList<Comment>();

		try {

			pstmt = connection.prepareStatement("select * from comment where pop_id = ? order by reg_date asc");
			pstmt.setInt(1, pop_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Comment comment = new Comment();

				comment.setId(rs.getInt("id"));
				comment.setPop_id(rs.getInt("pop_id"));
				comment.setCorn_id(rs.getString("corn_id"));
				comment.setCorn_name(rs.getString("corn_name"));
				comment.setComment(rs.getString("comment"));
				comment.setReg_date(rs.getString("reg_date"));

				list.add(comment);

			}

		} catch (

		SQLException e) {

			System.out.println("댓글 조회에 실패했습니다.");
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

	// 댓글 삭제
	public void deleteComment(int id) {

		PreparedStatement pstmt = null;

		try {

			pstmt = connection.prepareStatement("delete from comment where id = ?");

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("댓글 삭제에 실패했습니다.");
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

	public int getPopCount(String corn_id) {
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		int num = 0;

		try {

			pstmt = connection.prepareStatement("select count(*) from pop where corn_id = ?");

			pstmt.setString(1, corn_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt("count(*)");

			}

		} catch (SQLException e) {

			System.out.println("좋아요 확인에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return num;
	}

	public int getPopComment(int pop_id) {
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		int num = 0;

		try {

			pstmt = connection.prepareStatement("select count(*) from comment where pop_id = ?");

			pstmt.setInt(1, pop_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt("count(*)");

			}

		} catch (SQLException e) {

			System.out.println("댓글 확인에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return num;
	}

	public int getMyLikeCount(String corn_id) {
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		int num = 0;

		try {

			pstmt = connection.prepareStatement("select count(*) from corn_like where corn_id = ?");

			pstmt.setString(1, corn_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt("count(*)");

			}

		} catch (SQLException e) {

			System.out.println("좋아요 확인에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return num;
	}

	// 좋아요 유저 조회
	public List<Corn> selectAllCorn(String type, String corn_id) {
		// type : like_corn_id(like me) corn_id(my like)

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Corn> list = new ArrayList<Corn>();

		try {

			pstmt = connection.prepareStatement("select * from corn_like where " + type + " = ?");
			pstmt.setString(1, corn_id);

			System.out.println(pstmt);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id_name = type.equals("corn_id") ? "like_corn_id" : "corn_id";
				Corn corn = selectOneCorn(rs.getString(id_name));

				list.add(corn);

			}

		} catch (SQLException e) {

			System.out.println("모든 좋아요 유저 조회에 실패했습니다.");
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

	// 지역 검색
	public List<Location> selectAllLoaction() {
		// type : like_corn_id(like me) corn_id(my like)

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Location> list = new ArrayList<Location>();

		try {

			pstmt = connection
					.prepareStatement("select count(*),location from pop group by location order by count(*) desc");

			rs = pstmt.executeQuery();
			
			int i=0;
			while (rs.next()) {
				i++;
				Location location = new Location();
				location.setCount(rs.getInt("count(*)"));
				location.setLocation_name(rs.getString("location"));

				list.add(location);
				if(i==3)
					break;

			}

		} catch (SQLException e) {

			System.out.println("지역 조회에 실패했습니다.");
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
	// 지역 검색
		public List<Location> selectAllLoaction(String year) {
			// type : like_corn_id(like me) corn_id(my like)

			PreparedStatement pstmt = null;

			ResultSet rs = null;

			List<Location> list = new ArrayList<Location>();

			try {

				String sql = "select count(*),location from pop where corn_id in "+
				"(select id from corn where birth >= '"+(Integer.parseInt(year)-4)+"' and birth <= '"+(Integer.parseInt(year)+4)+"') "+
				"group by location order by count(*) desc";
				
				pstmt = connection.prepareStatement(sql);

				rs = pstmt.executeQuery();
				
				int i=0;
				while (rs.next()) {
					i++;
					Location location = new Location();
					location.setCount(rs.getInt("count(*)"));
					location.setLocation_name(rs.getString("location"));

					list.add(location);
					if(i==3)
						break;

				}

			} catch (SQLException e) {

				System.out.println("지역 조회에 실패했습니다.");
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

	// 인기 게시물
	public List<Pop> selectPopularPop() {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Pop> list = new ArrayList<Pop>();

		try {

			pstmt = connection.prepareStatement("select * from pop order by like_num desc");

			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				i++;
				Pop pop = selectOnePop(rs.getInt("id"));
				list.add(pop);
				if (i == 2)
					break;

			}

		} catch (SQLException e) {

			System.out.println("인기있는 게시물 조회에 실패했습니다.");
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

}
