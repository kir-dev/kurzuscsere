/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

/**
 *
 * @author Kresshy
 */
public class AppSession extends WebSession {

    private String userName;

    public synchronized String getUserName() {
        return userName;
    }

    public synchronized void setUserName(String userName) {
        this.userName = userName;
        dirty();
    }

    public synchronized boolean isLoggedIn() {
        if (userName != null) {
            return true;
        }
        return false;
    }

    public AppSession(Request request) {
        super(request);
    }
    
}
