
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menuUpdate")
public class menuUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public menuUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html, charset=utf-8");
		Connection conn = null;
		PreparedStatement pstmt = null;

		String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // DB접속정보
		String userid = "ora_user";
		String passcode = "human123";
		String sql = "update menu set name = ?, price = ? where code = ?";
		String result_flag = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, passcode);
			System.out.println("DB연결성공");
			pstmt = conn.prepareStatement(sql);
			System.out.println("Statement객체 생성 성공");
//	        System.out.println(request.getParameter("name")+","+
//      			   request.getParameter("price"));
			pstmt.setString(1, request.getParameter("name"));
			pstmt.setInt(2, Integer.parseInt(request.getParameter("price")));			pstmt.setString(3, request.getParameter("code"));
			
			pstmt.executeUpdate();
			result_flag = "OK";
			System.out.println(result_flag);
		} catch (Exception e) {
			result_flag = "FAIL";
			System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
