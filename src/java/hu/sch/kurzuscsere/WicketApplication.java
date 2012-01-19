/*
 * WicketApplication.java
 *
 * Created on October 20, 2011, 9:28 PM
 */
 
package hu.sch.kurzuscsere;           

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
/** 
 *
 * @author Kresshy
 * @version 
 */

public class WicketApplication extends WebApplication {

    
    public WicketApplication() {
    }
    

    public Class getHomePage() {
        return HomePage.class;
    }

    @Override
    public AppSession newSession(Request request, Response response) {
        return new AppSession(request);
    }
    
}
