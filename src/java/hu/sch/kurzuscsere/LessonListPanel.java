/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere;

import java.util.ArrayList;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Kresshy
 */
public final class LessonListPanel extends Panel {

    final ArrayList<Lesson> lsns = ((WicketApplication) getApplication()).getLessons();
    final AppSession aps = (AppSession) getSession();
    Form lista = new Form("list");

    public LessonListPanel(String id) {
        super(id);

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(lista);
        Label newest = new Label("legfrisebb","Legfrisebbek");
        lista.add(newest);
        ListView listview = new ListView("listview", lsns) {

            protected void populateItem(ListItem item) {
                item.add(new Label("label", item.getModel()));
            }
        };
        lista.add(listview);

    }

   

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        
        lista.setVisible(aps.isLoggedIn());
        
    }
}

