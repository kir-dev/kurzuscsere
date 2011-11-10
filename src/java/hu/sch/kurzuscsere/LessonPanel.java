/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Kresshy
 */
public final class LessonPanel extends Panel {

    TextField lsn;
    TextField cof;
    TextField cot;
    TextField tif;
    TextField tit;
    String lessonName;
    String courseFrom;
    String courseTo;
    String timeFrom;
    String timeTo;
    Lesson lesson; //model object
    Form lessonForm;

    /*public Form getLessonForm() {
    return lessonForm;
    }*/
    private static final String dateFormat = "yyyy.MM.dd HH:mm";

    public LessonPanel(String id) {
        super(id);

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        lesson = new Lesson();
        final PropertyModel<Lesson> model = new PropertyModel(this, "lesson");

        final AppSession aps = (AppSession) getSession();
        final ArrayList<Lesson> lessonAdapter = ((WicketApplication) getApplication()).getLessons();
        lessonForm = new Form("lessonform");
        add(lessonForm);

        lessonForm.add(lsn = new TextField("lessonname", new PropertyModel(model, "name")));
        lessonForm.add(cof = new TextField("coursefrom", new PropertyModel(model, "courseFrom")));
        lessonForm.add(cot = new TextField("courseto", new PropertyModel(model, "courseTo")));
        lessonForm.add(tif = new TextField("timefrom", Model.of(dateFormat)));
        lessonForm.add(tit = new TextField("timeto", Model.of(dateFormat)));
        lessonForm.add(new Button("btnSub") {

            @Override
            public void onSubmit() {
                super.onSubmit();

                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                String timefrom = tif.getDefaultModelObjectAsString();
                String timeto = tit.getDefaultModelObjectAsString();

                try {
                    Lesson lesson = model.getObject();
                    lesson.setTimeFrom(sdf.parse(timefrom));
                    lesson.setTimeTo(sdf.parse(timeto));

                    lessonAdapter.add(lesson);
                    info(lesson.toString());

                    model.setObject(new Lesson());

                } catch (ParseException ex) {
                    error("DATEFORMAT KOCSOG"); //Logger.getLogger(LessonPanel.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });

    }

    public void setFormVisibility(boolean visibility) {
        lessonForm.setVisible(visibility);
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        AppSession aps = (AppSession) getSession();

        setFormVisibility(aps.isLoggedIn());

    }
}
