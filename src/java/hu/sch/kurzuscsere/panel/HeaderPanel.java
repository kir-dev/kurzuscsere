package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.authz.DummyAuthorization;
import hu.sch.kurzuscsere.domain.User;
import hu.sch.kurzuscsere.page.HomePage;
import hu.sch.kurzuscsere.page.HomePageDev;
import hu.sch.kurzuscsere.page.MyRequestsPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

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
        add(new Label("loggedInUser",
                new Model<User>(DummyAuthorization.actualTestUser)));

        add(new DevUserSwitchPanel("devPanel"));

        add(new BookmarkablePageLink<HomePage>("homeMenuLink", HomePage.class));
        add(new BookmarkablePageLink<MyRequestsPage>("myreqMenuLink",
                MyRequestsPage.class));
        add(new BookmarkablePageLink<HomePageDev>("newHomeMenuLink",
                HomePageDev.class));
    }
}