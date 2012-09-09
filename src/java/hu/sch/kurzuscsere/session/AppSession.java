package hu.sch.kurzuscsere.session;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 *
 * @author Kresshy
 */
public class AppSession extends WebSession {

    private Long userId;

    public synchronized Long getUserId() {
        return userId;
    }

    public synchronized void setUserId(final Long userId) {
        this.userId = userId;
        dirty();
    }

    public synchronized boolean isLoggedIn() {
        if (userId != null) {
            return true;
        }
        return false;
    }

    public AppSession(final Request request) {
        super(request);
    }
}
