/*
 * HomePage.java
 *
 * Created on October 20, 2011, 9:28 PM
 */
package hu.sch.kurzuscsere;

import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends BasePage {

    private LessonPanel lsnPanel = new LessonPanel("lessonpanel");

    public LessonPanel getLsnPanel() {
        return lsnPanel;
    }
    final AppSession aps = (AppSession) getSession();

    public HomePage() {
        add(new Label("message", "Kurzuscsere"));
        // ide raktam be ezt a cuccost 
        add(lsnPanel.setVisible(aps.isLoggedIn()));

    }
    
    
}
