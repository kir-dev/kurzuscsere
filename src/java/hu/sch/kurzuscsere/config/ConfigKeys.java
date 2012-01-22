package hu.sch.kurzuscsere.config;

/**
 *
 * @author balo
 */
public abstract class ConfigKeys {

    public static final String JDBC_RES = "jdbc/KurzusCsereDb";
    public static final String LOGBACK_CONF = "logback.xml";
    public static final String JVM_PROP_ENVIRONMENT = "kurzuscsere.environment";

    private ConfigKeys() {
    }
}
