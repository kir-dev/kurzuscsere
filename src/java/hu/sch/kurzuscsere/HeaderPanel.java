package hu.sch.kurzuscsere;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Kresshy
 * @version
 */
public class HeaderPanel extends Panel {

    public HeaderPanel(String componentName, String pageTitle) {
        super(componentName);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("pageTitle"));
        add(new LoginPanel("loginpanel"));
    }
}