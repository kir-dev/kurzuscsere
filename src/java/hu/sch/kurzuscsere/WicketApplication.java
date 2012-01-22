package hu.sch.kurzuscsere;

import hu.sch.kurzuscsere.config.ConfigKeys;
import hu.sch.kurzuscsere.config.LoggerSetup;
import hu.sch.kurzuscsere.page.HomePage;
import hu.sch.kurzuscsere.session.AppSession;
import hu.sch.kurzuscsere.util.ServerTimerFilter;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Kresshy
 * @version
 */
public class WicketApplication extends WebApplication {

    private static final Logger log;

    static {
        //setup logging
        LoggerSetup.configureLogging();
        log = LoggerFactory.getLogger(WicketApplication.class);
    }

    public WicketApplication() {
    }

    @Override
    protected void init() {
        super.init();

        //getApplicationSettings().setPageExpiredErrorPage(PageExpiredError.class);
        getPageSettings().setAutomaticMultiWindowSupport(false);

        //Ha dev módban vagyunk, akkor hozzáteszünk egy új filtert, ami mutatja
        //a render időket a log fájlban.
        if (getConfigurationType().equals(DEVELOPMENT)) {
            getRequestCycleSettings().addResponseFilter(new ServerTimerFilter());
            log.info("Successfully enabled ServerTimerFilter");
        }
    }

    @Override
    public Class getHomePage() {
        return HomePage.class;
    }

    @Override
    public String getConfigurationType() {
        String prop = System.getProperty(ConfigKeys.JVM_PROP_ENVIRONMENT, DEPLOYMENT);
        if (prop.equals("DEV")) {
            return DEVELOPMENT;
        }

        return DEPLOYMENT;
    }

    @Override
    public AppSession newSession(Request request, Response response) {
        return new AppSession(request);
    }
}
