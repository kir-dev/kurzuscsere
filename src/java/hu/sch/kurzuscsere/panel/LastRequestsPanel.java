package hu.sch.kurzuscsere.panel;

import hu.sch.kurzuscsere.domain.CCRequest;
import hu.sch.kurzuscsere.logic.CourseManager;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author balo
 */
public class LastRequestsPanel extends Panel {

    public LastRequestsPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final List<CCRequest> lastRequests =
                CourseManager.getInstance().getLastRequests(10, null);

        final ListView<CCRequest> lv = new ListView<CCRequest>("requestsList",
                lastRequests) {

            @Override
            protected void populateItem(final ListItem<CCRequest> item) {
                CCRequest req = item.getModelObject();

                StringBuilder sb = new StringBuilder(req.getLesson().getName());
                sb.append(" (").append(req.getLesson().getClassCode()).append(")");

                item.add(new Label("requestLessonName", sb.toString()));
                item.add(new Label("requestCourseFrom", req.getFromCourse()));
                item.add(getCourseToList(req.getToCourses()));
            }
        };

        add(lv);
    }

    private ListView<String> getCourseToList(final List<String> toList) {
        final ListView<String> lv = new ListView<String>("requestCourseToList",
                toList) {

            @Override
            protected void populateItem(ListItem<String> item) {
                StringBuilder sb = new StringBuilder();
                if (item.getIndex() != 0) {
                    sb.append(" | ");
                }
                sb.append(item.getModelObject());
                item.add(new Label("requestCourseToElem", sb.toString()));
            }
        };

        return lv;
    }
}
