package hu.sch.kurzuscsere.page;

import hu.sch.kurzuscsere.panel.CourseChangePanel;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 *
 * @author balo
 */
public class MyRequestsPage extends BasePage {

    public MyRequestsPage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final MarkupContainer repeatPanel = new WebMarkupContainer("repeatPanel");
        Form form = new Form("form");
        repeatPanel.setOutputMarkupId(true);
        form.add(repeatPanel);
        
        List pan = new ArrayList();
        
        final ListView lv = new ListView("panel", pan) {

            @Override
            protected void populateItem(ListItem li) {
                CourseChangePanel cPanel = new CourseChangePanel("cpanel");
                li.add(cPanel);                
            }
        };
        
        lv.setReuseItems(true);
        
        
        
        AjaxButton addPanel = new AjaxButton("addbtn", form) {

            @Override
            protected void onSubmit(AjaxRequestTarget art, Form form) {
                lv.getModelObject().add(new CourseChangePanel("cpanel"));
                if (art != null)
                    art.addComponent(repeatPanel);
            }
        };
        
        addPanel.setDefaultFormProcessing(false);
        repeatPanel.add(addPanel);
    
    }
    
    

}