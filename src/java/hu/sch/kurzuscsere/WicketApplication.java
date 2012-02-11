package hu.sch.kurzuscsere;

import hu.sch.kurzuscsere.authz.AgentBasedAuthorization;
import hu.sch.kurzuscsere.authz.DummyAuthorization;
import hu.sch.kurzuscsere.authz.UserAuthorization;
import hu.sch.kurzuscsere.config.ConfigKeys;
import hu.sch.kurzuscsere.config.LoggerSetup;
import hu.sch.kurzuscsere.page.HomePage;
import hu.sch.kurzuscsere.page.MyRequestsPage;
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
 * @author balo
 * @version
 */
public class WicketApplication extends WebApplication {

    private static final Logger log;
    private UserAuthorization authorizationComponent;

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
        //getMarkupSettings().setStripWicketTags(true); //test in dev mode

        //Ha dev módban vagyunk, akkor hozzáteszünk egy új filtert, ami mutatja
        //a render időket a log fájlban.
        if (getConfigurationType().equals(DEVELOPMENT)) {
            authorizationComponent = new DummyAuthorization();
            getRequestCycleSettings().addResponseFilter(new ServerTimerFilter());
            log.info("Successfully enabled ServerTimerFilter");
        } else {
            authorizationComponent = new AgentBasedAuthorization();
        }

        //mount bookmarkable pages
        mountPages();
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

    public UserAuthorization getAuthorizationComponent() {
        return authorizationComponent;
    }

    @Override
    public AppSession newSession(Request request, Response response) {
        return new AppSession(request);
    }

    private void mountPages() {
        mountBookmarkablePage("/Home", HomePage.class);
        mountBookmarkablePage("/MyRequests", MyRequestsPage.class);
    }
}
