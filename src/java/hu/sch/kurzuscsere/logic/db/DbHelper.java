package hu.sch.kurzuscsere.logic.db;

import java.sql.Connection;

/**
 *
 * @author balo
 */
@Deprecated
public abstract class DbHelper {

    private DbHelper() {
    }

    /**
     * Throws UnsupportedOperationException.
     * Need for only building until we call it.
     *
     * @deprecated 
     * @throws UnsupportedOperationException
     */
    @Deprecated
    public static Connection getConnection() {
        throw new UnsupportedOperationException("Use JPA from EJB!");
    }
}
