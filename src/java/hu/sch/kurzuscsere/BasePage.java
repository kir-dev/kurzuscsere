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
        add(new LoginPanel("loginpanel"));
       
        add(new FooterPanel("footerpanel", "Powered by Kir-Dev and created by Kresshy"));
        
        //final AppSession aps = (AppSession) getSession();
        
        /*if (aps.isLoggedIn()) lsnPanel.setFormVisibility(true);
        else lsnPanel.setFormVisibility(false);*/
        
        add(new FeedbackPanel("feedbackPanel"));
    } 

}
