/*
 * WicketExamplePage.java
 *
 * Created on October 20, 2011, 9:28 PM
 */
 
package hu.sch.kurzuscsere;           

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/** 
 *
 * @author Kresshy
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        add(new HeaderPanel("headerpanel", "Welcome To Kurzuscsere")); 
        add(new LessonPanel("lessonpanel"));
        add(new FooterPanel("footerpanel", "Powered by Wicket and the NetBeans Wicket Plugin"));
        
        add(new FeedbackPanel("feedbackPanel"));
    } 

}
