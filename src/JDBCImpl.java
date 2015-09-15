import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class JDBCImpl implements Istudents {

    @Override
    public students create(int id, String first_name, String last_name, int group_id) throws SQLException {
        try {
            Connection conn = getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            PreparedStatement ps =
                    conn.prepareStatement("INSERT INTO students VALUES( ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, first_name);
            ps.setString(3, last_name);
            ps.setInt(4, group_id);
            ps.executeUpdate();
            return new students(id, first_name, last_name, group_id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            Connection conn = getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            PreparedStatement ps =
                    conn.prepareStatement("DELETE FROM students WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    @Override
    public void update(int id, students student) throws SQLException {
        try {
            Connection conn = getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            PreparedStatement ps =
                    conn.prepareStatement("UPDATE students SET first_name= ?, last_name= ?, group_id= ? WHERE id = ?");
            ps.setString(1, student.getFirst_name());
            ps.setString(2, student.getLast_name());
            ps.setInt(3, student.getGroup_id());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<students> getAllStudents() throws SQLException {

        List<students> studentsList = new ArrayList<students>();
        Connection conn = getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        String sql = "SELECT * FROM students";
        Statement s = conn.createStatement();
        s.executeQuery(sql);
        ResultSet rs = s.getResultSet();
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
        return  studentsList;
    }


    @Override
    public students findByName(String fName, String lName) throws SQLException {

        Connection conn = getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        PreparedStatement ps =
                conn.prepareStatement("SELECT FROM students WHERE first_name= ? AND last_name=?");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            students fStudent = new students(rs.getInt("id"), fName, lName, rs.getInt("group_id"));
            return fStudent;
        }

//TODO: испаравить возвращаемое значение, через условие
        return null;
    }


    @Override
    public students findById(int id) throws SQLException {
        return null;
//TODO: написать метод
    }
}