package hu.sch.kurzuscsere.page;

import hu.sch.kurzuscsere.panel.CourseChangePanel;
import hu.sch.kurzuscsere.panel.UploadPanel;

public class HomePage extends BasePage {

    public HomePage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new CourseChangePanel("courseChangePanel"));
        add(new UploadPanel("uploadPanel"));
    }
}
