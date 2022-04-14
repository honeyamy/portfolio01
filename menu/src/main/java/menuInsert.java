
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insert
 */
@WebServlet("/menuInsert")
public class menuInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;


   public menuInsert() {
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
      String sql = "insert into menu values (seq_menu.nextval,?,?)";
      String result_flag = "";
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn = DriverManager.getConnection(url, userid, passcode);
         pstmt = conn.prepareStatement(sql);
//         System.out.println(request.getParameter("name")+","+
//                        	request.getParameter("price"));
         pstmt.setString(1, request.getParameter("name"));
         pstmt.setInt(2, Integer.parseInt(request.getParameter("price")));

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

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
