package hu.sch.kurzuscsere.config;

/**
 *
 * @author balo
 */
public abstract class ConfigKeys {

    public static final String JVM_PROP_CONF_DIR = "kurzuscsere.configdir";
    public static final String PARAM_ENVIRONMENT = "environment";
    public static final String PARAM_JDBC = "jdbc";
    public static final String PARAM_LOGBACK_FILE = "logback.file";

    private ConfigKeys() {
    }
}
