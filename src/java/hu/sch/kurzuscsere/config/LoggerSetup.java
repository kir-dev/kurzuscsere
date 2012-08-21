package hu.sch.kurzuscsere.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

/**
 *
 * @author balo
 */
public abstract class LoggerSetup {

    private LoggerSetup() {
    }

    /**
     * Betölti a logback konfigurációját
     *
     * @throws IllegalArgumentException Ha nem sikerült a konfiguráció betöltése
     */
    public static void configureLogging(final String fileName) {
        String configDir = System.getProperty(ConfigKeys.JVM_PROP_CONF_DIR, "");

        if (fileName == null || configDir.isEmpty()) {
            String error = "Path to logging configuration file is undefined.";
            throw new IllegalArgumentException(error);
        }

        if (!configDir.endsWith("/")) {
            configDir += "/";
        }

        final String logbackFile = configDir + fileName;
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        try {
            JoranConfigurator cfg = new JoranConfigurator();
            cfg.setContext(lc);
            lc.reset();
            cfg.doConfigure(logbackFile);
        } catch (JoranException ex) {
            throw new IllegalArgumentException("Unable to configure logging", ex);
        } finally {
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        }
    }
}
