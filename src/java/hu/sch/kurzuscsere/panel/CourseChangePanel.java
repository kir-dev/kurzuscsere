/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere.panel;


import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Kresshy
 */
public final class CourseChangePanel extends Panel {
    
    private static List<String> LESSONS = Arrays.asList(new String[] {"Analízis 1", "Digitális Technológia 1", "Programozás alapjai 1"});
    private String selected = "Kérem válasszon egy tárgyat";
    private String courseFrom = "";
    private String courseTo = "";

    @Override
    public void onInitialize() {
        super.onInitialize();
        
        final Form changePanel = new Form("changePanel");
        add(changePanel);
        
        changePanel.add(new DropDownChoice<String>("lessons", new PropertyModel<String>(this, "selected"), LESSONS));
        changePanel.add(new TextField("from", new PropertyModel(this, "courseFrom")));
        changePanel.add(new TextField("to", new PropertyModel(this, "courseTo")));
        changePanel.add(new Button("btn1", Model.of("Elküldés")) {

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
