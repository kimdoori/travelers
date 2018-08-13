<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="model.Corn"%>


<%!
	String url = "jdbc:mysql://localhost:3306/travelers";
	String id = "root";   
	String pw = "doori0228!"; 
%>
<%-- <% // null로 초기화 한다.
 Connection conn = null; 
 try{ // 사용하려는 데이터베이스명을 포함한 URL 기술 
	 String url = "jdbc:mysql://localhost:3306/travelers"; 
 String id = "root"; // 사용자계정
 String pw = "doori0228!"; // 사용자계정의 패스워드
 // 데이터베이스와 연동하기 위해 DriverManager에 등록한다. 
 Class.forName("com.mysql.jdbc.Driver"); // DriverManager 객체로부터 Connection 객체를 얻어온다. 
 conn=DriverManager.getConnection(url,id,pw); // 커넥션이 제대로 연결되면 수행된다. 
 out.println("제대로 연결되었습니다."); // 예외가 발생하면 예외 상황을 처리한다. 
 }catch(Exception e){ e.printStackTrace(); } 
 %> --%>


<%!
public boolean executQuery(String query,Object ocject){
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    boolean result = false;
    
    try{
       
        Class.forName("com.mysql.jdbc.Driver");                  
        conn=DriverManager.getConnection(url,id,pw);       
        
        switch(query){
        case "insert_corn":
        	insert_corn();
        	break;
        
        }
        
        
        
        // sql 쿼리  
        String sql = "insert into corn values(?,?,?,?,?)";
        // prepareStatement에서 해당 sql을 미리 컴파일한다.
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,corn.getId());
        pstmt.setString(2,corn.getPw());
        pstmt.setString(3,corn.getName());
        pstmt.setString(4,corn.getNickname());
        pstmt.setString(5,corn.getBirth());
        pstmt.setString(6,corn.getPhone());
        pstmt.setInt(7,0);

        // 현재 날짜와 시간
        //pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        // 쿼리 실행
        pstmt.executeUpdate();
        // 성공시 메시지 출력
        System.out.println("새로운 회원 추가에 성공했습니다.");
        result=true;
        
    // 예외가 발생하면 예외 상황을 처리한다.
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("새로운 회원 추가에 실패했습니다.");
    // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
        result=false;
    }finally{
        // PreparedStatement 객체 해제
        if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
        // Connection 해제
        if(conn != null) try{conn.close();}catch(SQLException sqle){}
    }
    
    return result;
}
%>
