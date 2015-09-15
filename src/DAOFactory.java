
// Import the JNDI classes
import javax.naming.NamingException;
import javax.naming.InitialContext;
import java.sql.SQLException;

public class DAOFactory {

    private static Istudents dao = null;

    public static Istudents getDAO() throws SQLException
    {
        // If we already have loaded the DAO, return it
        if ( dao != null ) {
            return dao;
        }

        try {
            InitialContext ic = new InitialContext();
            String className = ( String )ic.lookup( "SAMPLEDAO.Impl" );
            dao = ( Istudents )Class.forName( className ).newInstance();
        }
        catch( NamingException ne ) {
            throw new SQLException( ne );
        }
        catch( Exception se ) {
            throw new SQLException( se );
        }
        return dao;
    }
}