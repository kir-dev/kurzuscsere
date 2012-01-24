package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.page.HomePage;
import hu.sch.kurzuscsere.page.MyRequestsPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
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

        add(new BookmarkablePageLink<HomePage>("homeMenuLink", HomePage.class));
        add(new BookmarkablePageLink<MyRequestsPage>("myreqMenuLink",
                MyRequestsPage.class));
    }
}