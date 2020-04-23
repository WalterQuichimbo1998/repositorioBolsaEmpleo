
package control;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Walter Quichimbo
 */
public class Conexion {

    private Connection con = null;

    public Connection conexion() {
        try {
            String jdniName = "java:app/bolsa_empleo_istl";
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(jdniName);
            con = ds.getConnection();
        } catch (SQLException | NamingException e) {
        }
        return con;
    }

}
