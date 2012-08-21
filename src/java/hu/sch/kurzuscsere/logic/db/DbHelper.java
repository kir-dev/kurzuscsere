package hu.sch.kurzuscsere.logic.db;

import hu.sch.kurzuscsere.WicketApplication;
import hu.sch.kurzuscsere.config.ConfigKeys;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author balo
 */
public abstract class DbHelper {

    private static final Logger log = LoggerFactory.getLogger(DbHelper.class);
    private static final String EXCEPTION_MSG = "Can't get database connection.";

    private DbHelper() {
    }

    public static synchronized Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext context = new InitialContext();
            final String jdbcRes = WicketApplication.get().getInitParameter(ConfigKeys.PARAM_JDBC);
            if (jdbcRes == null) {
                throw new Exception(ConfigKeys.PARAM_JDBC +" parameter cannot be found in web.xml");
            }
            DataSource dataSource = (DataSource) context.lookup(jdbcRes);
            connection = dataSource.getConnection();
            if (connection == null) {
                throw new Exception("connection is null");
            }
        } catch (NamingException e) {
            log.error(EXCEPTION_MSG, e);
        } catch (SQLException e) {
            log.error(EXCEPTION_MSG, e);
        } catch (Exception e) {
            log.error(EXCEPTION_MSG, e);
        }
        return connection;
    }
}
