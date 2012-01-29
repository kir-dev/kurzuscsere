package hu.sch.kurzuscsere.logic;

import hu.sch.kurzuscsere.domain.Lesson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Kresshy
 */
public class LessonManager {

    private static final Logger log = LoggerFactory.getLogger(LessonManager.class);

    private LessonManager() {
    }

    public synchronized static LessonManager getInstance() {
        return LessonManager.LessonManagerHolder.INSTANCE;
    }

    private static class LessonManagerHolder {

        private static final LessonManager INSTANCE = new LessonManager();
    }

    public void insertLesson(final Connection conn, final Lesson l) {

        String sql = "INSERT INTO lessons(ls_name, ls_code) VALUES(?, ?);";
        try {
            if (l.getName() == null || l.getName().equals("") || l.getClassCode() == null
                    || l.getClassCode().equals("")) {

                throw new Exception();
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, l.getName());
            stmt.setString(2, l.getClassCode());
            stmt.executeUpdate();
            stmt.close();
            //
//            conn.close();
        } catch (Exception ex) {
            log.error("Can't insert lesson: " + l, ex);
        }


    }
}
