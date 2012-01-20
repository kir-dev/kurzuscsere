/*
 * FooterPanel.java
 *
 * Created on October 20, 2011, 9:28 PM
 */
 
package hu.sch.kurzuscsere.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author Kresshy
 * @version 
 */

public final class FooterPanel extends Panel {

    public FooterPanel(String id, String text) {
        super(id);
        add(new Label("footerpanel_text", text));
    }

}
