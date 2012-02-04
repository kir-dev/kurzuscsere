package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.domain.CCRequest;
import hu.sch.kurzuscsere.domain.Lesson;
import hu.sch.kurzuscsere.domain.User;
import hu.sch.kurzuscsere.logic.CourseManager;
import hu.sch.kurzuscsere.logic.LessonManager;
import hu.sch.kurzuscsere.logic.UserManager;
import hu.sch.kurzuscsere.logic.db.DbHelper;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
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
    private Lesson selectedLesson;
    private static final Logger log = LoggerFactory.getLogger(CourseChangePanel.class);

    public CourseChangePanel(String id) {
        super(id);
    }

    @Override
    public void onInitialize() {
        super.onInitialize();

        changeRequest = new CCRequest();
        changeRequest.getTo().add("");

        final Form changeForm = new Form("changePanel");
        add(changeForm);

        final MarkupContainer rowPanel = new WebMarkupContainer("rowsPanel");
        rowPanel.setOutputMarkupId(true);
        changeForm.add(rowPanel);

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

        final ChoiceRenderer cr = new ChoiceRenderer("name", "id");
        final DropDownChoice<Lesson> ddc = new DropDownChoice<Lesson>("lessons", new PropertyModel<Lesson>(this, "selectedLesson"),
                LessonManager.getInstance().getLessons(), cr);
        changeForm.add(ddc);
        changeForm.add(new RequiredTextField("from", new PropertyModel(changeRequest, "fromCourse")));
        Button send = new Button("btn1", Model.of("Elküldés")) {

            @Override
            public void onSubmit() {
                super.onSubmit();
                
//                User testUser = new User();
//                
//                testUser.setNick("TESTUSER");
//                testUser.setName("Test User");
//                testUser.setEmail("user@test.com");
//                
//                UserManager.getInstance().insertUser(DbHelper.getConnection(), testUser);
                
                Long idx = UserManager.getInstance().getUserId("TESTUSER");
                
                changeRequest.setUser(UserManager.getInstance().getUserById(idx));
                changeRequest.setLesson(selectedLesson);
                changeRequest.setStatus(CCRequest.Status.New);

                CourseManager.getInstance().instertRequest(changeRequest);
                log.warn(changeRequest.toString());

            }
        };

        changeForm.add(send);

    }
}
