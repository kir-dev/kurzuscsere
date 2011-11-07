/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.sch.kurzuscsere;

import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Kresshy
 */
public final class LessonListPanel extends Panel {

    final List<Lesson> lsns = ((WicketApplication) getApplication()).getLessons();
    final AppSession aps = (AppSession) getSession();
    final Form listform = new Form("listform");
    String lesson1 = "";
    String lesson2 = "";
    String lesson3 = "";
    String lesson4 = "";
    String lesson5 = "";
    String lesson6 = "";
    String lesson7 = "";
    String lesson8 = "";
    String lesson9 = "";
    String lesson0 = "";

    public LessonListPanel(String id) {
        super(id);

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(listform);

        PropertyModel lessonModel1 = new PropertyModel(this, "lesson1");
        PropertyModel lessonModel2 = new PropertyModel(this, "lesson2");
        PropertyModel lessonModel3 = new PropertyModel(this, "lesson3");
        PropertyModel lessonModel4 = new PropertyModel(this, "lesson4");
        PropertyModel lessonModel5 = new PropertyModel(this, "lesson5");
        PropertyModel lessonModel6 = new PropertyModel(this, "lesson6");
        PropertyModel lessonModel7 = new PropertyModel(this, "lesson7");
        PropertyModel lessonModel8 = new PropertyModel(this, "lesson8");
        PropertyModel lessonModel9 = new PropertyModel(this, "lesson9");
        PropertyModel lessonModel0 = new PropertyModel(this, "lesson0");

        Label lbl1 = new Label("listone", lessonModel1);
        Label lbl2 = new Label("listtwo", lessonModel2);
        Label lbl3 = new Label("listthree", lessonModel3);
        Label lbl4 = new Label("listfour", lessonModel4);
        Label lbl5 = new Label("listfive", lessonModel5);
        Label lbl6 = new Label("listsix", lessonModel6);
        Label lbl7 = new Label("listseven", lessonModel7);
        Label lbl8 = new Label("listeight", lessonModel8);
        Label lbl9 = new Label("listnine", lessonModel9);
        Label lbl0 = new Label("listten", lessonModel0);



        listform.add(new Label("cserek", "Legfrissebbek"));

        listform.add(lbl0);
        listform.add(lbl1);
        listform.add(lbl2);
        listform.add(lbl3);
        listform.add(lbl4);
        listform.add(lbl5);
        listform.add(lbl6);
        listform.add(lbl7);
        listform.add(lbl8);
        listform.add(lbl9);

    }

    public int lessonsize(int size) {
        if (size <= 10) {
            return size;
        } else {
            return 10;
        }
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        listform.setVisible(aps.isLoggedIn());

        switch (lessonsize(lsns.size())) {

            case 1:
                lesson1 = lsns.get(0).toString();
                lesson2 = "";
                lesson3 = "";
                lesson4 = "";
                lesson5 = "";
                lesson6 = "";
                lesson7 = "";
                lesson8 = "";
                lesson9 = "";
                lesson0 = "";

                break;

            case 2:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = "";
                lesson4 = "";
                lesson5 = "";
                lesson6 = "";
                lesson7 = "";
                lesson8 = "";
                lesson9 = "";
                lesson0 = "";

                break;

            case 3:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = lsns.get(2).toString();
                lesson4 = "";
                lesson5 = "";
                lesson6 = "";
                lesson7 = "";
                lesson8 = "";
                lesson9 = "";
                lesson0 = "";

                break;

            case 4:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = lsns.get(2).toString();
                lesson4 = lsns.get(3).toString();
                lesson5 = "";
                lesson6 = "";
                lesson7 = "";
                lesson8 = "";
                lesson9 = "";
                lesson0 = "";

                break;

            case 5:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = lsns.get(2).toString();
                lesson4 = lsns.get(3).toString();
                lesson5 = lsns.get(4).toString();
                lesson6 = "";
                lesson7 = "";
                lesson8 = "";
                lesson9 = "";
                lesson0 = "";

                break;

            case 6:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = lsns.get(2).toString();
                lesson4 = lsns.get(3).toString();
                lesson5 = lsns.get(4).toString();
                lesson6 = lsns.get(5).toString();
                lesson7 = "";
                lesson8 = "";
                lesson9 = "";
                lesson0 = "";


                break;

            case 7:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = lsns.get(2).toString();
                lesson4 = lsns.get(3).toString();
                lesson5 = lsns.get(4).toString();
                lesson6 = lsns.get(5).toString();
                lesson7 = lsns.get(6).toString();
                lesson8 = "";
                lesson9 = "";
                lesson0 = "";

                break;

            case 8:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = lsns.get(2).toString();
                lesson4 = lsns.get(3).toString();
                lesson5 = lsns.get(4).toString();
                lesson6 = lsns.get(5).toString();
                lesson7 = lsns.get(6).toString();
                lesson8 = lsns.get(7).toString();
                lesson9 = "";
                lesson0 = "";

                break;

            case 9:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = lsns.get(2).toString();
                lesson4 = lsns.get(3).toString();
                lesson5 = lsns.get(4).toString();
                lesson6 = lsns.get(5).toString();
                lesson7 = lsns.get(6).toString();
                lesson8 = lsns.get(7).toString();
                lesson9 = lsns.get(8).toString();
                lesson0 = "";

                break;

            case 10:
                lesson1 = lsns.get(0).toString();
                lesson2 = lsns.get(1).toString();
                lesson3 = lsns.get(2).toString();
                lesson4 = lsns.get(3).toString();
                lesson5 = lsns.get(4).toString();
                lesson6 = lsns.get(5).toString();
                lesson7 = lsns.get(6).toString();
                lesson8 = lsns.get(7).toString();
                lesson9 = lsns.get(8).toString();
                lesson0 = lsns.get(9).toString();

                break;

            default:
                lesson1 = "";
                lesson2 = "";
                lesson3 = "";
                lesson4 = "";
                lesson5 = "";
                lesson6 = "";
                lesson7 = "";
                lesson8 = "";
                lesson9 = "";
                lesson0 = "";

                break;
        }
    }
}
