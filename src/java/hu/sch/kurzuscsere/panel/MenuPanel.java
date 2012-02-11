package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.page.HomePage;
import hu.sch.kurzuscsere.page.HomePageDev;
import hu.sch.kurzuscsere.page.MyRequestsPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Kresshy
 * @version
 */
public class MenuPanel extends Panel {

    public MenuPanel(String pageTitle) {
        super(pageTitle);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new BookmarkablePageLink<HomePage>("homeMenuLink", HomePage.class));
        add(new BookmarkablePageLink<MyRequestsPage>("myreqMenuLink",
                MyRequestsPage.class));
        add(new BookmarkablePageLink<HomePageDev>("newHomeMenuLink",
                HomePageDev.class));
    }
}