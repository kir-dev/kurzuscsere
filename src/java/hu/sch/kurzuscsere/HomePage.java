/*
 * HomePage.java
 *
 * Created on October 20, 2011, 9:28 PM
 */
package hu.sch.kurzuscsere;

import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends BasePage {

    public HomePage() {
        add(new Label("message", "Kurzuscsere"));
       
        // ide raktam be ezt a cuccost git stg
        add(new LessonPanel("lessonpanel"));
        add(new LessonListPanel("list"));

    }
    
    
}
