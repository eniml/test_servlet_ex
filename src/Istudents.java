import java.sql.SQLException;
import java.util.List;

/**
 * Created by eniml on 15.09.2015.
 */
public interface Istudents {
public students create(int id, String first_name, String last_name, int group_id) throws SQLException;
    public void delete(int id) throws SQLException;
    public void update(int id, students student) throws SQLException;
    public students findByName(String fName, String lName) throws SQLException;
    public students findById(int id) throws SQLException;
    public List<students> getAllStudents() throws SQLException;

}
