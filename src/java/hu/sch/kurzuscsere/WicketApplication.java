package hu.sch.kurzuscsere;

import hu.sch.kurzuscsere.config.LoggerSetup;
import hu.sch.kurzuscsere.page.HomePage;
import hu.sch.kurzuscsere.session.AppSession;
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
    public Class getHomePage() {
        return HomePage.class;
    }

    @Override
    public AppSession newSession(Request request, Response response) {
        return new AppSession(request);
    }
}
