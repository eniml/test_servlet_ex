import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

class Test extends HttpServlet {

    private ServletConfig config;

    String page = "index.jsp";

    public void init(ServletConfig config)

            throws ServletException {

        this.config = config;

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        System.out.print("123");

        String connectionURL = "jdbc:mysql://localhost:3306/test";

        Connection connection = null;

        ResultSet rs;

        response.setContentType("text/html");

        List<students> studentsList = new ArrayList<students>();

        try {

            connection = DriverManager.getConnection(connectionURL, "root", "");

            String sql = "SELECT * FROM students";

            Statement s = connection.createStatement();

            s.executeQuery(sql);

            rs = s.getResultSet();

            boolean more = true;
            while (more) {
                students student = new students();
                student.setId(rs.getInt("id"));
                student.setFirst_name(rs.getString("first_name"));
                student.setLast_name(rs.getString("last_name"));
                student.setGroup_id(rs.getInt("group_id"));
                studentsList.add(student);
                more = rs.next();
            }

            rs.close();

            s.close();
            String t;

        } catch (Exception e) {

            System.out.println("Exception is ;" + e);

        }

        request.setAttribute("data", studentsList);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        if (dispatcher != null) {

            dispatcher.forward(request, response);

        }

    }


}