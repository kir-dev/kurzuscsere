/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
    Lesson newLesson;
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

        final AppSession aps = (AppSession) getSession();
        final List<Lesson> lessonAdapter = ((WicketApplication) getApplication()).getLessons();
        newLesson = new Lesson();
        lessonForm = new Form("lessonform");
        add(lessonForm);


        /*<input type="text" wicket:id="lessonname"></input>
        <input type="text" wicket:id="coursefrom"></input>
        <input type="text" wicket:id="courseto"></input>
        <input type="text" wicket:id="timefrom"></input>
        <input type="text" wicket:id="timeto"></input>*/

        PropertyModel<Lesson> lessonNameModel = new PropertyModel<Lesson>(newLesson, "name");
        PropertyModel<Lesson> courseFromModel = new PropertyModel<Lesson>(newLesson, "courseFrom");
        PropertyModel<Lesson> courseToModel = new PropertyModel<Lesson>(newLesson, "courseTo");

        /*PropertyModel timeFromModel = new PropertyModel (this, "timeFrom");
        PropertyModel timeToModel = new PropertyModel(this, "timeTo");
         */

        lessonForm.add(lsn = new TextField("lessonname", lessonNameModel));
        lessonForm.add(cof = new TextField("coursefrom", courseFromModel));
        lessonForm.add(cot = new TextField("courseto", courseToModel));
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

                    newLesson.setTimeFrom(sdf.parse(timefrom));
                    newLesson.setTimeTo(sdf.parse(timeto));

                    lessonAdapter.add(newLesson);
                    info(newLesson.toString());

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
