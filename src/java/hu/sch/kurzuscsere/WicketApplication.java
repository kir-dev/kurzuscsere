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

    private List<Lesson> lessons;
    
    public WicketApplication() {
        lessons = new ArrayList<Lesson>();        
    }
    

    public Class getHomePage() {
        return HomePage.class;
    }

    @Override
    public AppSession newSession(Request request, Response response) {
        return new AppSession(request);
    }

    /**
     * @return the ls
     */
    public List<Lesson> getLessons() {
        return lessons;
    }
    
    
    
}
