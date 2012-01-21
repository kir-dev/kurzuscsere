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

    /**
     * Betölti a logback konfigurációját
     *
     * @throws IllegalArgumentException Ha nem sikerült a konfiguráció betöltése
     */
    public static final void configureLogging() {
        String logbackFile = ConfigKeys.LOGBACK_CONF;
        if (logbackFile == null || logbackFile.isEmpty()) {
            String error = "Path to logging configuration file is undefined. "
                    + "Missing key: ConfigKeys.LOGBACK_CONF";
            System.err.println(error);
            throw new IllegalArgumentException(error);
        }
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        try {
            JoranConfigurator cfg = new JoranConfigurator();
            cfg.setContext(lc);
            lc.reset();
            cfg.doConfigure(LoggerSetup.class.getResourceAsStream(logbackFile));
        } catch (JoranException ex) {
            throw new IllegalArgumentException("Unable to configure logging", ex);
        } finally {
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        }
    }
}
