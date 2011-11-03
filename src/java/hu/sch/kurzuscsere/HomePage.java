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
    }

}
