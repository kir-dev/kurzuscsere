/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere.session;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

/**
 *
 * @author Kresshy
 */
public class AppSession extends WebSession {

    private Long userId;

    public synchronized Long getUserId() {
        return userId;
    }

    public synchronized void setUserId(Long userId) {
        this.userId = userId;
        dirty();
    }

    public synchronized boolean isLoggedIn() {
        if (userId != null) {
            return true;
        }
        return false;
    }

    public AppSession(Request request) {
        super(request);
    }
}
