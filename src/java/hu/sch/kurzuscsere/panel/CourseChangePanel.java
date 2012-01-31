package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.domain.CCRequest;
import hu.sch.kurzuscsere.domain.Lesson;
import hu.sch.kurzuscsere.logic.LessonManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Kresshy
 */
public final class CourseChangePanel extends Panel {

    private CCRequest changeRequest;
//    private List<Lesson> LESSONS;// = Arrays.asList(new String[]{"Analízis 1", "Digitális Technológia 1", "Programozás alapjai 1"});
    private String selected = "Kérem válasszon egy tárgyat";
//    private String courseFrom = "";
//    private String label = "Lessons here";
//    private List courseTo = new ArrayList();
    private LessonManager lsm;
    private static final Logger log = LoggerFactory.getLogger(CourseChangePanel.class);

    public CourseChangePanel(String id) {
        super(id);
    }

    @Override
    public void onInitialize() {
        super.onInitialize();

        changeRequest = new CCRequest();
        final Form changeForm = new Form("changePanel");
        add(changeForm);

        final MarkupContainer rowPanel = new WebMarkupContainer("rowsPanel");
        rowPanel.setOutputMarkupId(true);
        changeForm.add(rowPanel);

        List rows = new ArrayList();
        rows.add(new String());

        final ListView<String> lv = new ListView<String>("rows",
                new PropertyModel<List<String>>(changeRequest, "to")) {

            @Override
            protected void populateItem(ListItem<String> item) {
                TextField text = new TextField("text", item.getModel());
                item.add(text);
            }
        };

        rowPanel.add(lv.setReuseItems(true));

        AjaxButton addButton = new AjaxButton("addRow", changeForm) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                lv.getModelObject().add("");
                if (target != null) {
                    target.addComponent(rowPanel);
                }
            }
        };

        addButton.setDefaultFormProcessing(false);
        rowPanel.add(addButton);

        ChoiceRenderer cr = new ChoiceRenderer("name", "id");
        changeForm.add(new DropDownChoice<Lesson>("lessons", 
                LessonManager.getInstance().getLessons(), cr));
        
        changeForm.add(new RequiredTextField("from", new PropertyModel(changeRequest, "fromCourse")));
//        final PropertyModel pModel = new PropertyModel(this, "label");

//        final PropertyModel testModel = new PropertyModel(this, "courseTo");
        Button send = new Button("btn1", Model.of("Elküldés")) {

            @Override
            public void onSubmit() {
                super.onSubmit();
//                int index = (int) lv.getModelObject().size();
//                --index;
//                pModel.setObject(lv.getModelObject().get(index));
//                testModel.setObject(lv.getModelObject());
//                courseTo = (ArrayList) testModel.getObject();
                log.warn(changeRequest.toString());

            }
        };

//        changeForm.add(new Label("test", testModel));
//        changeForm.add(new Label("testone", new PropertyModel(this, "courseTo")));
        changeForm.add(send);

    }
}
