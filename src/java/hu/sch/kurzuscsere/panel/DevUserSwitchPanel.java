package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.authz.DummyAuthorization;
import hu.sch.kurzuscsere.domain.User;
import hu.sch.kurzuscsere.logic.UserManager;
import hu.sch.kurzuscsere.session.AppSession;
import javax.ejb.EJB;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author balo
 */
public class DevUserSwitchPanel extends Panel {

    @EJB(name = "UserManager")
    private UserManager userManager;

    public DevUserSwitchPanel(final String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer formContainer = new WebMarkupContainer("formContainer");
        add(formContainer.setOutputMarkupId(true));

        final Form changeDevUserFrm = new Form("changeDevUser") {
            @Override
            protected void onSubmit() {
                super.onSubmit();

                final User user = userManager.merge(getDevUser());
                ((AppSession) getSession()).setUserId(user.getId());
            }
        };

        changeDevUserFrm.setVisible(false);
        formContainer.add(changeDevUserFrm);

        changeDevUserFrm.add(new TextField("devUserNick", new PropertyModel(getDevUser(),
                "nick")));
        changeDevUserFrm.add(new TextField("devUserName", new PropertyModel(getDevUser(),
                "name")));
        changeDevUserFrm.add(new TextField("devUserMail", new PropertyModel(getDevUser(),
                "email")));

        add(new AjaxLink("devPanelSwitcher") {
            @Override
            public void onClick(final AjaxRequestTarget target) {

                changeDevUserFrm.setVisible(!changeDevUserFrm.isVisible());
                AjaxRequestTarget.get().add(formContainer);
            }
        });

    }

    @Override
    public boolean isVisible() {
        return getApplication().getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT);
    }

    private User getDevUser() {
        return DummyAuthorization.TEST_USER;
    }
}
