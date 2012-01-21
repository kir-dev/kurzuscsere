/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Kresshy
 */
public final class CourseChangePanel extends Panel {

    private static List<String> LESSONS = Arrays.asList(new String[]{"Analízis 1", "Digitális Technológia 1", "Programozás alapjai 1"});
    private String selected = "Kérem válasszon egy tárgyat";
    private String courseFrom = "";

    @Override
    public void onInitialize() {
        super.onInitialize();

        final Form changeForm = new Form("changePanel");
        add(changeForm);

        final MarkupContainer rowPanel = new WebMarkupContainer("rowsPanel");
        rowPanel.setOutputMarkupId(true);
        changeForm.add(rowPanel);

        List rows = new ArrayList();
        rows.add(new String());
        
        final ListView lv = new ListView("rows", rows) {

            @Override
            protected void populateItem(ListItem item) {
                int index = item.getIndex() + 1;
                TextField text = new TextField("text", item.getModel());
                item.add(text);
            }
        };
        rowPanel.add(lv);

        AjaxSubmitButton addButton = new AjaxSubmitButton("addRow", changeForm) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                lv.getModelObject().add(new String());
                if (target != null) {
                    target.addComponent(rowPanel);
                }
            }
        };
        
        addButton.setDefaultFormProcessing(false);
        rowPanel.add(addButton);

        changeForm.add(new DropDownChoice<String>("lessons", new PropertyModel<String>(this, "selected"), LESSONS));
        changeForm.add(new TextField("from", new PropertyModel(this, "courseFrom")));
        changeForm.add(new Button("btn1", Model.of("Elküldés")) {

            @Override
            public void onSubmit() {
                super.onSubmit();

            }
        });

    }

    public CourseChangePanel(String id) {
        super(id);
    }
}
