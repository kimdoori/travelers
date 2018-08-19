package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import model.Pop;

public class PopCornDAO {

	private static PopCornDAO instance;

	private Connection connection;

	private PopCornDAO() {

		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelers", "root", "doori0228!");

		} catch (SQLException e) {

			// FIXME Auto-generated catch block

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

			System.out.println("게시글을 추가하는데 실패했습니다.");
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

					+ "content = ?, like_num = ? where id = ?");

			pstmt.setInt(4, pop.getId());
			pstmt.setString(1, pop.getTitle());
			pstmt.setString(2, pop.getLocation());
			pstmt.setString(3, pop.getContent());
			pstmt.setInt(4, pop.getLike_num());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("게시글을 업데이트하는데 실패했습니다.");
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

			pstmt = connection.prepareStatement("delete from product where p_id = ?");

			pstmt.setInt(1, p_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			// FIXME Auto-generated catch block

			e.printStackTrace();

		}

		finally {

			if (pstmt != null)

				try {

					pstmt.close();

				} catch (SQLException e) {

					// FIXME Auto-generated catch block

					e.printStackTrace();

				}

		}

	}
	
	// 지정 게시글 삭제 

	public void deleteProduct(int p_id) {

		PreparedStatement pstmt = null;

		try {

			pstmt = connection.prepareStatement("delete from product where p_id = ?");

			pstmt.setInt(1, p_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			// FIXME Auto-generated catch block

			e.printStackTrace();

		}

		finally {

			if (pstmt != null)

				try {

					pstmt.close();

				} catch (SQLException e) {

					// FIXME Auto-generated catch block

					e.printStackTrace();

				}

		}

	}

	// 지정한 id값을 가진 레코드 호출

	public Product selectOne(int p_id) {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		Product product = new Product();

		try {

			pstmt = connection.prepareStatement("select * from product where p_id = ?");

			pstmt.setInt(1, p_id);

			rs = pstmt.executeQuery();

			if (rs.next())

			{

				product.setP_id(rs.getInt("p_id"));

				product.setP_name(rs.getString("p_name"));

				product.setP_price(rs.getDouble("p_price"));

				product.setP_desc(rs.getString("p_desc"));

			}

		} catch (SQLException e) {

			// FIXME Auto-generated catch block

			e.printStackTrace();

		}

		finally {

			try {

				if (pstmt != null)

					pstmt.close();

				if (rs != null)

					rs.close();

			} catch (SQLException e) {

				// FIXME Auto-generated catch block

				e.printStackTrace();

			}

		}

		return product;

	}

	// product table에 있는 모든 레코드 호출!

	public List<Product> selectAll() {

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		List<Product> list = new ArrayList<Product>();

		try {

			pstmt = connection.prepareStatement("select * from product");

			rs = pstmt.executeQuery();

			while (rs.next())

			{

				Product product = new Product();

				product.setP_id(rs.getInt("p_id"));

				product.setP_name(rs.getString("p_name"));

				product.setP_price(rs.getDouble("p_price"));

				product.setP_desc(rs.getString("p_desc"));

				list.add(product);

			}

		} catch (SQLException e) {

			// FIXME Auto-generated catch block

			e.printStackTrace();

		}

		finally {

			try {

				if (pstmt != null)

					pstmt.close();

				if (rs != null)

					rs.close();

			} catch (SQLException e) {

				// FIXME Auto-generated catch block

				e.printStackTrace();

			}

		}

		return list;

	}

}
