

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class salesInsert
 */
@WebServlet("/salesInsert")
public class salesInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public salesInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("utf-8");
         response.setContentType("text/html, charset=utf-8");
         Connection conn = null;
         PreparedStatement pstmt = null;
         Statement stmt = null;
         ResultSet rs = null;
         


         String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // DB접속정보
         String userid = "ora_user";
         String passcode = "human123";
//         String sql = "select a.code, a.mobile , b.name, a.qty, a.total, a.sold_time from cafe_sales a, menu b where a.menu_code = b.code order by a.code "+
//        		 	  "insert into cafe_sales (seq_menu.nextval,?,?,?,?,sysdate)";
         String sql = "INSERT INTO cafe_sales(code, mobile, menu_code, qty, total, sold_time) "
         			  + "VALUES(seq_code.nextval, ?, (SELECT code FROM menu WHERE name = ?), ?, ?, sysdate)";
         String result_flag = "";
         
         
         try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, userid, passcode);
            pstmt = conn.prepareStatement(sql);
            System.out.println(request.getParameter("mobile") + "," +
                           	request.getParameter("name") + "," +
            				request.getParameter("qty") + "," +
            				request.getParameter("total"));
            pstmt.setString(1, request.getParameter("mobile"));
            pstmt.setString(2, request.getParameter("name"));
            pstmt.setInt(3, Integer.parseInt(request.getParameter("qty")));
            pstmt.setInt(4, Integer.parseInt(request.getParameter("total")));

            pstmt.executeUpdate();
            result_flag = "OK";
            System.out.println(result_flag);
         } catch (Exception e) {
       	 result_flag = "FAIL";
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
