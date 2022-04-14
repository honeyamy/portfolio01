

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/salesSelect")
public class salesSelect extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public salesSelect() {
        super();
        // TODO Auto-generated constructor stub
    }


   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      String strReturn="";
      String url="jdbc:oracle:thin:@localhost:1521:orcl"; // DB접속정보
      String userid="ora_user";
      String passcode="human123";
      String sql="select a.code, a.mobile , b.name, a.qty, a.total, a.sold_time from cafe_sales a, menu b where a.menu_code = b.code order by sold_time";
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         conn=DriverManager.getConnection(url,userid,passcode);
         stmt=conn.createStatement();
         rs=stmt.executeQuery(sql);
         while(rs.next()) {
            if(!strReturn.equals("")) strReturn+=";";
            strReturn += rs.getInt("code") + "," + rs.getString("mobile") + "," + 
            			 rs.getString("name") + "," + rs.getInt("qty") + "," +
            			 rs.getInt("total") + "," + rs.getString("sold_time");
         }
         
         
      } catch (Exception e) {
         e.printStackTrace();
		 System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
		 System.out.print("사유 : " + e.getMessage());
      } finally {
         try {
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      response.getWriter().print(strReturn);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}