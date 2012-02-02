package hu.sch.kurzuscsere.logic;

import hu.sch.kurzuscsere.domain.Lesson;
import hu.sch.kurzuscsere.logic.db.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        } catch (Exception ex) {
            log.error("Can't insert lesson: " + l, ex);
        }
    }

    public void importLessons(Map<String, String> lessons) {
        if (lessons != null) {

            Connection conn = DbHelper.getConnection();
            if (conn == null) {
                log.warn("Can't import lessons because connection is null");
                return;
            }

            for (String lessonCode : lessons.keySet()) {
                Lesson lesson = new Lesson();
                lesson.setClassCode(lessonCode);
                lesson.setName(lessons.get(lessonCode));

                insertLesson(conn, lesson);
            }
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                log.warn("Can't close db connection", ex);
            }
        }
    }

    /**
     * Megkeresi az átadott azonosítóval ellátott tárgyat. Ha nem találja
     * <pre>null</pre> értékkel tér vissza
     *
     * @param id
     * @return
     */
    public Lesson getLessonById(Long id) {
        Lesson lesson = null;

        Connection conn = DbHelper.getConnection();
        if (conn == null) {
            return lesson;
        }

        String sql = "SELECT * FROM lessons WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                lesson = new Lesson();
                lesson.setId(id);
                lesson.setClassCode(res.getString("ls_code"));
                lesson.setName(res.getString("ls_name"));
            }
            stmt.close();
            //
            conn.close();
        } catch (SQLException ex) {
            log.error("Can't get lesson from id: " + id, ex);
        }

        return lesson;
    }

    public List<Lesson> getLessons() {

        final Connection conn = DbHelper.getConnection();
        if (conn == null) {
            return Collections.EMPTY_LIST;
        }

        List<Lesson> lessons = new LinkedList<Lesson>();
        String sql = "SELECT * FROM lessons "
                + "ORDER BY ls_name ASC";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Lesson lsn = new Lesson();
                lsn.setId(res.getLong("id"));
                lsn.setName(res.getString("ls_name"));
                lsn.setClassCode(res.getString("ls_code"));
                lessons.add(lsn);
            }
            stmt.close();
            //
            conn.close();
        } catch (SQLException ex) {
            log.error("Can't get lessons", ex);
        }

        return lessons;
    }
}
