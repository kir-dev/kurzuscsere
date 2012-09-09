package hu.sch.kurzuscsere;

import hu.sch.kurzuscsere.authz.AgentBasedAuthorization;
import hu.sch.kurzuscsere.authz.DummyAuthorization;
import hu.sch.kurzuscsere.authz.UserAuthorization;
import hu.sch.kurzuscsere.config.ConfigKeys;
import hu.sch.kurzuscsere.config.LoggerSetup;
import hu.sch.kurzuscsere.page.HomePage;
import hu.sch.kurzuscsere.page.HomePageDev;
import hu.sch.kurzuscsere.page.MyRequestsPage;
import hu.sch.kurzuscsere.session.AppSession;
import hu.sch.kurzuscsere.util.ServerTimerFilter;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.javaee.injection.JavaEEComponentInjector;
import org.wicketstuff.javaee.naming.global.GlobalJndiNamingStrategy;
import org.wicketstuff.javaee.naming.global.ModuleJndiNamingStrategy;

/**
 *
 * @author Kresshy
 * @author balo
 */
public class WicketApplication extends WebApplication {

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static Logger log;
    private UserAuthorization authorizationComponent;

    @Override
    protected void init() {
        super.init();

        LoggerSetup.configureLogging(get().getInitParameter(ConfigKeys.PARAM_LOGBACK_FILE));
        log = LoggerFactory.getLogger(WicketApplication.class);

        //need for inject ejbs
        getComponentInstantiationListeners().add(new JavaEEComponentInjector(this, new ModuleJndiNamingStrategy()));

        getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);

        //getApplicationSettings().setPageExpiredErrorPage(PageExpiredError.class);

        //Ha dev módban vagyunk, akkor hozzáteszünk egy új filtert, ami mutatja
        //a render időket a log fájlban.
        if (getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT)) {
            authorizationComponent = new DummyAuthorization();
            getRequestCycleSettings().addResponseFilter(new ServerTimerFilter());
            log.info("Successfully enabled ServerTimerFilter");

            getMarkupSettings().setStripWicketTags(false);
            getMarkupSettings().setStripComments(false);
            getMarkupSettings().setCompressWhitespace(false);
        } else {
            authorizationComponent = new AgentBasedAuthorization();
            getMarkupSettings().setStripWicketTags(true);
            getMarkupSettings().setStripComments(true);
            getMarkupSettings().setCompressWhitespace(true);
        }

        //mount bookmarkable pages
        mountPages();
    }

    @Override
    public Class getHomePage() {
        return HomePage.class;
    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        final String prop = WicketApplication.get().getInitParameter(ConfigKeys.PARAM_ENVIRONMENT);

        RuntimeConfigurationType conf = RuntimeConfigurationType.DEPLOYMENT;
        if (prop != null && prop.equals("DEV")) {
            conf = RuntimeConfigurationType.DEVELOPMENT;
        }

        return conf;
    }

    public UserAuthorization getAuthorizationComponent() {
        return authorizationComponent;
    }

    @Override
    public AppSession newSession(final Request request, final Response response) {
        return new AppSession(request);
    }

    private void mountPages() {
        mountPage("/Home", HomePage.class);
        mountPage("/MyRequests", MyRequestsPage.class);
        mountPage("/Home2", HomePageDev.class);
    }
}
