package hu.sch.kurzuscsere.page;

import hu.sch.kurzuscsere.WicketApplication;
import hu.sch.kurzuscsere.authz.UserAuthorization;
import hu.sch.kurzuscsere.domain.User;
import hu.sch.kurzuscsere.logic.UserManager;
import hu.sch.kurzuscsere.panel.DevUserSwitchPanel;
import hu.sch.kurzuscsere.panel.MenuPanel;
import hu.sch.kurzuscsere.panel.VirMenuPanel;
import hu.sch.kurzuscsere.session.AppSession;
import javax.ejb.EJB;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Kresshy
 * @version
 */
public abstract class BasePage extends WebPage {

    @EJB(name = "UserManager")
    protected UserManager userManager;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        loadUser();

        add(new FeedbackPanel("feedbackPanel"));

        add(new DevUserSwitchPanel("devPanel"));

        add(new Label("pageTitle", Model.of(getString("page.title"))));

        add(new Label("inPageTitle", getPageTitle()));
        add(new BookmarkablePageLink<HomePage>("appNameLink", HomePage.class));
        add(new MenuPanel("menuPanel"));
        add(new VirMenuPanel("virMenuPanel"));
    }

    private void loadUser() {
        final String remUser = getAuthorizationComponent().getRemoteUser(getRequest());

        if (remUser == null || remUser.equals("")) { // no sso login
            getSession().setUserId(0L);
            return;
        }

        User user = userManager.getUserFromLoginName(remUser);
        if (user == null) {
            user = new User();
        }

        final User userAttrs = getAuthorizationComponent().getUserAttributes(getRequest());
        if (userAttrs != null) {
            user.setEmail(userAttrs.getEmail());
            user.setName(userAttrs.getName());
            user = userManager.merge(user);
        }

        getSession().setUserId(user.getId());
    }

    protected UserAuthorization getAuthorizationComponent() {
        return ((WicketApplication) getApplication()).getAuthorizationComponent();
    }

    @Override
    public AppSession getSession() {
        return (AppSession) super.getSession();
    }

    protected abstract String getPageTitle();
}
