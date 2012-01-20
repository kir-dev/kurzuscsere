/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.session.AppSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Kresshy
 */
public final class LoginPanel extends Panel {

    TextField utf;
    PasswordTextField ptf;
    String uname = "";
    String psw = "";
    String unameLblText = "";

    public LoginPanel(String id) {
        super(id);

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        

        final AppSession aps = (AppSession) getSession();
        final Form loginForm = new Form("form");
        add(loginForm);

        PropertyModel unameLblModel = new PropertyModel(this, "unameLblText");

        final Form logOutForm = new Form("logoutform");
        add(logOutForm.setVisible(false));
        logOutForm.add(new Label("loggedinname", unameLblModel));
        logOutForm.add(new Button("btnlogout", Model.of("Kijelentkezes")) {

            @Override
            public void onSubmit() {
                super.onSubmit();
                aps.setUserName(null);
                loginForm.setVisible(true);
                logOutForm.setVisible(false);
               
            }
        });

        PropertyModel userNameModel = new PropertyModel(this, "uname");
        PropertyModel pswModel = new PropertyModel(this, "psw");
        loginForm.add(utf = new TextField("username", userNameModel));
        loginForm.add(ptf = new PasswordTextField("password", pswModel));
        loginForm.add(new Button("btnSubmit") {

            @Override
            public void onSubmit() {

                aps.setUserName(uname);
                if (aps.isLoggedIn()) {
                    unameLblText = aps.getUserName();
                    loginForm.setVisible(false);
                    logOutForm.setVisible(true);
                    
                    
                }
            }
        });

    } 
    
    
}


