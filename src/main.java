import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 *
 * @author Javin Paul
 */
public class main {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "";
    private static List<students> studentsList = new ArrayList<students>();

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    static String query = "SELECT * FROM students";

    public static void main(String args[]) throws SQLException, IOException {

        // opening database connection to MySQL server
        con = DriverManager.getConnection(url, user, password);
        ScriptRunner runner = new ScriptRunner(con, false, false);
        runner.runScript(new BufferedReader(new FileReader("test.sql")));
        Statement stmt = con.createStatement();

        // Отсылаем запрос, получаем объект

        ResultSet rs = stmt.executeQuery(query);

        ResultSetMetaData rsmd = rs.getMetaData();
        int numCols = rsmd.getColumnCount();
        int i;
        boolean more = rs.next();
        more = true;
        while (more) {
            students student = new students();
            student.setId(rs.getInt("id"));
            student.setFirst_name(rs.getString("first_name"));
            student.setLast_name(rs.getString("last_name"));
            student.setGroup_id(rs.getInt("group_id"));
            studentsList.add(student);
            more = rs.next();
        }


        // Закрыть набор данных

        rs.close();

        // Закрыть оператор

        stmt.close();

        // Закрыть соединение

        con.close();
        con.close();


    }
}

