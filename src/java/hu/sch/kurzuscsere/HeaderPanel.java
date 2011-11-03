/*
 * HeaderPanel.java
 *
 * Created on October 20, 2011, 9:28 PM
 */
 
package hu.sch.kurzuscsere;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author Kresshy
 * @version 
 */

public class HeaderPanel extends Panel {

    /**
     * Construct.
     * @param componentName name of the component
     * @param exampleTitle title of the example
     */

    public HeaderPanel(String componentName, String exampleTitle)
    {
        super(componentName);
        add(new LoginPanel("loginpanel"));
        
    }

}
